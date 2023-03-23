package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class GetAlarmStateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getAlarmStates()
}
