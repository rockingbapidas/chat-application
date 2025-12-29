package com.example.whatsappsample.data.remote.dto

import com.example.whatsappsample.domain.chat.model.Chat
import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(
    val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: MessageDto? = null,
    val unreadCount: Int = 0,
    val isGroup: Boolean = false,
    val groupName: String? = null,
    val groupPictureUrl: String? = null,
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
) {
    fun toDomain(): Chat = Chat(
        id = id,
        participants = participants,
        lastMessage = lastMessage?.toDomain(),
        unreadCount = unreadCount,
        isGroup = isGroup,
        groupName = groupName,
        groupPictureUrl = groupPictureUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(chat: Chat): ChatDto = ChatDto(
            id = chat.id,
            participants = chat.participants,
            lastMessage = chat.lastMessage?.let { MessageDto.fromDomain(it) },
            unreadCount = chat.unreadCount,
            isGroup = chat.isGroup,
            groupName = chat.groupName,
            groupPictureUrl = chat.groupPictureUrl,
            createdAt = chat.createdAt,
            updatedAt = chat.updatedAt
        )
    }
}
