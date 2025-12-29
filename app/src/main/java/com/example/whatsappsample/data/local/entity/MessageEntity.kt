package com.example.whatsappsample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val type: String, // TEXT, IMAGE, AUDIO, etc.
    val timestamp: Long,
    val status: String // SENT, DELIVERED, READ
)

