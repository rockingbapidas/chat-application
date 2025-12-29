package com.example.whatsappsample.di

import com.example.whatsappsample.data.remote.AuthSourceWrapper
import com.example.whatsappsample.data.remote.AuthRemoteSource
import com.example.whatsappsample.data.remote.firebase.FirebaseAuthRemoteSourceImpl
import com.example.whatsappsample.data.remote.rest.RestAuthRemoteSourceImpl
import com.example.whatsappsample.data.repository.AuthRepositoryImpl
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Binds
    @Singleton
    @Named("firebase")
    abstract fun bindFirebaseAuthRemoteSource(
        firebaseAuthRemoteSource: FirebaseAuthRemoteSourceImpl
    ): AuthRemoteSource

    @Binds
    @Singleton
    @Named("rest")
    abstract fun bindRestAuthRemoteSource(
        restAuthRemoteSource: RestAuthRemoteSourceImpl
    ): AuthRemoteSource

    @Binds
    @Singleton
    abstract fun bindAuthRemoteSource(authSourceWrapper: AuthSourceWrapper): AuthRemoteSource

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}