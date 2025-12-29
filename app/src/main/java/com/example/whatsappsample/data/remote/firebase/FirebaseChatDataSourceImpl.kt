package com.example.whatsappsample.data.remote.firebase

import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseChatDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRemoteDataSource {
    override fun getMessages(chatId: String): Flow<MessageDto> = callbackFlow {
        val listener = firestore.collection("chats").document(chatId).collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                snapshot?.documents?.mapNotNull { it.toObject(MessageDto::class.java) }?.forEach {
                    trySend(it)
                }
            }
        awaitClose { listener.remove() }
    }

    override suspend fun sendMessage(chatId: String, message: MessageDto) {
        firestore.collection("chats").document(chatId).collection("messages").add(message)
    }

    override fun getChats(): Flow<List<ChatDto>> = callbackFlow {
        val listener = firestore.collection("chats")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                snapshot?.documents?.mapNotNull { it.toObject(ChatDto::class.java) }?.let {
                    trySend(it)
                }
            }
        awaitClose { listener.remove() }
    }

    override suspend fun getChat(chatId: String): Flow<ChatDto> = flow {
        val document = firestore.collection("chats").document(chatId).get().await()
        val chat = document.toObject(ChatDto::class.java)
            ?: throw IllegalStateException("Chat not found")
        emit(chat)
    }

    override suspend fun createChat(chat: ChatDto): Flow<ChatDto> = flow {
        firestore.collection("chats").document(chat.id).set(chat).await()
        emit(chat)
    }

    override suspend fun deleteChat(chatId: String) {
        firestore.collection("chats").document(chatId).delete().await()
    }

    override suspend fun markChatAsRead(chatId: String) {
        // Not implemented for Firebase
    }

    override suspend fun updateChatLastMessage(chatId: String, message: MessageDto) {
        firestore.collection("chats").document(chatId).update("lastMessage", message).await()
    }
}

