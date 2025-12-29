package com.example.whatsappsample.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase
import com.example.whatsappsample.domain.auth.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            isUserAuthenticatedUseCase().collect { isAuthenticated ->
                _state.value = _state.value.copy(isAuthenticated = isAuthenticated)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            signInUseCase(email, password)
                .onSuccess {
                    _state.value = _state.value.copy(isLoading = false)
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message ?: "Login failed"
                    )
                }
        }
    }
}

