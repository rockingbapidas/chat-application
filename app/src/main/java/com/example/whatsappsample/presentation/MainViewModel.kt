package com.example.whatsappsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase
) : ViewModel() {
    val isAuthenticated: StateFlow<Boolean?> = isUserAuthenticatedUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}
