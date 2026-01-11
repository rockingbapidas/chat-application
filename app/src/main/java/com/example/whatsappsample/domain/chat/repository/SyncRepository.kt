package com.example.whatsappsample.domain.chat.repository

interface SyncRepository {
    suspend fun initialSync(onProgress: suspend (current: Int, total: Int, message: String) -> Unit)
    suspend fun syncChat(chatId: String, onProgress: (current: Int, total: Int) -> Unit = { _, _ -> })
}
