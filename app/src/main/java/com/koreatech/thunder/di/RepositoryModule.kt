package com.koreatech.thunder.di

import com.koreatech.thunder.data.repository.ThunderRepositoryImpl
import com.koreatech.thunder.domain.repository.ThunderRepository
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
}
