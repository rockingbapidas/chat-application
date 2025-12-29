package com.example.whatsappsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.whatsappsample.data.local.converter.Converters
import com.example.whatsappsample.data.local.dao.ChatDao
import com.example.whatsappsample.data.local.dao.MessageDao
import com.example.whatsappsample.data.local.dao.OutboxMessageDao
import com.example.whatsappsample.data.local.dao.UserDao
import com.example.whatsappsample.data.local.entity.ChatEntity
import com.example.whatsappsample.data.local.entity.MessageEntity
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity
import com.example.whatsappsample.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ChatEntity::class,
        MessageEntity::class,
        OutboxMessageEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun outboxMessageDao(): OutboxMessageDao
}

