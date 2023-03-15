package com.koreatech.thunder.di

import com.koreatech.thunder.data.service.AuthService
import com.koreatech.thunder.data.service.ThunderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesThunderService(retrofit: Retrofit): ThunderService =
        retrofit.create(ThunderService::class.java)
}