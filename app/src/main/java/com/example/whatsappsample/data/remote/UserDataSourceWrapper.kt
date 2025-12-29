package com.example.whatsappsample.data.remote

import com.example.whatsappsample.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserDataSourceWrapper @Inject constructor(
    @param:Named("xmpp") private val xmppDataSource: UserRemoteDataSource,
    @param:Named("firebase") private val firebaseDataSource: UserRemoteDataSource
) : UserRemoteDataSource {

    var currentSource: UserRemoteDataSource = firebaseDataSource

    fun useXmpp() { currentSource = xmppDataSource }
    fun useFirebase() { currentSource = firebaseDataSource }

    override fun getUsers(): Flow<List<UserDto>> = currentSource.getUsers()
    override suspend fun getUser(userId: String): Flow<UserDto> = currentSource.getUser(userId)
    override suspend fun updateProfile(user: UserDto) = currentSource.updateProfile(user)
    override suspend fun searchUsers(query: String): Flow<List<UserDto>> = currentSource.searchUsers(query)
}
