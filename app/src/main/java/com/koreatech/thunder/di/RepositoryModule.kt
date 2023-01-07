package com.koreatech.thunder.di

import com.koreatech.thunder.data.repository.ThunderRepositoryImpl
import com.koreatech.thunder.domain.repository.ThunderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsThunderRepository(thunderRepositoryImpl: ThunderRepositoryImpl): ThunderRepository
}
