package com.koreatech.thunder.di

import com.koreatech.thunder.data.repository.*
import com.koreatech.thunder.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsThunderRepository(
        thunderRepositoryImpl: ThunderRepositoryImpl
    ): ThunderRepository

    @Binds
    fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    fun bindsEvaluateRepository(
        evaluateRepositoryImpl: EvaluateRepositoryImpl
    ): EvaluateRepository
}
