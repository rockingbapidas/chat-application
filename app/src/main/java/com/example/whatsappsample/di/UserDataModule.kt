package com.example.whatsappsample.di

import com.example.whatsappsample.data.remote.wrapper.UserDataSourceWrapper
import com.example.whatsappsample.data.remote.UserRemoteDataSource
import com.example.whatsappsample.data.remote.firebase.FirebaseUserDataSourceImpl
import com.example.whatsappsample.data.remote.xmpp.XmppUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataModule {

    @Binds
    @Singleton
    @Named("firebase")
    abstract fun bindFirebaseUserRemoteDataSource(firebaseUserDataSource: FirebaseUserDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    @Named("xmpp")
    abstract fun bindXmppUserRemoteDataSource(xmppUserDataSource: XmppUserDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(userDataSourceWrapper: UserDataSourceWrapper): UserRemoteDataSource
}
