package com.koreatech.thunder.feature.evaluate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EvaluateThunderViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        EvaluateUiState(
            "test test",
            listOf(
                EvaluateMember(nickname = "KWY", profile = "", rating = 3),
                EvaluateMember(nickname = "HSE", profile = "", rating = 3),
                EvaluateMember(nickname = "MSB", profile = "", rating = 3)
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun setMemberRating(memberIndex: Int, rating: Int) {
        val members = _uiState.value.evaluateMembers.mapIndexed { idx, member ->
            if (idx == memberIndex) _uiState.value.evaluateMembers[memberIndex].copy(rating = rating)
            else member
        }
        _uiState.value = _uiState.value.copy(evaluateMembers = members)
    }
}

data class EvaluateUiState(
    val title: String,
    val evaluateMembers: List<EvaluateMember>
)

data class EvaluateMember(
    val nickname: String,
    val profile: String,
    val rating: Int
)
