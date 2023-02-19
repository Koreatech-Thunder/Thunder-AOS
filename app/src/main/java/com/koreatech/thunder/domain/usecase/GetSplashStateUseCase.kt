package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class GetSplashStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getSplashState()
}
