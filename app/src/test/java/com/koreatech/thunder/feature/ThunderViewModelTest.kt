package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.feature.thunder.HashtagUiState
import com.koreatech.thunder.feature.thunder.ThunderViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
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
        val collectJob = launch { thunderViewModel.hashtagUiState.collect() }
        val data = thunderViewModel.hashtagUiState.value
        val expectedHashtags = listOf(Hashtag.SPORT, Hashtag.MOVIE, Hashtag.WALK)
        coEvery { thunderRepository.getHashtags() } returns Result.success(expectedHashtags)

        assertIs<HashtagUiState.Loading>(data)
        thunderViewModel.getHashtags()

        assertIs<HashtagUiState.Success>(data)
        Assertions.assertEquals(data.hashtags, expectedHashtags)
        collectJob.cancel()
    }

    @DisplayName("사용자가 해시태그를 설정하지 않았으면 모든 해시태그를 보여준다.")
    @Test
    fun hashtagTest2() = runTest {
        val collectJob = launch { thunderViewModel.hashtagUiState.collect() }
        val expectedHashtags = Hashtag.values().toList()
        val data = thunderViewModel.hashtagUiState.value

        thunderViewModel.getHashtags()
        assertIs<HashtagUiState.Success>(data)

        Assertions.assertEquals(data.hashtags, expectedHashtags)
        collectJob.cancel()
    }
}
