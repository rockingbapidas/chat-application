package com.example.whatsappsample.di

import com.example.whatsappsample.data.repository.AuthRepositoryImpl
import com.example.whatsappsample.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}