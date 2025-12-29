package com.example.whatsappsample.domain.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.whatsappsample.data.local.dao.OutboxMessageDao
import com.example.whatsappsample.data.local.entity.OutboxStatus
import com.example.whatsappsample.data.mapper.toDomain
import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.dto.MessageDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class OutboxWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val outboxMessageDao: OutboxMessageDao,
    private val chatRemoteDataSource: ChatRemoteDataSource
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val pendingMessages = outboxMessageDao.getMessagesByStatus(listOf(OutboxStatus.PENDING, OutboxStatus.FAILED))
        var allSuccess = true
        for (msg in pendingMessages) {
            try {
                outboxMessageDao.update(msg.copy(status = OutboxStatus.SENDING))
                val message = msg.toDomain()
                val messageDto = MessageDto.fromDomain(message)
                chatRemoteDataSource.sendMessage(msg.chatId, messageDto)
                outboxMessageDao.update(msg.copy(status = OutboxStatus.SENT))
                outboxMessageDao.delete(msg)
            } catch (e: Exception) {
                outboxMessageDao.update(msg.copy(status = OutboxStatus.FAILED))
                allSuccess = false
            }
        }
        if (allSuccess) Result.success() else Result.retry()
    }
}

