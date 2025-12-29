package com.example.whatsappsample.data.remote.firebase

import com.example.whatsappsample.data.remote.UserRemoteDataSource
import com.example.whatsappsample.data.remote.dto.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRemoteDataSource {

    override fun getUsers(): Flow<List<UserDto>> = callbackFlow {
        val listener = firestore.collection("users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val users = snapshot?.documents?.mapNotNull { it.toObject(UserDto::class.java) } ?: emptyList()
                trySend(users)
            }
        awaitClose { listener.remove() }
    }

    override suspend fun getUser(userId: String): Flow<UserDto> = callbackFlow {
        val listener = firestore.collection("users").document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val user = snapshot?.toObject(UserDto::class.java)
                if (user != null) {
                    trySend(user)
                }
            }
        awaitClose { listener.remove() }
    }

    override suspend fun updateProfile(user: UserDto) {
        firestore.collection("users").document(user.id).set(user).await()
    }

    override suspend fun searchUsers(query: String): Flow<List<UserDto>> = callbackFlow {
        val listener = firestore.collection("users")
            .whereGreaterThanOrEqualTo("name", query)
            .whereLessThanOrEqualTo("name", query + "\uf8ff")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val users = snapshot?.documents?.mapNotNull { it.toObject(UserDto::class.java) } ?: emptyList()
                trySend(users)
            }
        awaitClose { listener.remove() }
    }
}
