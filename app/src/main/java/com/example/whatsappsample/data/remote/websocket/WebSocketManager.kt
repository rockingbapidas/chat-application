package com.example.whatsappsample.data.remote.websocket

import android.util.Log
import com.example.whatsappsample.data.remote.dto.ConnectionState
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(
    private val client: HttpClient
) {
    private val TAG = "WebSocketManager"
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var session: DefaultClientWebSocketSession? = null

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val isConnecting = AtomicBoolean(false)

    // Message listener
    private val _events = MutableSharedFlow<String>()
    val events: SharedFlow<String> = _events.asSharedFlow()

    fun connect(url: String) {
        if (isConnecting.get() || _connectionState.value == ConnectionState.Connected) return

        isConnecting.set(true)
        _connectionState.value = ConnectionState.Connecting

        scope.launch {
            try {
                client.webSocket(url) {
                    session = this
                    _connectionState.value = ConnectionState.Connected
                    isConnecting.set(false)
                    Log.d(TAG, "WebSocket connected")

                    for (frame in incoming) {
                        frame as? Frame.Text ?: ""
                        val text = frame.readBytes()
                        _events.emit(text.decodeToString())
                    }
                }
            } catch (e: Exception) {
                _connectionState.value = ConnectionState.Error(e.message ?: "Unknown error")
                isConnecting.set(false)
                Log.e(TAG, "WebSocket error: ${e.message}")
            } finally {
                session = null
                _connectionState.value = ConnectionState.Disconnected
                Log.d(TAG, "WebSocket disconnected")
            }
        }
    }

    fun disconnect() {
        scope.launch {
            session?.close()
            _connectionState.value = ConnectionState.Disconnected
        }
    }

    fun getConnection(): DefaultClientWebSocketSession? = session
}
