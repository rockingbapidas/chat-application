package com.example.whatsappsample.domain.chat.model

data class Chat(
    val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: Message? = null,
    val unreadCount: Int = 0,
    val isGroup: Boolean = false,
    val groupName: String? = null,
    val groupPictureUrl: String? = null,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
) {
    val name: String
        get() = groupName ?: "Chat"
}

