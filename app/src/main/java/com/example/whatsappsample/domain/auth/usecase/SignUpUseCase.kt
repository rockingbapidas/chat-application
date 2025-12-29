package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.domain.auth.model.User
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, displayName: String): Result<User> {
        return authRepository.signUp(email, password, displayName)
    }
}
