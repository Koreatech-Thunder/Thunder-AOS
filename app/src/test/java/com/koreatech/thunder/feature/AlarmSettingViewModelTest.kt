package com.koreatech.thunder.feature

import com.koreatech.thunder.feature.profile.AlarmSettingViewModel
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AlarmSettingViewModelTest {
    private lateinit var alarmSettingViewModel: AlarmSettingViewModel

    @BeforeEach
    fun setUp() {
        alarmSettingViewModel = AlarmSettingViewModel()
    }

    @DisplayName("스위치 버튼을 클릭하면 해당 인덱스의 boolean 을 반전시킨다.")
    @Test
    fun clickTest() {
        assertEquals(listOf(false, false, false), alarmSettingViewModel.alarms.value)
        alarmSettingViewModel.clickAlarm(0)
        assertEquals(listOf(true, false, false), alarmSettingViewModel.alarms.value)
    }
}
