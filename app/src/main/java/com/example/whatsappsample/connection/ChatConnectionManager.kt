package com.example.whatsappsample.connection

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatConnectionManager @Inject constructor(
    private val webSocketManager: WebSocketManager,
    private val xmppManager: XmppManager
) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val webSocketState: StateFlow<ConnectionState> = webSocketManager.connectionState
    val xmppState: StateFlow<ConnectionState> = xmppManager.connectionState

    val combinedState: StateFlow<ConnectionState> = combine(
        webSocketState,
        xmppState
    ) { ws, xmpp ->
        when {
            ws is ConnectionState.Error -> ws
            xmpp is ConnectionState.Error -> xmpp
            ws == ConnectionState.Connecting || xmpp == ConnectionState.Connecting -> ConnectionState.Connecting
            ws == ConnectionState.Connected && xmpp == ConnectionState.Connected -> ConnectionState.Connected
            else -> ConnectionState.Disconnected
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ConnectionState.Disconnected
    )

    fun connectWebSocket(url: String) {
        webSocketManager.connect(url)
    }

    fun connectXmpp(username: String, password: String, host: String, port: Int, domain: String) {
        xmppManager.connect(username, password, host, port, domain)
    }

    fun connect(
        wsUrl: String,
        xmppUsername: String,
        xmppPassword: String,
        xmppHost: String,
        xmppPort: Int,
        xmppDomain: String
    ) {
        connectWebSocket(wsUrl)
        connectXmpp(xmppUsername, xmppPassword, xmppHost, xmppPort, xmppDomain)
    }

    fun disconnectAll() {
        webSocketManager.disconnect()
        xmppManager.disconnect()
    }
}
