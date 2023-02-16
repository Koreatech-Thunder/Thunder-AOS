package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.isContains
import javax.inject.Inject

class GetAllSelectableHashtagUseCase @Inject constructor() {
    operator fun invoke(selectable: Boolean = false): List<SelectableHashtag> =
        Hashtag.values().map { hashtag ->
            SelectableHashtag(hashtag, selectable)
        }

    operator fun invoke(hashtags: List<SelectableHashtag>): List<SelectableHashtag> =
        Hashtag.values()
            .map { hashtag ->
                SelectableHashtag(hashtag)
            }
            .map { selectableHashtag ->
                if (hashtags.isContains(selectableHashtag)) selectableHashtag.copy(isSelected = true)
                else selectableHashtag
            }
}
