package com.koreatech.thunder.domain.entity

data class User(
    val userId: String,
    val name: String,
    val introduction: String,
    val temperature: Int,
    val hashtags: List<HashTag>
)
