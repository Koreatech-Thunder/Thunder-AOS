package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AlarmSettingViewModel @Inject constructor() : ViewModel() {
    private val _alarms: MutableStateFlow<List<Boolean>> =
        MutableStateFlow(listOf(false, false, false))
    val alarms = _alarms.asStateFlow()

    fun clickAlarm(index: Int) {
        _alarms.value = alarms.value.mapIndexed { idx, isChecked ->
            if (idx == index) !isChecked
            else isChecked
        }
    }
}
