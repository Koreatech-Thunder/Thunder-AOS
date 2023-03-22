package com.koreatech.thunder.feature.thunder.edit

import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.EditThunderUseCase
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetThunderUseCase
import com.koreatech.thunder.feature.thunder.base.InputUiState
import com.koreatech.thunder.feature.thunder.base.ThunderInputViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThunderEditViewModel @Inject constructor(
    private val getThunderUseCase: GetThunderUseCase,
    private val editThunderUseCase: EditThunderUseCase,
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ThunderInputViewModel() {
    private val cacheUiState = MutableStateFlow(InputUiState())
    private val thunderId = MutableStateFlow("")

    init {
        val dummyInputUiState = InputUiState(
            limitParticipantsCnt = 5,
            hashtags = listOf(SelectableHashtag(Hashtag.SPORT, true)),
            selectedHashtagCount = 1,
            title = "수정 테스트",
            content = "수정 테스트 content",
            time = "21:00"
        )
        _uiState.update { uiState ->
            uiState.copy(
                limitParticipantsCnt = dummyInputUiState.limitParticipantsCnt,
                hashtags = getAllSelectableHashtagUseCase(dummyInputUiState.hashtags),
                title = dummyInputUiState.title,
                content = dummyInputUiState.content,
                selectedHashtagCount = dummyInputUiState.hashtags.size
            )
        }
        setTime(dummyInputUiState.time)
        setDate(2023, 2, 18)
        cacheUiState.value = _uiState.value.copy()
    }

    fun getThunder(thunderId: String) {
        viewModelScope.launch {
            getThunderUseCase(thunderId)
                .onSuccess { thunder ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            limitParticipantsCnt = thunder.limitParticipantsCnt,
                            hashtags = getAllSelectableHashtagUseCase(
                                thunder.hashtags.map {
                                    SelectableHashtag(
                                        it
                                    )
                                }
                            ),
                            title = thunder.title,
                            content = thunder.content,
                            selectedHashtagCount = thunder.hashtags.size
                        )
                    }
                }
                .onFailure { }
        }
    }

    override fun isButtonActive(): Boolean =
        uiState.value.title != cacheUiState.value.title ||
            uiState.value.content != cacheUiState.value.content ||
            uiState.value.date != cacheUiState.value.date ||
            uiState.value.time != cacheUiState.value.time ||
            uiState.value.limitParticipantsCnt != cacheUiState.value.limitParticipantsCnt ||
            isChangeHashtags()

    override fun isChangeHashtags(): Boolean {
        uiState.value.hashtags.forEachIndexed { idx, selectableHashtag ->
            if (cacheUiState.value.hashtags[idx].isSelected != selectableHashtag.isSelected) return true
        }
        return false
    }

    override fun onClickThunder() {
        viewModelScope.launch {
            editThunderUseCase(
                thunderId = thunderId.value,
                title = uiState.value.title,
                content = uiState.value.content,
                deadline = "",
                hashtags = uiState.value.hashtags.filter { it.isSelected }.map { it.hashtag },
                limitParticipantsCnt = uiState.value.limitParticipantsCnt
            )
                .onSuccess { }
                .onFailure { }
        }
    }
}
