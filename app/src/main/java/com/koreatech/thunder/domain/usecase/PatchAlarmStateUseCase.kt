package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class PatchAlarmStateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        alarmStates: List<Boolean>
    ) = userRepository.patchAlarmState(alarmStates)
}
