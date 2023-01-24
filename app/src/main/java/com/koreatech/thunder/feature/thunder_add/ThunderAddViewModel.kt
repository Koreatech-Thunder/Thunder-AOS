package com.koreatech.thunder.feature.thunder_add

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ThunderAddViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ViewModel() {
    private var selectedHashtagCount = 0
    private val _hashtags: MutableStateFlow<List<SelectableHashtag>> = MutableStateFlow(emptyList())
    private val _limitParticipantsCnt = MutableStateFlow(2)
    val limitParticipantsCnt = _limitParticipantsCnt.asStateFlow()
    val hashtags = _hashtags.asStateFlow()

    init {
        _hashtags.value = getAllSelectableHashtagUseCase()
    }

    fun plusLimitParticipantsCnt() {
        _limitParticipantsCnt.value = _limitParticipantsCnt.value + 1
    }

    fun minusLimitParticipantsCnt() {
        if (limitParticipantsCnt.value > 2) {
            _limitParticipantsCnt.value = _limitParticipantsCnt.value - 1
        }
    }

    fun selectHashtag(selectIndex: Int) {
        if (selectedHashtagCount < SELECTABLE_HASHTAG_COUNT || hashtags.value[selectIndex].isSelected) {
            val afterHashtags =
                hashtags.value.mapIndexed { index, selectableHashtag ->
                    if (index == selectIndex) {
                        if (selectableHashtag.isSelected) selectedHashtagCount--
                        else selectedHashtagCount++
                        selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
                    } else selectableHashtag
                }
            _hashtags.value = afterHashtags
        }
    }

    companion object {
        const val SELECTABLE_HASHTAG_COUNT = 4
    }
}
