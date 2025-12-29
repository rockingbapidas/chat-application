package com.example.whatsappsample.di

import com.example.whatsappsample.data.remote.ChatDataSourceWrapper
import com.example.whatsappsample.data.local.ChatLocalDataSource
import com.example.whatsappsample.data.local.ChatLocalDataSourceImpl
import com.example.whatsappsample.data.remote.ChatRemoteDataSource
import com.example.whatsappsample.data.remote.firebase.FirebaseChatDataSourceImpl
import com.example.whatsappsample.data.remote.xmpp.XmppChatDataSourceImpl
import com.example.whatsappsample.data.remote.websocket.WebSocketChatDataSourceImpl
import com.example.whatsappsample.data.repository.ChatRepositoryImpl
import com.example.whatsappsample.domain.chat.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatDataModule {
    @Binds
    @Singleton
    abstract fun bindChatLocalDataSource(chatLocalDataSource: ChatLocalDataSourceImpl): ChatLocalDataSource

    @Binds
    @Singleton
    @Named("firebase")
    abstract fun bindFirebaseChatRemoteDataSource(firebaseChatDataSource: FirebaseChatDataSourceImpl): ChatRemoteDataSource

    @Binds
    @Singleton
    @Named("xmpp")
    abstract fun bindXmppChatRemoteDataSource(xmppChatDataSource: XmppChatDataSourceImpl): ChatRemoteDataSource

    @Binds
    @Singleton
    @Named("websocket")
    abstract fun bindWebSocketChatRemoteDataSource(webSocketChatDataSource: WebSocketChatDataSourceImpl): ChatRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindChatRemoteDataSource(chatDataSourceWrapper: ChatDataSourceWrapper): ChatRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository
}