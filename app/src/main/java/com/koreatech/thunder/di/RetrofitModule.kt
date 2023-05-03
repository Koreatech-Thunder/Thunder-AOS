package com.koreatech.thunder.di

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.koreatech.thunder.BuildConfig.THUNDER_URI
import com.koreatech.thunder.MainActivity
import com.koreatech.thunder.data.source.local.AuthLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesInterceptor(
        @ApplicationContext context: Context,
        authLocalDataSource: AuthLocalDataSource,
        localPref: SharedPreferences
    ): Interceptor =
        Interceptor { chain ->
            val response = chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader(
                        name = "Authorization",
                        value = authLocalDataSource.accessToken
                    )
                    .build()
            )
            when (response.code) {
                WARNING_USER -> {
                    authLocalDataSource.splashState = WARNING_VIEW
                    Intent(context, MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                }
                STOP_USER -> {
                    with(localPref.edit()) {
                        clear()
                        commit()
                    }
                    Intent(context, MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                }
            }
            response
        }

    @Provides
    @Singleton
    fun providesThunderOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

    @Provides
    @Singleton
    fun providesThunderRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(THUNDER_URI)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()

    private const val WARNING_VIEW = "WARNING"
    private const val WARNING_USER = 426
    private const val STOP_USER = 427
}
