package com.example.whatsappsample.domain.chat.repository

import com.example.whatsappsample.domain.chat.model.Chat
import com.example.whatsappsample.domain.chat.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChats(): Flow<List<Chat>>
    suspend fun getChat(chatId: String): Flow<Chat>
    fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(chatId: String, content: String)
    suspend fun createChat(userId: String): Flow<Chat>
    suspend fun deleteChat(chatId: String)
    suspend fun markChatAsRead(chatId: String)
    suspend fun updateChatLastMessage(chatId: String, message: Message)
}

