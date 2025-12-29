package com.example.whatsappsample.domain.auth.repository

import com.example.whatsappsample.domain.auth.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    val isUserAuthenticated: Flow<Boolean>

    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String, name: String): Result<User>
    suspend fun signOut()
    suspend fun resetPassword(email: String): Result<Unit>
    suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit>
    suspend fun deleteAccount(): Result<Unit>
    suspend fun updateEmail(newEmail: String): Result<Unit>
    suspend fun updatePassword(newPassword: String): Result<Unit>
}

