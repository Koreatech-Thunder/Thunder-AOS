package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.dummySelectableHashtag
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.feature.thunder.HashtagUiState
import com.koreatech.thunder.feature.thunder.ThunderUiState
import com.koreatech.thunder.feature.thunder.ThunderViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ThunderViewModelTest {

    private val thunderRepository: ThunderRepository = mockk()
    private val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()
    private lateinit var thunderViewModel: ThunderViewModel

    @BeforeEach
    fun setUp() {
        thunderViewModel = ThunderViewModel(thunderRepository, getAllSelectableHashtagUseCase)
    }

    @DisplayName("사용자가 하나 이상 해시태그를 설정했고 통신을 성공했다면 사용자의 해시태그를 보여준다")
    @Test
    fun hashtagTest() = runTest {
        val expectedHashtags = dummySelectableHashtag
        coEvery { thunderRepository.getHashtags() } returns Result.success(expectedHashtags)

        assertIs<HashtagUiState.Loading>(thunderViewModel.hashtagUiState.value)
        thunderViewModel.getHashtags()

        val data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)
        assertEquals(data.hashtags, expectedHashtags)
    }

    @DisplayName("사용자가 해시태그를 설정하지 않았으면 모든 해시태그를 보여준다.")
    @Test
    fun hashtagTest2() = runTest {
        val expectedHashtags = getAllSelectableHashtagUseCase()
        coEvery { thunderRepository.getHashtags() } returns Result.success(emptyList())

        thunderViewModel.getHashtags()
        val data = thunderViewModel.hashtagUiState.value

        assertIs<HashtagUiState.Success>(data)
        assertEquals(data.hashtags, expectedHashtags)
    }

    @DisplayName("해시태그를 선택하면 해당 해시태그의 selectable 을 변경한다")
    @Test
    fun hashtagTest3() = runTest {
        coEvery { thunderRepository.getHashtags() } returns Result.success(emptyList())

        thunderViewModel.getHashtags()
        var data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)

        assertEquals(data.hashtags[0].isSelected, false)

        thunderViewModel.selectHashtag(0)

        data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)
        assertEquals(data.hashtags[0].isSelected, true)
    }

    @DisplayName("해시태그는 최대 1개까지 선택할 수 있다")
    @Test
    fun hashtagTest4() = runTest {
        coEvery { thunderRepository.getHashtags() } returns Result.success(emptyList())

        thunderViewModel.getHashtags()
        var data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)

        assertEquals(data.hashtags[0].isSelected, false)

        thunderViewModel.selectHashtag(0)

        data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)
        assertEquals(data.hashtags[0].isSelected, true)

        thunderViewModel.selectHashtag(1)

        data = thunderViewModel.hashtagUiState.value
        assertIs<HashtagUiState.Success>(data)
        assertEquals(data.hashtags[0].isSelected, false)
        assertEquals(data.hashtags[1].isSelected, true)
    }

    @DisplayName("메인 뷰 진입 시 현재 진행 중인 번개를 불러온다.")
    @Test
    fun thunderTest() = runTest {
        val expectedThunders = dummyThunders
        coEvery { thunderRepository.getThunders() } returns Result.success(expectedThunders)

        thunderViewModel.getThunders()
        val data = thunderViewModel.thunderUiState.value

        assertIs<ThunderUiState.Success>(data)
        assertEquals(data.thunders, expectedThunders)
    }

    @DisplayName("특정 번개에서 유저를 클릭했을 때 유저의 정보를 저장한다")
    @Test
    fun userTest() = runTest {
        val expectedUser = dummyThunders[0].participants[1]

        thunderViewModel.setUser(expectedUser)

        val data = thunderViewModel.userInfo.value
        assertEquals(data, expectedUser)
    }
}
