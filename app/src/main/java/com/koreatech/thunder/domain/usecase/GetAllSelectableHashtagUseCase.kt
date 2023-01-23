package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import javax.inject.Inject

class GetAllSelectableHashtagUseCase @Inject constructor() {
    operator fun invoke(selectable: Boolean = false): List<SelectableHashtag> =
        Hashtag.values().map { hashtag ->
            SelectableHashtag(hashtag, selectable)
        }
}
