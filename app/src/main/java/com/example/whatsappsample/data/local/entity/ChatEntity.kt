package com.example.whatsappsample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey
    val id: String,
    val participants: List<String>,
    val lastMessageId: String?,
    val unreadCount: Int,
    val isGroup: Boolean,
    val groupName: String?,
    val groupPictureUrl: String?,
    val createdAt: Long,
    val updatedAt: Long
)

