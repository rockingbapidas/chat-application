package com.example.whatsappsample.data.remote

import com.example.whatsappsample.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface AuthRemoteSource {
    val currentUser: Flow<UserDto?>
    val isUserAuthenticated: Flow<Boolean>

    suspend fun signIn(email: String, password: String): Result<UserDto>
    suspend fun signUp(email: String, password: String, name: String): Result<UserDto>
    suspend fun signOut()
    suspend fun resetPassword(email: String): Result<Unit>
    suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit>
    suspend fun deleteAccount(): Result<Unit>
    suspend fun updateEmail(newEmail: String): Result<Unit>
    suspend fun updatePassword(newPassword: String): Result<Unit>
}
