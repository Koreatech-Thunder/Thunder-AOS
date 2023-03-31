package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.repository.UserRepository
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.PutUserProfileUseCase
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.feature.onboarding.UserInputViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import com.koreatech.thunder.util.callPrivateFunc
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class UserInputScreenViewModelTest {
    private val authRepository: AuthRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val setSplashStateUseCase = SetSplashStateUseCase(authRepository)
    private val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()
    private val putUserProfileUseCase = PutUserProfileUseCase(userRepository)
    private lateinit var userInputViewModel: UserInputViewModel

    @BeforeEach
    fun setUp() {
        userInputViewModel = UserInputViewModel(
            getAllSelectableHashtagUseCase,
            setSplashStateUseCase,
            putUserProfileUseCase
        )
    }

    @DisplayName("첫 진입 시 모든 해시태그가 들어있고, false 상태이다.")
    @Test
    fun hashtagTest() {
        val hashtags: List<SelectableHashtag> = userInputViewModel.hashtags.value

        assertEquals(hashtags.size, Hashtag.values().size)
        hashtags.forEach { hashtag ->
            assertEquals(hashtag.isSelected, false)
        }
    }

    @DisplayName("해시태그를 클릭하면 해당 index 에 있는 해시태그의 isSelected 값을 반전시킨다.")
    @Test
    fun hashtagTest2() {
        assertEquals(userInputViewModel.hashtags.value[0].isSelected, false)

        userInputViewModel.selectHashtag(0)

        assertEquals(userInputViewModel.hashtags.value[0].isSelected, true)

        userInputViewModel.selectHashtag(0)

        assertEquals(userInputViewModel.hashtags.value[0].isSelected, false)
    }

    @DisplayName("isSelected 값이 true 인 해시태그 name 들이 담겨있는 리스트를 반환한다.")
    @Test
    fun hashtagTest3() {
        val expectedHashtags = listOf(
            userInputViewModel.hashtags.value[0].hashtag.name,
            userInputViewModel.hashtags.value[1].hashtag.name
        )

        userInputViewModel.selectHashtag(0)
        userInputViewModel.selectHashtag(1)
        val actualHashtags = userInputViewModel.callPrivateFunc("selectedHashtags")

        assertEquals((actualHashtags as List<Hashtag>).size, expectedHashtags.size)
    }
}
