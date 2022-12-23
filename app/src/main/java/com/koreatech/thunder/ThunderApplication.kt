package com.koreatech.thunder

import android.app.Application
import com.koreatech.thunder.util.ThunderDeBugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ThunderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(ThunderDeBugTree())
    }
}
