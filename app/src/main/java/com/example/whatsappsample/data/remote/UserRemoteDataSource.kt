package com.example.whatsappsample.data.remote

import com.example.whatsappsample.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUsers(): Flow<List<UserDto>>
    suspend fun getUser(userId: String): Flow<UserDto>
    suspend fun updateProfile(user: UserDto)
    suspend fun searchUsers(query: String): Flow<List<UserDto>>
}
