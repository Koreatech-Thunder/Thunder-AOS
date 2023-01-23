package com.koreatech.thunder.feature

import com.koreatech.thunder.feature.thunder_add.ThunderAddViewModel
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ThunderAddViewModelTest {
    private lateinit var thunderAddViewModel: ThunderAddViewModel

    @BeforeEach
    fun setUp() {
        thunderAddViewModel = ThunderAddViewModel()
    }

    @DisplayName("제한 인원은 2명으로 초기화 되어있다.")
    @Test
    fun limitParticipantsCntTest() {
        val count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)
    }

    @DisplayName("제한 인원을 늘릴 수 있다.")
    @Test
    fun limitParticipantsCntTest2() {
        var count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)

        thunderAddViewModel.plusLimitParticipantsCnt()
        count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 3)
    }

    @DisplayName("제한 인원을 줄일 수 있다.")
    @Test
    fun limitParticipantsCntTest3() {
        var count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)

        thunderAddViewModel.plusLimitParticipantsCnt()
        count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 3)

        thunderAddViewModel.minusLimitParticipantsCnt()
        count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)
    }

    @DisplayName("제한 인원이 2명일 땐 줄일 수 없다.")
    @Test
    fun limitParticipantsCntTest4() {
        var count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)

        thunderAddViewModel.minusLimitParticipantsCnt()
        count = thunderAddViewModel.limitParticipantsCnt.value
        assertEquals(count, 2)
    }
}
