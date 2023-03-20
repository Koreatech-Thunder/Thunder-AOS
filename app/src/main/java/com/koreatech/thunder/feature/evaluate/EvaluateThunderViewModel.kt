package com.koreatech.thunder.feature.evaluate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EvaluateThunderViewModel @Inject constructor() : ViewModel()

data class EvaluateMember(
    val nickname: String,
    val profile: String,
    val rating: Int
)
