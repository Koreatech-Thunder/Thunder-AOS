package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GetAllSelectableHashtagUseCaseTest : BehaviorSpec({
    given("GetAllSelectableHashtagUseCase 테스트") {
        val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()

        `when`("파라미터가 들어 오지 않았다면") {
            val selectableHashtags = getAllSelectableHashtagUseCase()

            then("모든 hashtag 의 selectable 이 false 인 리스트를 가져올 수 있다") {
                selectableHashtags.size shouldBe Hashtag.values().size
                selectableHashtags.forEach { selectableHashtag ->
                    selectableHashtag.isSelected shouldBe false
                }
            }
        }

        `when`("파라미터로 true 가 들어왔다면") {
            val selectableHashtags = getAllSelectableHashtagUseCase(true)

            then("모든 hashtag 의 selectable 이 true 인 리스트를 가져올 수 있다") {
                selectableHashtags.size shouldBe Hashtag.values().size
                selectableHashtags.forEach { selectableHashtag ->
                    selectableHashtag.isSelected shouldBe true
                }
            }
        }
    }
})
