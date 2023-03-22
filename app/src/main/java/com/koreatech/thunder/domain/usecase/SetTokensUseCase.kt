package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class SetTokensUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(accessToken: String, refreshToken: String) {
        authRepository.setTokens(accessToken, refreshToken)
    }
}
