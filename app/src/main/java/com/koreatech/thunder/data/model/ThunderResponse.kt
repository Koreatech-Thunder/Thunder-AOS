package com.koreatech.thunder.data.model

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder

data class ThunderResponse(
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<String>,
    val host: UserResponse,
    val participants: List<UserResponse>,
    val limitParticipantsCnt: Int
)

fun ThunderResponse.toThunder(): Thunder = Thunder(
    title = title,
    content = content,
    deadline = deadline,
    hashtags = hashtags.map { hashtag -> Hashtag.valueOf(hashtag) },
    host = host.toUser(),
    participants = participants.map { participant -> participant.toUser() },
    limitParticipantsCnt = limitParticipantsCnt
)
