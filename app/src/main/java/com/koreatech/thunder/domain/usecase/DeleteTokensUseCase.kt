package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class DeleteTokensUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.deleteTokens()
}
