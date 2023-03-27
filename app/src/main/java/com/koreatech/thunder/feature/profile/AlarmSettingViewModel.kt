package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.usecase.GetAlarmStateUseCase
import com.koreatech.thunder.domain.usecase.PutAlarmStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AlarmSettingViewModel @Inject constructor(
    private val putAlarmStateUseCase: PutAlarmStateUseCase,
    private val getAlarmStateUseCase: GetAlarmStateUseCase
) : ViewModel() {
    private val _alarms: MutableStateFlow<List<Boolean>> =
        MutableStateFlow(listOf(false, false, false))
    val alarms = _alarms.asStateFlow()

    init {
        viewModelScope.launch {
            getAlarmStateUseCase()
                .onSuccess { alarms ->
                    _alarms.value = alarms
                }
                .onFailure {
                    Timber.e("error is ${it.message}")
                }
        }
    }

    fun clickAlarm(index: Int) {
        _alarms.value = alarms.value.mapIndexed { idx, isChecked ->
            if (idx == index) !isChecked
            else isChecked
        }
        patchAlarmStates()
    }

    private fun patchAlarmStates() {
        viewModelScope.launch {
            putAlarmStateUseCase(alarms.value)
                .onFailure {
                    Timber.e("error ${it.message}")
                }
        }
    }
}
