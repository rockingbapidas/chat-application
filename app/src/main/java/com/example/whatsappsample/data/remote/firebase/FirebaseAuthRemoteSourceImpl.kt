package com.example.whatsappsample.data.remote.firebase

import androidx.core.net.toUri
import com.example.whatsappsample.data.local.AppPreferences
import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.data.remote.dto.UserDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRemoteSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val appPreferences: AppPreferences
) : AuthRemoteSource {

    override val currentUser: Flow<UserDto?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            val dto = user?.toDto()
            trySend(dto)
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }.onEach { dto ->
        dto?.let { appPreferences.saveUser(it) }
    }

    override val isUserAuthenticated: Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser != null)
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override suspend fun signIn(email: String, password: String): Result<UserDto> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val dto = result.user?.toDto() ?: throw IllegalStateException("User is null")
            appPreferences.saveUser(dto)
            Result.success(dto)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<UserDto> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            )?.await()
            val dto = result.user?.toDto() ?: throw IllegalStateException("User is null")
            appPreferences.saveUser(dto)
            Result.success(dto)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
        appPreferences.clear()
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit> {
        return try {
            val profileUpdates = UserProfileChangeRequest.Builder()
            name?.let { profileUpdates.setDisplayName(it) }
            photoUrl?.let { profileUpdates.setPhotoUri(it.toUri()) }
            firebaseAuth.currentUser?.updateProfile(profileUpdates.build())?.await()
            firebaseAuth.currentUser?.toDto()?.let { appPreferences.saveUser(it) }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return try {
            firebaseAuth.currentUser?.delete()?.await()
            signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateEmail(newEmail: String): Result<Unit> {
        return try {
            firebaseAuth.currentUser?.updateEmail(newEmail)?.await()
            firebaseAuth.currentUser?.toDto()?.let { appPreferences.saveUser(it) }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {
        return try {
            firebaseAuth.currentUser?.updatePassword(newPassword)?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun FirebaseUser.toDto(): UserDto {
        return UserDto(
            id = uid,
            name = displayName ?: "",
            email = email ?: "",
            phone = phoneNumber ?: "",
            profilePictureUrl = photoUrl?.toString() ?: "",
            status = "",
            lastSeen = 0
        )
    }
}
