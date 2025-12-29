package com.example.whatsappsample.data.remote.xmpp

import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.data.remote.dto.MessageDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.packet.Message
import org.jxmpp.jid.impl.JidCreate
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XmppChatDataSourceImpl @Inject constructor(
    private val xmppManager: XmppManager
) : ChatRemoteDataSource {

    private val chatManager: ChatManager?
        get() = xmppManager.getConnection()?.let { ChatManager.getInstanceFor(it) }

    override fun getChats(): Flow<List<ChatDto>> = flow {
        // In a real app, this would use MAM or a separate server API
        // For now returning empty list as XMPP doesn't store state by default
        emit(emptyList())
    }

    override suspend fun getChat(chatId: String): Flow<ChatDto> = flow {
        // Return a mock chat DTO for XMPP
        emit(ChatDto(id = chatId, participants = listOf(chatId)))
    }

    override fun getMessages(chatId: String): Flow<MessageDto> = callbackFlow {
        val listener = org.jivesoftware.smack.chat2.IncomingChatMessageListener { from, message, chat ->
            if (from.asBareJid().toString() == chatId) {
                trySend(
                    MessageDto(
                        id = message.stanzaId ?: UUID.randomUUID().toString(),
                        content = message.body ?: "",
                        senderId = from.asBareJid().toString(),
                        chatId = chatId,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
        chatManager?.addIncomingListener(listener)
        awaitClose { chatManager?.removeIncomingListener(listener) }
    }

    override suspend fun sendMessage(chatId: String, message: MessageDto) {
        val jid = JidCreate.entityBareFrom(chatId)
        val chat = chatManager?.chatWith(jid)
        val xmppMessage = Message(jid, Message.Type.chat).apply {
            body = message.content
            stanzaId = message.id
        }
        chat?.send(xmppMessage)
    }

    override suspend fun createChat(chat: ChatDto): Flow<ChatDto> = flow {
        emit(chat)
    }

    override suspend fun deleteChat(chatId: String) {
        // XMPP doesn't have "delete chat" in the same way Firebase does
    }

    override suspend fun markChatAsRead(chatId: String) {
        // Can send a read receipt stanza here
    }

    override suspend fun updateChatLastMessage(chatId: String, message: MessageDto) {
        // Update local state or notify via XMPP (e.g. state chat markers)
    }
}
