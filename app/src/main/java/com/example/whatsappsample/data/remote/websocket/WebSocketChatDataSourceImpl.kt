package com.example.whatsappsample.data.remote.websocket

import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketChatDataSourceImpl @Inject constructor(
    private val webSocketManager: WebSocketManager
) : ChatRemoteDataSource {

    override fun getChats(): Flow<List<ChatDto>> = flow {
        // Typically chats are fetched via REST, but can be pushed via WS
        emit(emptyList())
    }

    override suspend fun getChat(chatId: String): Flow<ChatDto> = flow {
        emit(ChatDto(id = chatId))
    }

    override fun getMessages(chatId: String): Flow<MessageDto> {
        return webSocketManager.events
            .map { json ->
                // Example decoding
                try {
                    Json.decodeFromString<MessageDto>(json)
                } catch (e: Exception) {
                    null
                }
            }
            .filterNotNull()
            .filter { it.chatId == chatId }
    }

    override suspend fun sendMessage(chatId: String, message: MessageDto) {
        val json = Json.encodeToString(MessageDto.serializer(), message)
        webSocketManager.send(json)
    }

    override suspend fun createChat(chat: ChatDto): Flow<ChatDto> = flow {
        emit(chat)
    }

    override suspend fun deleteChat(chatId: String) {
        // Send delete command via WS
    }

    override suspend fun markChatAsRead(chatId: String) {
        // Send read receipt via WS
    }

    override suspend fun updateChatLastMessage(chatId: String, message: MessageDto) {
        // Send update via WS
    }
}
