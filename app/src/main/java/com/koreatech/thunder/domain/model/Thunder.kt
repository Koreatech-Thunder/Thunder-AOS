package com.koreatech.thunder.domain.model

data class Thunder(
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<Hashtag>,
    val host: User,
    val participants: List<User>,
    val limitParticipantsCnt: Int
)
