package com.example.whatsappsample.data.repository

import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.domain.auth.model.User
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
) : AuthRepository {

    override val currentUser: Flow<User?> = authRemoteSource.currentUser.map { it?.toDomain() }

    override val isUserAuthenticated: Flow<Boolean> = authRemoteSource.isUserAuthenticated

    override suspend fun signIn(email: String, password: String): Result<User> {
        return authRemoteSource.signIn(email, password).map { it.toDomain() }
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<User> {
        return authRemoteSource.signUp(email, password, name).map { it.toDomain() }
    }

    override suspend fun signOut() {
        authRemoteSource.signOut()
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return authRemoteSource.resetPassword(email)
    }

    override suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit> {
        return authRemoteSource.updateProfile(name, photoUrl)
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return authRemoteSource.deleteAccount()
    }

    override suspend fun updateEmail(newEmail: String): Result<Unit> {
        return authRemoteSource.updateEmail(newEmail)
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {
        return authRemoteSource.updatePassword(newPassword)
    }
}