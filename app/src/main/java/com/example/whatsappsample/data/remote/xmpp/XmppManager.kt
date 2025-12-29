package com.example.whatsappsample.data.remote.xmpp

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.ConnectionListener
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.jid.impl.JidCreate
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XmppManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val TAG = "XmppManager"
    private var connection: AbstractXMPPConnection? = null

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val isConnecting = AtomicBoolean(false)

    fun connect(username: String, password: String, host: String, port: Int, domain: String) {
        if (isConnecting.get() || _connectionState.value == ConnectionState.Connected) return

        isConnecting.set(true)
        _connectionState.value = ConnectionState.Connecting

        try {
            val config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(username, password)
                .setXmppDomain(JidCreate.domainBareFrom(domain))
                .setHost(host)
                .setPort(port)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled) // TODO: Enable TLS in production
                .setResource("Android")
                .build()

            connection = XMPPTCPConnection(config).apply {
                addConnectionListener(object : ConnectionListener {
                    override fun connected(connection: XMPPConnection?) {
                        Log.d(TAG, "Connected")
                    }

                    override fun authenticated(connection: XMPPConnection?, resumed: Boolean) {
                        _connectionState.value = ConnectionState.Connected
                        isConnecting.set(false)
                        Log.d(TAG, "Authenticated")
                    }

                    override fun connectionClosed() {
                        _connectionState.value = ConnectionState.Disconnected
                        Log.d(TAG, "Connection closed")
                    }

                    override fun connectionClosedOnError(e: Exception?) {
                        _connectionState.value = ConnectionState.Error(e?.message ?: "Unknown error")
                        isConnecting.set(false)
                        Log.e(TAG, "Connection closed on error", e)
                    }
                })
                connect().login()
            }
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.Error(e.message ?: "Unknown error")
            isConnecting.set(false)
            Log.e(TAG, "Failed to connect", e)
        }
    }

    fun disconnect() {
        connection?.disconnect()
        connection = null
        _connectionState.value = ConnectionState.Disconnected
    }

    fun getConnection(): AbstractXMPPConnection? = connection

    sealed class ConnectionState {
        object Disconnected : ConnectionState()
        object Connecting : ConnectionState()
        object Connected : ConnectionState()
        data class Error(val message: String) : ConnectionState()
    }
}
