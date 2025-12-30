package com.example.whatsappsample.data.local

import android.content.Context
import com.example.whatsappsample.data.remote.dto.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class AppPreferences @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    private val _currentUser = MutableStateFlow(getCachedUser())
    val currentUser: Flow<UserDto?> = _currentUser.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(getToken() != null)
    val isAuthenticated: Flow<Boolean> = _isAuthenticated.asStateFlow()

    fun saveToken(token: String) {
        prefs.edit { putString(KEY_TOKEN, token) }
        _isAuthenticated.value = true
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun saveUser(user: UserDto) {
        val userJson = json.encodeToString(user)
        prefs.edit { putString(KEY_USER, userJson) }
        _currentUser.value = user
    }

    fun getCachedUser(): UserDto? {
        val userJson = prefs.getString(KEY_USER, null) ?: return null
        return try {
            json.decodeFromString<UserDto>(userJson)
        } catch (e: Exception) {
            null
        }
    }

    fun clear() {
        prefs.edit { clear() }
        _currentUser.value = null
        _isAuthenticated.value = false
    }

    companion object Companion {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_USER = "auth_user"
    }
}
