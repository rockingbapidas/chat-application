package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.domain.auth.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.signOut()
    }
}
