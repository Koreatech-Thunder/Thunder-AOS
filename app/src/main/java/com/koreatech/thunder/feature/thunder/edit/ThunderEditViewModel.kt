package com.koreatech.thunder.feature.thunder.edit

import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.EditThunderUseCase
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetThunderUseCase
import com.koreatech.thunder.feature.thunder.base.InputUiState
import com.koreatech.thunder.feature.thunder.base.ThunderInputViewModel
import com.koreatech.thunder.navigation.ThunderDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ThunderEditViewModel @Inject constructor(
    private val getThunderUseCase: GetThunderUseCase,
    private val editThunderUseCase: EditThunderUseCase,
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ThunderInputViewModel() {
    private val cacheUiState = MutableStateFlow(InputUiState())
    private val thunderId = MutableStateFlow("")
    private val _moveDestination = MutableSharedFlow<ThunderDestination>()
    val moveDestination = _moveDestination.asSharedFlow()

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
                    val date = thunder.deadline.split(" ")[0].split("-").map { it.toInt() }
                    val time = thunder.deadline.split(" ")[1]
                    setTime(time)
                    setDate(date[0], date[1], date[2])
                    cacheUiState.value = _uiState.value.copy()
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
                deadline = "${formattedText.value} ${hour24FormatTime.value}",
                hashtags = uiState.value.hashtags.filter { it.isSelected }.map { it.hashtag },
                limitParticipantsCnt = uiState.value.limitParticipantsCnt
            )
                .onSuccess {
                    _moveDestination.emit(ThunderDestination.THUNDER)
                }
                .onFailure { }
        }
    }
}
