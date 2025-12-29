package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.domain.auth.model.User
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.signIn(email, password)
    }
}
