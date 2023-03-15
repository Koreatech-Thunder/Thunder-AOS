package com.koreatech.thunder.feature.thunder.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.DayOfWeek
import java.time.LocalDate

abstract class ThunderInputViewModel : ViewModel() {
    private val _formattedDate = MutableStateFlow("")
    private val _hour24FormatTime = MutableStateFlow("")
    protected val _uiState = MutableStateFlow(InputUiState())
    val uiState = _uiState.asStateFlow()
    val formattedText = _formattedDate.asStateFlow()
    val hour24FormatTime = _hour24FormatTime.asStateFlow()
    val buttonState = uiState.map {
        isButtonActive()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    protected abstract fun isButtonActive(): Boolean
    protected abstract fun isChangeHashtags(): Boolean
    abstract fun onClickThunder()

    fun plusLimitParticipantsCnt() {
        _uiState.value = _uiState.value.copy(
            limitParticipantsCnt = uiState.value.limitParticipantsCnt + 1
        )
    }

    fun minusLimitParticipantsCnt() {
        if (uiState.value.limitParticipantsCnt > 2) {
            _uiState.value = _uiState.value.copy(
                limitParticipantsCnt = uiState.value.limitParticipantsCnt - 1
            )
        }
    }

    fun selectHashtag(selectIndex: Int) {
        if (uiState.value.selectedHashtagCount < SELECTABLE_HASHTAG_COUNT || uiState.value.hashtags[selectIndex].isSelected) {
            val afterHashtags =
                uiState.value.hashtags.mapIndexed { index, selectableHashtag ->
                    if (index == selectIndex) {
                        if (selectableHashtag.isSelected) _uiState.value =
                            _uiState.value.copy(selectedHashtagCount = uiState.value.selectedHashtagCount - 1)
                        else _uiState.value =
                            _uiState.value.copy(selectedHashtagCount = uiState.value.selectedHashtagCount + 1)
                        selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
                    } else selectableHashtag
                }
            _uiState.value = _uiState.value.copy(
                hashtags = afterHashtags
            )
        }
    }

    fun writeTitle(title: String) {
        _uiState.value = _uiState.value.copy(
            title = title
        )
    }

    fun writeContent(content: String) {
        _uiState.value = _uiState.value.copy(
            content = content
        )
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        changeToUiDate(year, month, dayOfMonth)
        _formattedDate.value = "$year/$month/$dayOfMonth"
    }

    fun setTime(time: String) {
        _hour24FormatTime.value = time
        changeToUiTime(time)
    }

    private fun changeToUiTime(changeTime: String) {
        val (time, minute) = changeTime.split(":")
        if (time.toInt() > 12) {
            _uiState.value = _uiState.value.copy(
                time = "오후 ${time.toInt() - 12}:$minute"
            )
        } else {
            _uiState.value = _uiState.value.copy(
                time = "오전 $changeTime"
            )
        }
    }

    private fun changeToUiDate(year: Int, month: Int, dayOfMonth: Int) {
        val dayOfWeek = when (LocalDate.of(year, month, dayOfMonth).dayOfWeek) {
            DayOfWeek.MONDAY -> "월요일"
            DayOfWeek.TUESDAY -> "화요일"
            DayOfWeek.WEDNESDAY -> "수요일"
            DayOfWeek.THURSDAY -> "목요일"
            DayOfWeek.FRIDAY -> "금요일"
            DayOfWeek.SATURDAY -> "토요일"
            DayOfWeek.SUNDAY -> "일요일"
        }
        _uiState.value = _uiState.value.copy(
            date = "$month.$dayOfMonth $dayOfWeek"
        )
    }

    companion object {
        const val SELECTABLE_HASHTAG_COUNT = 4
    }
}

data class InputUiState(
    val limitParticipantsCnt: Int = 2,
    val hashtags: List<SelectableHashtag> = emptyList(),
    val selectedHashtagCount: Int = 0,
    val title: String = "",
    val content: String = "",
    val date: String = "",
    val time: String = ""
)
