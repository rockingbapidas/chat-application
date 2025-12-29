package com.example.whatsappsample.domain.chat.model

data class Message(
    val id: String = "",
    val chatId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val content: String = "",
    val type: MessageType = MessageType.TEXT,
    val timestamp: Long = 0,
    val status: MessageStatus = MessageStatus.SENT
)

enum class MessageType {
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    DOCUMENT,
    LOCATION
}

enum class MessageStatus {
    SENT,
    DELIVERED,
    READ
}

