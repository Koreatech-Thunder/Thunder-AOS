package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.repository.ThunderRepository
import javax.inject.Inject

class PostThunderUseCase @Inject constructor(
    private val thunderRepository: ThunderRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ) = thunderRepository.postThunder(
        title = title,
        content = content,
        hashtags = hashtags,
        limitParticipantsCnt = limitParticipantsCnt,
        deadline = deadline
    )
}
