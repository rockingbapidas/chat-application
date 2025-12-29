package com.example.whatsappsample.data.local

import com.example.whatsappsample.data.local.dao.ChatDao
import com.example.whatsappsample.data.local.dao.MessageDao
import com.example.whatsappsample.data.local.dao.OutboxMessageDao
import com.example.whatsappsample.data.local.entity.ChatEntity
import com.example.whatsappsample.data.local.entity.MessageEntity
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatLocalDataSourceImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val outboxMessageDao: OutboxMessageDao
) : ChatLocalDataSource {
    override fun getChats(): Flow<List<ChatEntity>> = chatDao.getAllChats()

    override fun getChat(chatId: String): Flow<ChatEntity?> = chatDao.getChatById(chatId)

    override fun getMessages(chatId: String): Flow<List<MessageEntity>> = messageDao.getMessagesForChat(chatId)

    override fun getMessage(messageId: String): Flow<MessageEntity?> = messageDao.getMessageById(messageId)

    override suspend fun insertChat(chat: ChatEntity) = chatDao.insert(chat)

    override suspend fun insertChats(chats: List<ChatEntity>) = chatDao.insertAll(chats)

    override suspend fun insertMessage(message: MessageEntity) = messageDao.insert(message)

    override suspend fun insertMessages(messages: List<MessageEntity>) = messageDao.insertAll(messages)

    override suspend fun insertOutboxMessage(message: OutboxMessageEntity) = outboxMessageDao.insert(message)

    override suspend fun deleteChat(chatId: String) {
        chatDao.deleteChat(chatId)
        messageDao.deleteMessagesForChat(chatId)
    }

    override suspend fun deleteAll() {
        chatDao.deleteAll()
        messageDao.deleteAll()
    }
}
