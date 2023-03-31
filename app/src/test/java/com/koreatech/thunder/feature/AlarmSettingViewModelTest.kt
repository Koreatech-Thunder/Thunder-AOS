package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.repository.UserRepository
import com.koreatech.thunder.domain.usecase.GetAlarmStateUseCase
import com.koreatech.thunder.domain.usecase.PutAlarmStateUseCase
import com.koreatech.thunder.feature.profile.AlarmSettingViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class AlarmSettingViewModelTest {
    private val userRepository: UserRepository = mockk()
    private lateinit var alarmSettingViewModel: AlarmSettingViewModel
    private val putAlarmStateUseCase = PutAlarmStateUseCase(userRepository)
    private val getAlarmStateUseCase = GetAlarmStateUseCase(userRepository)

    @BeforeEach
    fun setUp() {
        alarmSettingViewModel = AlarmSettingViewModel(putAlarmStateUseCase, getAlarmStateUseCase)
    }

    @DisplayName("스위치 버튼을 클릭하면 해당 인덱스의 boolean 을 반전시킨다.")
    @Test
    fun clickTest() {
        coEvery { userRepository.getAlarmStates() } returns Result.success(
            listOf(
                false,
                false,
                false
            )
        )

        assertEquals(listOf(false, false, false), alarmSettingViewModel.alarms.value)
        alarmSettingViewModel.clickAlarm(0)
        assertEquals(listOf(true, false, false), alarmSettingViewModel.alarms.value)
    }
}
