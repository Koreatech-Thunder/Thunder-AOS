package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.feature.thunder.add.ThunderAddViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import com.koreatech.thunder.util.callPrivateFunc
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ThunderAddViewModelTest {
    private val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()
    private lateinit var thunderAddViewModel: ThunderAddViewModel

    @BeforeEach
    fun setUp() {
        thunderAddViewModel = ThunderAddViewModel(getAllSelectableHashtagUseCase)
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

    @DisplayName("첫 진입 시 모든 해시태그가 들어있고, false 상태이다.")
    @Test
    fun hashtagTest() {
        val hashtags: List<SelectableHashtag> = thunderAddViewModel.hashtags.value

        assertEquals(hashtags.size, Hashtag.values().size)
        hashtags.forEach { hashtag ->
            assertEquals(hashtag.isSelected, false)
        }
    }

    @DisplayName("해시태그는 최대 4개까지 선택할 수 있다.")
    @Test
    fun hashtagTest2() {
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[4].isSelected, false)

        thunderAddViewModel.selectHashtag(0)
        thunderAddViewModel.selectHashtag(1)
        thunderAddViewModel.selectHashtag(2)
        thunderAddViewModel.selectHashtag(3)
        thunderAddViewModel.selectHashtag(4)

        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[4].isSelected, false)
    }

    @DisplayName("선택했던 해시태그를 클릭할 시 true 에서 false 로 변경한다.")
    @Test
    fun hashtagTest3() {
        thunderAddViewModel.selectHashtag(0)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)

        thunderAddViewModel.selectHashtag(0)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, false)
    }

    @DisplayName("해시태그, 제목, 날짜, 시간, 내용을 전부 입력해야 완료 버튼이 활성화 된다.")
    @Test
    fun buttonStateTest() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { thunderAddViewModel.buttonState.collect() }
        assertEquals(thunderAddViewModel.buttonState.value, false)

        thunderAddViewModel.selectHashtag(0)
        thunderAddViewModel.writeTitle("test")
        thunderAddViewModel.writeContent("test")
        thunderAddViewModel.setDate(2023, 2, 18)
        thunderAddViewModel.setTime("오전 9:00")

        assertEquals(thunderAddViewModel.buttonState.value, true)

        collectJob.cancel()
    }

    @DisplayName("`21:00`형식인 24시간 기준으로 시간이 들어올 때 `오후 9:00`이런 형태로 바꿀 수 있다.")
    @Test
    fun timeTextTest() {
        val testTime = "21:00"
        val expectedTime = "오후 9:00"

        thunderAddViewModel.callPrivateFunc("changeToUiTime", testTime)

        assertEquals(thunderAddViewModel.timeUiText.value, expectedTime)
    }

    @DisplayName("번개 생성하기 진입 시 현재 시각으로 초기화 되어 있다.")
    @Test
    fun timeTextTest2() {
        val compareViewModel = ThunderAddViewModel(getAllSelectableHashtagUseCase)
        val currentTime: LocalTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES)

        compareViewModel.callPrivateFunc("changeToUiTime", currentTime.toString())

        assertEquals(thunderAddViewModel.timeUiText.value, compareViewModel.timeUiText.value)
    }

    @DisplayName("년도, 월, 요일이 들어오면 `12.05 목요일`형식으로 바꿀 수 있다")
    @Test
    fun dateTextTest() {
        val expectedDate = "2.18 토요일"

        thunderAddViewModel.callPrivateFunc("changeToUiDate", 2023, 2, 18)

        assertEquals(thunderAddViewModel.dateUiText.value, expectedDate)
    }

    @DisplayName("번개 생성하기 진입 시 현재 날짜로 초기화 되어 있다.")
    @Test
    fun dateTextTest2() {
        val compareViewModel = ThunderAddViewModel(getAllSelectableHashtagUseCase)
        val currentDate = LocalDateTime.now()

        compareViewModel.setDate(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)

        assertEquals(thunderAddViewModel.dateUiText.value, compareViewModel.dateUiText.value)
    }
}
