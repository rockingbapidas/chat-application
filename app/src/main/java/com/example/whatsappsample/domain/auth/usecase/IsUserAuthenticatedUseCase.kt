package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsUserAuthenticatedUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return authRepository.isUserAuthenticated
    }
}
