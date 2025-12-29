package com.example.whatsappsample.data.remote.dto

import com.example.whatsappsample.domain.chat.model.Message
import com.example.whatsappsample.domain.chat.model.MessageStatus
import com.example.whatsappsample.domain.chat.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String = "",
    val chatId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val content: String = "",
    val type: String = "TEXT",
    val timestamp: Long = 0L,
    val status: String = "SENT"
) {
    fun toDomain(): Message = Message(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = try { MessageType.valueOf(type) } catch(e: Exception) { MessageType.TEXT },
        timestamp = timestamp,
        status = try { MessageStatus.valueOf(status) } catch(e: Exception) { MessageStatus.SENT }
    )

    companion object {
        fun fromDomain(message: Message): MessageDto = MessageDto(
            id = message.id,
            chatId = message.chatId,
            senderId = message.senderId,
            receiverId = message.receiverId,
            content = message.content,
            type = message.type.name,
            timestamp = message.timestamp,
            status = message.status.name
        )
    }
}
