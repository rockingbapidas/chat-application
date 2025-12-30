package com.example.whatsappsample.data.remote.wrapper

import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthSourceWrapper @Inject constructor(
    @param:Named("firebase") private val firebaseAuthDataSource: AuthRemoteSource,
    @param:Named("rest") private val restAuthDataSource: AuthRemoteSource
) : AuthRemoteSource {

    private var currentSource: AuthRemoteSource = firebaseAuthDataSource

    fun useFirebase() { currentSource = firebaseAuthDataSource }
    fun useRest() { currentSource = restAuthDataSource }

    override val currentUser: Flow<UserDto?> get() = currentSource.currentUser
    override val isUserAuthenticated: Flow<Boolean> get() = currentSource.isUserAuthenticated

    override suspend fun signIn(email: String, password: String): Result<UserDto> = currentSource.signIn(email, password)
    override suspend fun signUp(email: String, password: String, name: String): Result<UserDto> = currentSource.signUp(email, password, name)
    override suspend fun signOut() = currentSource.signOut()
    override suspend fun resetPassword(email: String): Result<Unit> = currentSource.resetPassword(email)
    override suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit> = currentSource.updateProfile(name, photoUrl)
    override suspend fun deleteAccount(): Result<Unit> = currentSource.deleteAccount()
    override suspend fun updateEmail(newEmail: String): Result<Unit> = currentSource.updateEmail(newEmail)
    override suspend fun updatePassword(newPassword: String): Result<Unit> = currentSource.updatePassword(newPassword)
}
