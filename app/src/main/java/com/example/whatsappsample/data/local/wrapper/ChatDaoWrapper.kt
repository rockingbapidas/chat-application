package com.example.whatsappsample.data.local.wrapper

import com.example.whatsappsample.data.local.entity.ChatEntity
import com.example.whatsappsample.data.local.entity.MessageEntity
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingSource

interface ChatDaoWrapper {
    fun getChats(): Flow<List<ChatEntity>>
    fun getChat(chatId: String): Flow<ChatEntity?>
    fun getMessages(chatId: String): Flow<List<MessageEntity>>
    fun getMessagesPaging(chatId: String): PagingSource<Int, MessageEntity>
    fun getMessage(messageId: String): Flow<MessageEntity?>
    suspend fun insertChat(chat: ChatEntity)
    suspend fun insertChats(chats: List<ChatEntity>)
    suspend fun insertMessage(message: MessageEntity)
    suspend fun insertMessages(messages: List<MessageEntity>)
    suspend fun insertOutboxMessage(message: OutboxMessageEntity)
    suspend fun deleteChat(chatId: String)
    suspend fun deleteAll()
}
