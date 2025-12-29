package com.example.whatsappsample.di

import android.content.Context
import androidx.room.Room
import com.example.whatsappsample.data.local.AppDatabase
import com.example.whatsappsample.data.local.dao.ChatDao
import com.example.whatsappsample.data.local.dao.MessageDao
import com.example.whatsappsample.data.local.dao.OutboxMessageDao
import com.example.whatsappsample.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "whatsapp_database"
        )
        .fallbackToDestructiveMigration() // Useful for development if schema changes
        .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideChatDao(database: AppDatabase): ChatDao {
        return database.chatDao()
    }

    @Provides
    fun provideMessageDao(database: AppDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    fun provideOutboxMessageDao(database: AppDatabase): OutboxMessageDao {
        return database.outboxMessageDao()
    }
}