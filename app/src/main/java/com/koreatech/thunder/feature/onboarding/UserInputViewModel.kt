package com.koreatech.thunder.feature.onboarding

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class UserInputViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ViewModel() {
    private val _hashtags: MutableStateFlow<List<SelectableHashtag>> = MutableStateFlow(emptyList())
    private val _nickname: MutableStateFlow<String> = MutableStateFlow("")
    val hashtags = _hashtags.asStateFlow()
    val nickname = _nickname.asStateFlow()

    init {
        _hashtags.value = getAllSelectableHashtagUseCase()
    }

    fun selectHashtag(index: Int) {
        _hashtags.value = _hashtags.value.mapIndexed { idx, selectableHashtag ->
            if (idx == index) selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
            else selectableHashtag
        }
    }

    fun writeNickname(nickname: String) {
        _nickname.value = nickname
    }

    private fun selectedHashtags(): List<String> =
        mutableListOf<String>().apply {
            hashtags.value.forEach { selectedHashtag ->
                if (selectedHashtag.isSelected) add(
                    selectedHashtag.hashtag.name
                )
            }
        }
}
