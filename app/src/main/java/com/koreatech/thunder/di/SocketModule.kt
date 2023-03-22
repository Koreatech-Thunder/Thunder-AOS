package com.koreatech.thunder.di

import com.koreatech.thunder.BuildConfig.THUNDER_URI
import com.koreatech.thunder.data.source.local.AuthLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {
    @Provides
    @Singleton
    fun providesIoOption(
        localDataSource: AuthLocalDataSource
    ): IO.Options {
        val options = IO.Options()
        val header: MutableMap<String, List<String>> = HashMap()
        header["Authorization"] = listOf(localDataSource.accessToken)
        options.extraHeaders = header
        return options
    }

    @Provides
    @Singleton
    fun providesSocket(
        options: IO.Options
    ): Socket =
        IO.socket(THUNDER_URI, options)
}
