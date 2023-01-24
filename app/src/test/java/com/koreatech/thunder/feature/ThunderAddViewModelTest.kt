package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.feature.thunder_add.ThunderAddViewModel
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
        thunderAddViewModel.selectHashtag(0)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, false)

        thunderAddViewModel.selectHashtag(1)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, false)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, false)

        thunderAddViewModel.selectHashtag(2)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, false)

        thunderAddViewModel.selectHashtag(3)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[1].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[2].isSelected, true)
        assertEquals(thunderAddViewModel.hashtags.value[3].isSelected, true)
    }

    @DisplayName("선택했던 해시태그를 클릭할 시 true 에서 false 로 변경한다.")
    @Test
    fun hashtagTest3() {
        thunderAddViewModel.selectHashtag(0)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, true)

        thunderAddViewModel.selectHashtag(0)
        assertEquals(thunderAddViewModel.hashtags.value[0].isSelected, false)
    }
}
