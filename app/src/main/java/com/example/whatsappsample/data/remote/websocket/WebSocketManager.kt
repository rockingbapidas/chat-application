package com.example.whatsappsample.data.remote.websocket

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(
    private val client: HttpClient
) {
    private val TAG = "WebSocketManager"
    private var session: DefaultClientWebSocketSession? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _events = MutableSharedFlow<String>()
    val events: SharedFlow<String> = _events.asSharedFlow()

    fun connect(url: String) {
        scope.launch {
            try {
                client.webSocket(url) {
                    session = this
                    Log.d(TAG, "WebSocket connected")
                    for (frame in incoming) {
                        frame as? Frame.Text ?: ""
                        val text = frame.readBytes()
                        _events.emit(text.decodeToString())
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "WebSocket error: ${e.message}")
            } finally {
                session = null
                Log.d(TAG, "WebSocket disconnected")
            }
        }
    }

    suspend fun send(message: String) {
        session?.send(Frame.Text(message))
    }

    fun disconnect() {
        scope.launch {
            session?.close()
        }
    }
}
