package com.example.whatsappsample.data.remote

import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun getChats(): Flow<List<ChatDto>>
    suspend fun getChat(chatId: String): Flow<ChatDto>
    fun getMessages(chatId: String): Flow<MessageDto>
    suspend fun sendMessage(chatId: String, message: MessageDto)
    suspend fun createChat(chat: ChatDto): Flow<ChatDto>
    suspend fun deleteChat(chatId: String)
    suspend fun markChatAsRead(chatId: String)
    suspend fun updateChatLastMessage(chatId: String, message: MessageDto)
}

