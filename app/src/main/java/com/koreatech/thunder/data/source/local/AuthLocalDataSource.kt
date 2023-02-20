package com.koreatech.thunder.data.source.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    var splashState: String
        set(value) = prefs.edit { putString(SPLASH_STATE, value) }
        get() = prefs.getString(SPLASH_STATE, DEFAULT_STATE) ?: DEFAULT_STATE

    var accessToken: String
        set(value) = prefs.edit { putString(ACCESS_TOKEN, value) }
        get() = prefs.getString(ACCESS_TOKEN, "") ?: ""

    var refreshToken: String
        set(value) = prefs.edit { putString(REFRESH_TOKEN, value) }
        get() = prefs.getString(REFRESH_TOKEN, "") ?: ""

    companion object {
        private const val SPLASH_STATE = "splash_state"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val DEFAULT_STATE = "LOGIN"
    }
}
