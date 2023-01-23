package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetAllSelectableHashtagUseCaseTest {
    private val useCase = GetAllSelectableHashtagUseCase()

    @DisplayName("모든 hashtag 의 selectable 이 false 인 리스트를 가져올 수 있다")
    @Test
    fun useCaseTest1() {
        val list = useCase()
        assertEquals(Hashtag.values().size, list.size)
        list.forEach { selectableHashtag ->
            assertEquals(false, selectableHashtag.isSelected)
        }
    }

    @DisplayName("모든 hashtag 의 selectable 이 true 인 리스트를 가져올 수 있다")
    @Test
    fun useCaseTest2() {
        val list = useCase(true)
        assertEquals(Hashtag.values().size, list.size)
        list.forEach { selectableHashtag ->
            assertEquals(true, selectableHashtag.isSelected)
        }
    }
}
