package com.example.whatsappsample.domain.chat.usecase

import com.example.whatsappsample.domain.chat.model.Chat
import com.example.whatsappsample.domain.chat.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<List<Chat>> {
        return repository.getChats()
    }
}
