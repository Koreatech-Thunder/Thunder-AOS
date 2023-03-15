package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.repository.ThunderRepository
import javax.inject.Inject

class GetThunderUseCase @Inject constructor(
    private val thunderRepository: ThunderRepository
) {
    suspend operator fun invoke(thunderId: String) =
        thunderRepository.getThunder(thunderId = thunderId)
}
