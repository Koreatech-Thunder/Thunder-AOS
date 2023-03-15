package com.koreatech.thunder.data.model.request

data class UserRequest(
    val name: String,
    val introduction: String,
    val hashtags: List<String>
)
