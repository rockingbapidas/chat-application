package com.example.whatsappsample.data.mapper

import com.example.whatsappsample.data.local.entity.ChatEntity
import com.example.whatsappsample.data.remote.dto.ChatDto
import com.example.whatsappsample.domain.chat.model.Chat
import com.example.whatsappsample.domain.chat.model.Message

fun ChatEntity.toDomain(lastMessage: Message? = null): Chat {
    return Chat(
        id = id,
        participants = participants,
        lastMessage = lastMessage,
        unreadCount = unreadCount,
        isGroup = isGroup,
        groupName = groupName,
        groupPictureUrl = groupPictureUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Chat.toEntity(): ChatEntity {
    return ChatEntity(
        id = id,
        participants = participants,
        lastMessageId = lastMessage?.id,
        unreadCount = unreadCount,
        isGroup = isGroup,
        groupName = groupName,
        groupPictureUrl = groupPictureUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun ChatDto.toEntity(): ChatEntity {
    return ChatEntity(
        id = id,
        participants = participants,
        lastMessageId = lastMessage?.id,
        unreadCount = unreadCount,
        isGroup = isGroup,
        groupName = groupName,
        groupPictureUrl = groupPictureUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
