package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.feature.thunder.HashtagUiState
import com.koreatech.thunder.feature.thunder.ThunderViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ThunderViewModelTest {

    private val thunderRepository: ThunderRepository = mockk()
    private lateinit var thunderViewModel: ThunderViewModel

    @BeforeEach
    fun setUp() {
        thunderViewModel = ThunderViewModel(thunderRepository)
    }

    @DisplayName("사용자가 하나 이상 해시태그를 설정했고 통신을 성공했다면 사용자의 해시태그를 보여준다")
    @Test
    fun hashtagTest() = runTest {
        val expectedHashtags = listOf(Hashtag.SPORT, Hashtag.MOVIE, Hashtag.WALK)
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
        val expectedHashtags = Hashtag.values().toList()
        coEvery { thunderRepository.getHashtags() } returns Result.success(emptyList())

        thunderViewModel.getHashtags()
        val data = thunderViewModel.hashtagUiState.value

        assertIs<HashtagUiState.Success>(data)
        Assertions.assertEquals(data.hashtags, expectedHashtags)
    }
}
