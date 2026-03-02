package com.example.whatsappsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappsample.connection.ChatConnectionManager
import com.example.whatsappsample.connection.ConnectionState
import com.example.whatsappsample.data.local.AppPreferences
import com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    private val connectionManager: ChatConnectionManager,
    private val appPreferences: AppPreferences
) : ViewModel() {
    val isAuthenticated: StateFlow<Boolean?> = isUserAuthenticatedUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val connectionState: StateFlow<ConnectionState> = connectionManager.combinedState

    init {
        isAuthenticated.onEach { authenticated ->
            if (authenticated == true) {
                // In a real app, we'd get these from user profile or preferences
                val user = appPreferences.getCachedUser()
                connectionManager.connect(
                    wsUrl = "ws://echo.websocket.org", // Mock URL
                    xmppUsername = user?.phone ?: "guest",
                    xmppPassword = "password",
                    xmppHost = "localhost",
                    xmppPort = 5222,
                    xmppDomain = "localhost"
                )
            } else if (authenticated == false) {
                connectionManager.disconnectAll()
            }
        }.launchIn(viewModelScope)
    }
}
