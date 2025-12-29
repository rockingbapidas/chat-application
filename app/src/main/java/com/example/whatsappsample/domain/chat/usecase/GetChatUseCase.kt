package com.example.whatsappsample.domain.chat.usecase

import com.example.whatsappsample.domain.chat.model.Chat
import com.example.whatsappsample.domain.chat.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String): Flow<Chat> {
        return repository.getChat(chatId)
    }
}
