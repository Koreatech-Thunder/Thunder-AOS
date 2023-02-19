package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.SplashState

interface AuthRepository {
    fun getSplashState(): SplashState
    fun setSplashState(splashState: SplashState)
}
