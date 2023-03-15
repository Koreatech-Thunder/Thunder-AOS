package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class PostLogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.postLogout()
}
