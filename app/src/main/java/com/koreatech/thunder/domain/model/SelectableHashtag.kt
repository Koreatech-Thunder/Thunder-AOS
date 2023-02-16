package com.koreatech.thunder.domain.model

data class SelectableHashtag(
    val hashtag: Hashtag,
    val isSelected: Boolean = false
)

fun List<SelectableHashtag>.isContains(otherHashtag: SelectableHashtag): Boolean {
    this.forEach { selectableHashtag ->
        if (selectableHashtag.hashtag == otherHashtag.hashtag) return true
    }
    return false
}

val dummySelectableHashtag = listOf(
    SelectableHashtag(
        Hashtag.SPORT
    ),
    SelectableHashtag(
        Hashtag.MOVIE
    ),
    SelectableHashtag(
        Hashtag.WALK
    )
)
