package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.model.dummyThunderResponses
import com.koreatech.thunder.data.model.dummyUserResponses
import com.koreatech.thunder.data.source.remote.ThunderDataSource
import com.koreatech.thunder.domain.model.Hashtag.SPORT
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.ThunderRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ThunderRepositoryTest {
    private lateinit var thunderDataSource: ThunderDataSource
    private lateinit var thunderRepository: ThunderRepository

    @BeforeEach
    fun setUp() {
        thunderDataSource = mockk(relaxed = true)
        thunderRepository = ThunderRepositoryImpl(thunderDataSource)
    }

    @DisplayName("번개 리스트 호출한다.")
    @Test
    fun test1() = runBlocking {
        coEvery { thunderDataSource.getThunders() } returns
            dummyThunderResponses
        assertEquals(
            thunderRepository.getThunders(),
            dummyUsers
        )
    }

    @DisplayName("해시태그가 SPORT인 번개 리스트를 호출한다.")
    @Test
    fun test2() = runBlocking {
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(
            dummyThunderResponses[0]
        )
        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(dummyThunders[0])
        )
    }

    @DisplayName("유저의 id를 넣으면 유저의 정보를 가져온다.")
    @Test
    fun test3() = runBlocking {
        coEvery { thunderDataSource.getUser(dummyUserResponses[0].userId) } returns dummyUserResponses[0]
        assertEquals(thunderRepository.getUser(dummyUsers[0].userId), Result.success(dummyUsers[0]))
    }

    @DisplayName("번개를 추가할 수 있다.")
    @Test
    fun postThunderTest_network_success() = runBlocking {
        coEvery { thunderDataSource.getThunders() } returns
            listOf(dummyThunderResponses[0], dummyThunderResponses[1])
        assertNotEquals(
            thunderRepository.getThunders(),
            dummyThunders
        )

        thunderRepository.postThunder(
            title = dummyThunders[2].title,
            content = dummyThunders[2].content,
            limitParticipantsCnt = dummyThunders[2].limitParticipantsCnt,
            hashtags = dummyThunders[2].hashtags,
            deadline = dummyThunders[2].deadline,
            userId = dummyThunders[2].thunderId
        )

        coEvery { thunderDataSource.getThunders() } returns dummyThunderResponses
        assertEquals(
            thunderRepository.getThunders(),
            dummyThunders
        )
    }

    @DisplayName("번개를 참여할 수 있다.")
    @Test
    fun enterThunderTest_network_success() = runBlocking {
        val thunderResponse =
            dummyThunderResponses[0].copy(participants = listOf(dummyUserResponses[0]))
        val afterThunder =
            dummyThunders[0].copy(participants = listOf(dummyUsers[0], dummyUsers[1]))

        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(thunderResponse)
        assertNotEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )

        thunderRepository.enterThunder(dummyThunders[0].thunderId, dummyUsers[1].userId)

        val afterThunderResponse = dummyThunderResponses[0].copy(
            participants = listOf(
                dummyUserResponses[0],
                dummyUserResponses[1]
            )
        )
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns
            listOf(afterThunderResponse)

        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )
    }

    @DisplayName("번개를 취소할 수 있다.")
    @Test
    fun cancelThunderTest_network_success() = runBlocking {
        val thunderResponse =
            dummyThunderResponses[0].copy(
                participants = listOf(
                    dummyUserResponses[0],
                    dummyUserResponses[1]
                )
            )
        val afterThunder =
            dummyThunders[0].copy(participants = listOf(dummyUsers[0]))

        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(thunderResponse)
        assertNotEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )

        thunderRepository.cancelThunder(dummyThunders[0].thunderId, dummyUsers[1].userId)

        val afterThunderResponse =
            dummyThunderResponses[0].copy(
                participants = listOf(
                    dummyUserResponses[0]
                )
            )
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns
            listOf(afterThunderResponse)

        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )
    }
}
