package com.example.whatsappsample.domain.auth.usecase

import com.example.whatsappsample.data.local.AppPreferences
import javax.inject.Inject

class GetCurrentUserIdUseCase @Inject constructor(
    private val appPreferences: AppPreferences
) {
    operator fun invoke(): String {
        return appPreferences.getCachedUser()?.id ?: "unknown"
    }
}
