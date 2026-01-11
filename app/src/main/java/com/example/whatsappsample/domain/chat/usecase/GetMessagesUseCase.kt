package com.example.whatsappsample.domain.chat.usecase

import androidx.paging.PagingData
import com.example.whatsappsample.domain.chat.model.Message
import com.example.whatsappsample.domain.chat.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(chatId: String): Flow<PagingData<Message>> {
        return repository.getMessagesPaging(chatId)
    }
}
