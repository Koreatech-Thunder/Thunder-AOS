package com.koreatech.thunder.domain.model

data class SelectableHashtag(
    val hashtag: Hashtag,
    val isSelected: Boolean = false
)
