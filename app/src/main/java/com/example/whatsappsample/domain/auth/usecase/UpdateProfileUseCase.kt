package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.domain.auth.repository.AuthRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(displayName: String, profilePictureUrl: String?) {
        authRepository.updateProfile(displayName, profilePictureUrl)
    }
}
