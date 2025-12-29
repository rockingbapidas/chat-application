package com.example.whatsappsample.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappsample.domain.auth.model.User
import com.example.whatsappsample.domain.auth.usecase.GetCurrentUserUseCase
import com.example.whatsappsample.domain.auth.usecase.SignOutUseCase
import com.example.whatsappsample.domain.auth.usecase.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getCurrentUserUseCase().collect { user ->
                    _state.value = _state.value.copy(
                        user = user,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateProfile(name: String) {
        viewModelScope.launch {
            try {
                val currentUser = _state.value.user
                if (currentUser != null) {
                    updateProfileUseCase(name, currentUser.profilePictureUrl)
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                signOutUseCase()
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }
}
