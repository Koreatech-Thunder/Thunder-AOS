package com.koreatech.thunder.feature.thunder_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ThunderAddViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ViewModel() {
    private val _selectedHashtagCount = MutableStateFlow(0)
    private val _hashtags: MutableStateFlow<List<SelectableHashtag>> = MutableStateFlow(emptyList())
    private val _limitParticipantsCnt = MutableStateFlow(2)
    private val _titleText = MutableStateFlow("")
    private val _contentText = MutableStateFlow("")
    private val _dateText = MutableStateFlow("")
    private val _timeUiText = MutableStateFlow("")
    private val _hour24FormatTime = MutableStateFlow("")
    val limitParticipantsCnt = _limitParticipantsCnt.asStateFlow()
    val hashtags = _hashtags.asStateFlow()
    val selectedHashtagCount = _selectedHashtagCount.asStateFlow()
    val titleText = _titleText.asStateFlow()
    val contentText = _contentText.asStateFlow()
    val dateText = _dateText.asStateFlow()
    val timeUiText = _timeUiText.asStateFlow()
    val hour24FormatTime = _hour24FormatTime.asStateFlow()
    val buttonState =
        combine(
            titleText,
            contentText,
            dateText,
            timeUiText
        ) { title, content, date, time ->
            title.isNotEmpty() && content.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && isSelectHashtags()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init {
        _hashtags.value = getAllSelectableHashtagUseCase()
        val currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
        setTime(currentTime.toString())
    }

    private fun isSelectHashtags(): Boolean {
        hashtags.value.forEach { selectableHashtag ->
            if (selectableHashtag.isSelected) return true
        }
        return false
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
        if (selectedHashtagCount.value < SELECTABLE_HASHTAG_COUNT || hashtags.value[selectIndex].isSelected) {
            val afterHashtags =
                hashtags.value.mapIndexed { index, selectableHashtag ->
                    if (index == selectIndex) {
                        if (selectableHashtag.isSelected) _selectedHashtagCount.value -= 1
                        else _selectedHashtagCount.value += 1
                        selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
                    } else selectableHashtag
                }
            _hashtags.value = afterHashtags
        }
    }

    fun writeTitle(title: String) {
        _titleText.value = title
    }

    fun writeContent(content: String) {
        _contentText.value = content
    }

    fun setDate(date: String) {
        _dateText.value = date
    }

    fun setTime(time: String) {
        _hour24FormatTime.value = time
        changeToUiTime(time)
    }

    private fun changeToUiTime(changeTime: String) {
        val (time, minute) = changeTime.split(":")
        if (time.toInt() > 12) {
            _timeUiText.value = "오후 ${time.toInt() - 12}:$minute"
        } else {
            _timeUiText.value = "오전 $changeTime"
        }
    }

    companion object {
        const val SELECTABLE_HASHTAG_COUNT = 4
    }
}
