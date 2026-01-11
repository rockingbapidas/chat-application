package com.example.whatsappsample.data.remote.wrapper

import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ChatDataSourceWrapper @Inject constructor(
    @param:Named("xmpp") private val xmppDataSource: ChatRemoteDataSource,
    @param:Named("firebase") private val firebaseDataSource: ChatRemoteDataSource,
    @param:Named("websocket") private val webSocketDataSource: ChatRemoteDataSource
) : ChatRemoteDataSource {

    var currentSource: ChatRemoteDataSource = firebaseDataSource

    fun useXmpp() { currentSource = xmppDataSource }
    fun useFirebase() { currentSource = firebaseDataSource }
    fun useWebSocket() { currentSource = webSocketDataSource }

    override fun getChats(): Flow<List<ChatDto>> = currentSource.getChats()
    override suspend fun getChat(chatId: String): Flow<ChatDto> = currentSource.getChat(chatId)
    override fun getMessages(
        chatId: String,
        limit: Int,
        beforeTimestamp: Long?
    ): Flow<List<MessageDto>> = currentSource.getMessages(chatId, limit, beforeTimestamp)
    override suspend fun sendMessage(chatId: String, message: MessageDto) = currentSource.sendMessage(chatId, message)
    override suspend fun createChat(chat: ChatDto): Flow<ChatDto> = currentSource.createChat(chat)
    override suspend fun deleteChat(chatId: String) = currentSource.deleteChat(chatId)
    override suspend fun markChatAsRead(chatId: String) = currentSource.markChatAsRead(chatId)
    override suspend fun updateChatLastMessage(chatId: String, message: MessageDto) = currentSource.updateChatLastMessage(chatId, message)
}