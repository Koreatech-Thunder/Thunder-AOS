package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class SetSplashStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(splashState: SplashState) {
        authRepository.setSplashState(splashState)
    }
}
