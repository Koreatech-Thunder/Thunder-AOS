package com.koreatech.thunder.feature.evaluate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.repository.EvaluateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EvaluateThunderViewModel @Inject constructor(
    private val evaluateRepository: EvaluateRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EvaluateUiState("", emptyList()))
    val uiState = _uiState.asStateFlow()

    fun setMemberRating(memberIndex: Int, rating: Int) {
        val members = _uiState.value.evaluateMembers.mapIndexed { idx, member ->
            if (idx == memberIndex) _uiState.value.evaluateMembers[memberIndex].copy(rating = rating)
            else member
        }
        _uiState.value = _uiState.value.copy(evaluateMembers = members)
    }

    fun getEvaluateThunder(thunderId: String) {
        viewModelScope.launch {
            evaluateRepository.getEvaluateUsers(thunderId)
                .onSuccess { result ->
                    val evaluateMembers = result.users.map { user ->
                        EvaluateMember(
                            userId = user.userId,
                            nickname = user.name,
                            profile = "",
                            rating = 3
                        )
                    }
                    _uiState.value = EvaluateUiState(
                        result.title,
                        evaluateMembers
                    )
                }
                .onFailure {
                    Timber.e("error ${it.message}")
                }
        }
    }
}

data class EvaluateUiState(
    val title: String,
    val evaluateMembers: List<EvaluateMember>
)

data class EvaluateMember(
    val userId: String,
    val nickname: String,
    val profile: String,
    val rating: Int
)
