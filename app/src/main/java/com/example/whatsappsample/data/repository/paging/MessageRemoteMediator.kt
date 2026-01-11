package com.example.whatsappsample.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.whatsappsample.data.local.entity.MessageEntity
import com.example.whatsappsample.data.local.wrapper.ChatDaoWrapper
import com.example.whatsappsample.data.mapper.toEntity
import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPagingApi::class)
class MessageRemoteMediator(
    private val chatId: String,
    private val chatDao: ChatDaoWrapper,
    private val remoteDataSource: ChatRemoteDataSource
) : RemoteMediator<Int, MessageEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MessageEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.timestamp
                }
            }

            // Since we are scrolling UP (older messages), APPEND means loading messages older than the oldest one we have.
            // Our query is ORDER BY timestamp DESC, so APPEND loads smaller timestamps.
            
            val response = remoteDataSource.getMessages(
                chatId = chatId,
                limit = state.config.pageSize,
                beforeTimestamp = loadKey
            ).first()

            chatDao.insertMessages(response.map { it.toEntity() })

            MediatorResult.Success(endOfPaginationReached = response.size < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
