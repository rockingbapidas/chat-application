package com.example.whatsappsample.data.local.dao

import androidx.room.*
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity
import com.example.whatsappsample.data.local.entity.OutboxStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OutboxMessageDao {
    @Query("SELECT * FROM outbox_messages WHERE status IN (:statuses)")
    suspend fun getMessagesByStatus(statuses: List<OutboxStatus>): List<OutboxMessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: OutboxMessageEntity)

    @Update
    suspend fun update(message: OutboxMessageEntity)

    @Delete
    suspend fun delete(message: OutboxMessageEntity)

    @Query("DELETE FROM outbox_messages WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM outbox_messages ORDER BY timestamp ASC")
    fun observeAll(): Flow<List<OutboxMessageEntity>>
}

