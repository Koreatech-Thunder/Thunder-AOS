package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.feature.thunder.HashtagUiState
import com.koreatech.thunder.feature.thunder.ThunderUiState
import com.koreatech.thunder.feature.thunder.ThunderViewModel
import com.koreatech.thunder.feature.thunder.model.HashtagUi
import com.koreatech.thunder.feature.thunder.model.ThunderState
import com.koreatech.thunder.feature.thunder.model.previewThunderUis
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ThunderViewModelTest {
    @MockK
    private lateinit var thunderRepository: ThunderRepository
    private lateinit var thunderViewModel: ThunderViewModel

    @BeforeEach
    fun setUp() {
        thunderViewModel = ThunderViewModel(thunderRepository)
    }

    @DisplayName("서버 통신 결과 해시태그 리스트가 비어있다면 전체 해시태그 리스트를 저장한다.")
    @Test
    fun test1() {
        coEvery { thunderRepository.getHashtags() } returns Result.success(emptyList())
        val expectedHashtagUis = Hashtag.values().map { hashtag -> HashtagUi(hashtag) }

        thunderViewModel.getHashtags()

        assertEquals(
            HashtagUiState.Success(expectedHashtagUis),
            thunderViewModel.hashtagUiState.value
        )
    }

    @DisplayName("서버 통신 결과 해시태그 리스트가 있다면 해당 해시태그 리스트를 저장한다.")
    @Test
    fun test2() {
        coEvery { thunderRepository.getHashtags() } returns
            Result.success(listOf(Hashtag.SPORT, Hashtag.WALK))
        val expectedHashtagUis = listOf(HashtagUi(Hashtag.SPORT), HashtagUi(Hashtag.WALK))

        thunderViewModel.getHashtags()

        assertEquals(
            HashtagUiState.Success(expectedHashtagUis),
            thunderViewModel.hashtagUiState.value
        )
    }

    @DisplayName("서버 통신 결과 통신을 실패 했으면 전체 해시태그 리스트를 저장한다.")
    @Test
    fun test3() {
        coEvery { thunderRepository.getHashtags() } returns Result.failure(Throwable("통신 실패"))
        val expectedHashtagUis = Hashtag.values().map { hashtag -> HashtagUi(hashtag) }

        thunderViewModel.getHashtags()

        assertEquals(
            HashtagUiState.Success(expectedHashtagUis),
            thunderViewModel.hashtagUiState.value
        )
    }

    @DisplayName("전체 번개 리스트를 저장한다.")
    @Test
    fun test4() {
        coEvery { thunderRepository.getThunders() } returns Result.success(dummyThunders)

        thunderViewModel.getThunders()

        assertEquals(
            ThunderUiState.Success(previewThunderUis),
            thunderViewModel.thunderUiState.value
        )
    }

    @DisplayName("유저의 정보를 불러올 수 있다.")
    @Test
    fun test5() {
    }

    @DisplayName("특정 번개를 참여하지 않은 유저는 NON_MEMBER 상태이고 해당 유저가 참여하기를 누르면 MEMBER 상태가 된다.")
    @Test
    fun test6() {
        val enterUser = dummyUsers[1]
        val enterThunder = dummyThunders[0].copy(
            host = dummyUsers[0],
            participants = listOf(dummyUsers[0])
        )
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(enterThunder))

        assertEquals(
            ThunderState.NON_MEMBER,
            (thunderViewModel.thunderUiState.value as ThunderUiState.Success).thunders[0].thunderState
        )

        val afterThunder = enterThunder.copy(participants = listOf(dummyUsers[0], enterUser))
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(afterThunder))

        thunderViewModel.enterThunder(enterThunder.thunderId, enterUser.userId)
        thunderViewModel.getThunders()

        assertEquals(
            ThunderState.MEMBER,
            (thunderViewModel.thunderUiState.value as ThunderUiState.Success).thunders[0].thunderState
        )
    }

    @DisplayName("특정 번개를 참여 중인 유저는 MEMBER 상태이고 해당 유저가 취소하기를 누르면 NON_MEMBER 상태가 된다.")
    @Test
    fun test7() {
        val cancelUser = dummyUsers[1]
        val cancelThunder = dummyThunders[0].copy(
            host = dummyUsers[0],
            participants = listOf(dummyUsers[0], cancelUser)
        )
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(cancelThunder))

        assertEquals(
            ThunderState.MEMBER,
            (thunderViewModel.thunderUiState.value as ThunderUiState.Success).thunders[0].thunderState
        )

        val resultThunder = cancelThunder.copy(participants = listOf(dummyUsers[0]))
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(resultThunder))

        thunderViewModel.cancelThunder(cancelThunder.thunderId, cancelUser.userId)
        thunderViewModel.getThunders()

        assertEquals(
            ThunderState.NON_MEMBER,
            (thunderViewModel.thunderUiState.value as ThunderUiState.Success).thunders[0].thunderState
        )
    }

    @DisplayName("특정 번개를 개최한 유저는 HOST 상태다.")
    @Test
    fun test8() {
        val hostUser = dummyUsers[0]
        val createdThunder = dummyThunders[0].copy(host = hostUser, participants = listOf(hostUser))
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(createdThunder))

        thunderViewModel.getThunders()

        assertEquals(
            ThunderState.HOST,
            (thunderViewModel.thunderUiState.value as ThunderUiState.Success).thunders[0].thunderState
        )
    }

    @DisplayName("특정 해시태그를 클릭하면 번개 리스트가 특정 해시태그를 포함하고 있는 번개 리스트만 보여준다.")
    @Test
    fun test9() {
        coEvery { thunderRepository.getThunders() } returns Result.success(listOf(dummyThunders[0]))

        thunderViewModel.getThunders()

        assertEquals(
            ThunderUiState.Success(listOf(previewThunderUis[0])),
            thunderViewModel.thunderUiState.value
        )
    }
}
