package com.example.whatsappsample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outbox_messages")
data class OutboxMessageEntity(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val type: String,
    val timestamp: Long,
    val status: OutboxStatus
)

enum class OutboxStatus {
    PENDING, SENDING, SENT, FAILED
}

