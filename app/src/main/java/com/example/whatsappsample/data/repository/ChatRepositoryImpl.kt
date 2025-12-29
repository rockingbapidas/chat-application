package com.example.whatsappsample.data.repository

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.whatsappsample.data.local.ChatLocalDataSource
import com.example.whatsappsample.data.local.dao.OutboxMessageDao
import com.example.whatsappsample.data.mapper.toDomain
import com.example.whatsappsample.data.mapper.toEntity
import com.example.whatsappsample.data.mapper.toOutboxEntity
import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import com.example.whatsappsample.domain.worker.OutboxWorker
import com.example.whatsappsample.domain.chat.model.Chat
import com.example.whatsappsample.domain.chat.model.Message
import com.example.whatsappsample.domain.chat.model.MessageType
import com.example.whatsappsample.domain.chat.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val localDataSource: ChatLocalDataSource,
    private val remoteDataSource: ChatRemoteDataSource,
    private val workManager: WorkManager
) : ChatRepository {

    override fun getChats(): Flow<List<Chat>> {
        return localDataSource.getChats().map { chatEntities ->
            chatEntities.map { it.toDomain() }
        }
    }

    override suspend fun getChat(chatId: String): Flow<Chat> = flow {
        val chatEntity = localDataSource.getChat(chatId).first()
        if (chatEntity != null) {
            val lastMessage = chatEntity.lastMessageId?.let {
                localDataSource.getMessage(it).first()?.toDomain()
            }
            emit(chatEntity.toDomain(lastMessage))
        }

        val remoteChat = remoteDataSource.getChat(chatId).first()
        localDataSource.insertChat(remoteChat.toEntity())

        if (remoteChat.lastMessage != null) {
            localDataSource.insertMessage(remoteChat.lastMessage.toEntity())
        }
        emit(remoteChat.toDomain())
    }

    override fun getMessages(chatId: String): Flow<List<Message>> {
        return localDataSource.getMessages(chatId).map { messageEntities ->
            messageEntities.map { it.toDomain() }
        }
    }

    override suspend fun sendMessage(chatId: String, content: String) {
        val message = Message(
            id = UUID.randomUUID().toString(),
            content = content,
            senderId = "current_user_id", // TODO: Replace with actual user ID from auth
            chatId = chatId,
            timestamp = System.currentTimeMillis(),
            type = MessageType.TEXT
        )
        localDataSource.insertOutboxMessage(message.toOutboxEntity())

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<OutboxWorker>()
            .setConstraints(constraints)
            .build()
        workManager.enqueue(workRequest)

        localDataSource.insertMessage(message.toEntity())
        val chatEntity = localDataSource.getChat(chatId).first()
        if (chatEntity != null) {
            localDataSource.insertChat(
                chatEntity.copy(
                    lastMessageId = message.id,
                    updatedAt = message.timestamp
                )
            )
        }
    }

    override suspend fun createChat(userId: String): Flow<Chat> = flow {
        val chat = Chat(
            id = UUID.randomUUID().toString(),
            participants = listOf("current_user_id", userId), // TODO: Replace with actual user ID
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        val remoteChat = remoteDataSource.createChat(ChatDto.fromDomain(chat)).first()
        localDataSource.insertChat(remoteChat.toEntity())
        emit(remoteChat.toDomain())
    }

    override suspend fun deleteChat(chatId: String) {
        remoteDataSource.deleteChat(chatId)
        localDataSource.deleteChat(chatId)
    }

    override suspend fun markChatAsRead(chatId: String) {
        remoteDataSource.markChatAsRead(chatId)
        val chatEntity = localDataSource.getChat(chatId).first()
        if (chatEntity != null) {
            localDataSource.insertChat(chatEntity.copy(unreadCount = 0))
        }
    }

    override suspend fun updateChatLastMessage(chatId: String, message: Message) {
        remoteDataSource.updateChatLastMessage(chatId, MessageDto.fromDomain(message))
        val chatEntity = localDataSource.getChat(chatId).first()
        if (chatEntity != null) {
            localDataSource.insertChat(
                chatEntity.copy(
                    lastMessageId = message.id,
                    updatedAt = message.timestamp
                )
            )
        }
    }
}

