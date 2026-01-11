package com.example.whatsappsample.data.repository

import com.example.whatsappsample.data.local.wrapper.ChatDaoWrapper
import com.example.whatsappsample.data.mapper.toEntity
import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.domain.chat.repository.SyncRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepositoryImpl @Inject constructor(
    private val localDataSource: ChatDaoWrapper,
    private val remoteDataSource: ChatRemoteDataSource
) : SyncRepository {

    override suspend fun initialSync(onProgress: suspend (current: Int, total: Int, message: String) -> Unit) {
        onProgress(0, 100, "Fetching chats...")
        val remoteChats = remoteDataSource.getChats().first()
        localDataSource.insertChats(remoteChats.map { it.toEntity() })

        val totalChats = remoteChats.size
        for ((index, chat) in remoteChats.withIndex()) {
            val progress = ((index + 1) * 100) / totalChats
            onProgress(index + 1, totalChats, "Syncing chat ${index + 1} of $totalChats...")
            syncChat(chat.id)
        }
    }

    override suspend fun syncChat(chatId: String, onProgress: (current: Int, total: Int) -> Unit) {
        var lastTimestamp: Long? = null
        var hasMore = true
        val limit = 50
        var totalSynced = 0

        while (hasMore) {
            val remoteMessages = remoteDataSource.getMessages(chatId, limit = limit, beforeTimestamp = lastTimestamp).first()
            if (remoteMessages.isEmpty()) {
                hasMore = false
            } else {
                localDataSource.insertMessages(remoteMessages.map { it.toEntity() })
                totalSynced += remoteMessages.size
                lastTimestamp = remoteMessages.minOf { it.timestamp }
                onProgress(totalSynced, totalSynced) // For nested progress if needed
                
                if (remoteMessages.size < limit) {
                    hasMore = false
                }
            }
        }
    }
}
