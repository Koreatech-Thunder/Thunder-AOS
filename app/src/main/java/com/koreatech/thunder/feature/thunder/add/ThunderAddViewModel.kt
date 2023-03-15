package com.koreatech.thunder.feature.thunder.add

import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.PostThunderUseCase
import com.koreatech.thunder.feature.thunder.base.ThunderInputViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ThunderAddViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase,
    private val postThunderUseCase: PostThunderUseCase
) : ThunderInputViewModel() {

    init {
        _uiState.value = _uiState.value.copy(hashtags = getAllSelectableHashtagUseCase())
        val currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
        val currentDate = LocalDateTime.now()
        setTime(currentTime.toString())
        setDate(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
    }

    override fun isButtonActive(): Boolean =
        uiState.value.title.isNotEmpty() &&
            uiState.value.content.isNotEmpty() &&
            uiState.value.date.isNotEmpty() &&
            uiState.value.time.isNotEmpty() &&
            isChangeHashtags()

    override fun isChangeHashtags(): Boolean {
        uiState.value.hashtags.forEach { selectableHashtag ->
            if (selectableHashtag.isSelected) return true
        }
        return false
    }

    override fun onClickThunder() {
        viewModelScope.launch {
            postThunderUseCase(
                title = uiState.value.title,
                content = uiState.value.content,
                deadline = "",
                hashtags = uiState.value.hashtags.map { it.hashtag },
                limitParticipantsCnt = uiState.value.limitParticipantsCnt
            )
                .onSuccess { }
                .onFailure { }
        }
    }
}
