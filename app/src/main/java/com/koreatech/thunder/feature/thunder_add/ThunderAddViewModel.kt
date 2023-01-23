package com.koreatech.thunder.feature.thunder_add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThunderAddViewModel : ViewModel() {
    private val _limitParticipantsCnt = MutableStateFlow(2)
    val limitParticipantsCnt = _limitParticipantsCnt.asStateFlow()

    fun plusLimitParticipantsCnt() {
        _limitParticipantsCnt.value = _limitParticipantsCnt.value + 1
    }

    fun minusLimitParticipantsCnt() {
        if (limitParticipantsCnt.value > 2) {
            _limitParticipantsCnt.value = _limitParticipantsCnt.value - 1
        }
    }
}
