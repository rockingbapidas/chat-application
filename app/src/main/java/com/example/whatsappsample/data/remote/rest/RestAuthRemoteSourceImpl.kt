package com.example.whatsappsample.data.remote.rest

import com.example.whatsappsample.data.local.SessionManager
import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.data.remote.dto.AuthRequest
import com.example.whatsappsample.data.remote.dto.AuthResponse
import com.example.whatsappsample.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestAuthRemoteSourceImpl @Inject constructor(
    private val client: HttpClient,
    private val sessionManager: SessionManager
) : AuthRemoteSource {

    private val baseUrl = "https://api.example.com/v1" // Replace with actual API base URL

    private val _currentUser = MutableStateFlow<UserDto?>(null)
    override val currentUser: Flow<UserDto?> = _currentUser

    private val _isUserAuthenticated = MutableStateFlow(sessionManager.getToken() != null)
    override val isUserAuthenticated: Flow<Boolean> = _isUserAuthenticated

    override suspend fun signIn(email: String, password: String): Result<UserDto> {
        return try {
            val response: AuthResponse = client.post("$baseUrl/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(email = email, password = password))
            }.body()
            
            sessionManager.saveToken(response.token)
            _currentUser.value = response.user
            _isUserAuthenticated.value = true
            Result.success(response.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<UserDto> {
        return try {
            val response: AuthResponse = client.post("$baseUrl/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(email = email, password = password, name = name))
            }.body()
            
            sessionManager.saveToken(response.token)
            _currentUser.value = response.user
            _isUserAuthenticated.value = true
            Result.success(response.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut() {
        try {
            client.post("$baseUrl/auth/logout")
        } catch (e: Exception) {
            // Log error but proceed to clear local session
        } finally {
            sessionManager.clear()
            _currentUser.value = null
            _isUserAuthenticated.value = false
        }
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            client.post("$baseUrl/auth/reset-password") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("email" to email))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateProfile(name: String?, photoUrl: String?): Result<Unit> {
        return try {
            client.put("$baseUrl/user/profile") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("name" to name, "photoUrl" to photoUrl))
            }
            // Fetch updated user info or manually update _currentUser
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return try {
            client.delete("$baseUrl/user/account")
            signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateEmail(newEmail: String): Result<Unit> {
        return try {
            client.put("$baseUrl/user/email") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("email" to newEmail))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {
        return try {
            client.put("$baseUrl/user/password") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("password" to newPassword))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
