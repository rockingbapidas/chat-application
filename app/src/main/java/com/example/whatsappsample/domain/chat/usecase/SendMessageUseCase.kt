package com.example.whatsappsample.domain.chat.usecase

import com.example.whatsappsample.domain.chat.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String, content: String) {
        repository.sendMessage(chatId, content)
    }
}
