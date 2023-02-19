package com.koreatech.thunder.di

import com.koreatech.thunder.data.repository.AuthRepositoryImpl
import com.koreatech.thunder.data.repository.ThunderRepositoryImpl
import com.koreatech.thunder.data.repository.UserRepositoryImpl
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.domain.repository.UserRepository
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
}
