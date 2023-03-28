package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.ThunderState

data class ThunderDetailResponse(
    val thunderId: String,
    val title: String,
    val deadline: String,
    val content: String,
    val hashtags: List<String>,
    val limitMembersCnt: Int
) {
    fun toThunder() = Thunder(
        thunderId = thunderId,
        title = title,
        deadline = deadline,
        content = content,
        hashtags = hashtags.map { hashtag -> Hashtag.valueOf(hashtag) },
        limitParticipantsCnt = limitMembersCnt,
        thunderState = ThunderState.HOST,
        participants = emptyList()
    )
}
