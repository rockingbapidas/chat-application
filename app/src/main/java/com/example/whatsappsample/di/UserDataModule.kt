package com.example.whatsappsample.di

import com.example.whatsappsample.data.remote.UserRemoteDataSource
import com.example.whatsappsample.data.remote.xmpp.XmppUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataModule {
    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(xmppUserRemoteDataSource: XmppUserDataSourceImpl): UserRemoteDataSource
}
