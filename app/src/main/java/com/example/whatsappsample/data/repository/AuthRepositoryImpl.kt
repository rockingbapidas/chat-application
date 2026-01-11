package com.example.whatsappsample.data.repository

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.whatsappsample.data.local.AppPreferences
import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.domain.auth.model.User
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import com.example.whatsappsample.domain.worker.InitialSyncWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val workManager: WorkManager,
    appPreferences: AppPreferences
) : AuthRepository {

    override val currentUser: Flow<User?> = appPreferences.currentUser.map { it?.toDomain() }

    override val isUserAuthenticated: Flow<Boolean> = appPreferences.isAuthenticated

    override suspend fun signIn(email: String, password: String): Result<User> {
        val result = authRemoteSource.signIn(email, password).map { it.toDomain() }
        if (result.isSuccess) {
            triggerSync()
        }
        return result
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<User> {
        val result = authRemoteSource.signUp(email, password, name).map { it.toDomain() }
        if (result.isSuccess) {
            triggerSync()
        }
        return result
    }

    private fun triggerSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<InitialSyncWorker>()
            .setConstraints(constraints)
            .build()
        workManager.enqueue(workRequest)
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