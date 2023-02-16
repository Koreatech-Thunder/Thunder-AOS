package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.dummyUsers
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

        `when`("파라미터로 유저의 해시태그 리스트가 들어왔다면") {
            val userHashtags = dummyUsers[0].hashtags
            val selectableHashtags = getAllSelectableHashtagUseCase(userHashtags)

            then("유저의 해시태그 리스트에 포한한 해시태그의 selectable 이 true 이고, 나머지는 false 인 해시태그 리스트를 가져올 수 있다.") {
                selectableHashtags.size shouldBe Hashtag.values().size
                selectableHashtags[0].isSelected shouldBe true
                selectableHashtags[1].isSelected shouldBe true
                selectableHashtags[2].isSelected shouldBe false
                selectableHashtags[3].isSelected shouldBe false
            }
        }
    }
})
