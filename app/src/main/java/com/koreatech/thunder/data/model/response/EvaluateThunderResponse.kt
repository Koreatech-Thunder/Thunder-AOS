package com.koreatech.thunder.data.model.response

import com.google.gson.annotations.SerializedName

data class EvaluateThunderResponse(
    val title: String,
    @SerializedName("userInfo") val users: List<EvaluateUserResponse>
)
