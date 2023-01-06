package com.koreatech.thunder.domain.entity

data class Thunder(
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<HashTag>,
    val host: User,
    val participants: List<User>,
    val limitParticipantsCnt: Int
)
