package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.UserResponse
import com.koreatech.thunder.data.source.remote.ThunderDataSource
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Hashtag.SPORT
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.repository.ThunderRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ThunderRepositoryTest {
    private val user1 = User(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf(SPORT, Hashtag.HEALTH)
    )
    private val user2 = User(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.MOVIE, Hashtag.WALK)
    )
    private val user3 = User(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.CALLVAN, Hashtag.EAT)
    )
    private val thunder1 = Thunder(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(SPORT),
        participants = listOf(user1, user2, user3),
        host = user1,
        limitParticipantsCnt = 8
    )
    private val thunder2 = Thunder(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(Hashtag.HEALTH),
        participants = listOf(user1, user2, user3),
        host = user2,
        limitParticipantsCnt = 3
    )
    private val thunder3 = Thunder(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf(Hashtag.MOVIE),
        participants = listOf(user3),
        host = user3,
        limitParticipantsCnt = 4
    )
    private val userResponse1 = UserResponse(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf("SPORT", "HEALTH")
    )
    private val userResponse2 = UserResponse(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf("MOVIE", "WALK")
    )
    private val userResponse3 = UserResponse(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf("CALLVAN", "EAT")
    )
    private val thunderResponse1 = ThunderResponse(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("SPORT"),
        participants = listOf(userResponse1, userResponse2, userResponse3),
        host = userResponse1,
        limitParticipantsCnt = 8
    )
    private val thunderResponse2 = ThunderResponse(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("HEALTH"),
        participants = listOf(userResponse1, userResponse2, userResponse3),
        host = userResponse2,
        limitParticipantsCnt = 3
    )
    private val thunderResponse3 = ThunderResponse(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf("MOVIE"),
        participants = listOf(userResponse3),
        host = userResponse3,
        limitParticipantsCnt = 4
    )
    private lateinit var thunderDataSource: ThunderDataSource
    private lateinit var thunderRepository: ThunderRepository

    @BeforeEach
    fun setUp() {
        thunderDataSource = mockk(relaxed = true)
        thunderRepository = ThunderRepositoryImpl(thunderDataSource)
    }

    @Test
    fun getThundersTest_network_success() = runBlocking {
        coEvery { thunderDataSource.getThunders() } returns
            listOf(thunderResponse1, thunderResponse2)
        assertEquals(
            thunderRepository.getThunders(),
            Result.success(listOf(thunder1, thunder2))
        )
    }

    @Test
    fun getHashTaggedThundersTest_param_SPORT_network_success() = runBlocking {
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(thunderResponse1)
        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(listOf(thunder1))
        )
    }

    @Test
    fun getUserTest_network_success() = runBlocking {
        coEvery { thunderDataSource.getUser("KWY") } returns userResponse1
        assertEquals(thunderRepository.getUser("KWY"), Result.success(user1))
    }

    @Test
    fun postThunderTest_network_success() = runBlocking {
        coEvery { thunderDataSource.getThunders() } returns
            listOf(thunderResponse1, thunderResponse2)
        assertNotEquals(
            thunderRepository.getThunders(),
            Result.success(listOf(thunder1, thunder2, thunder3))
        )

        thunderRepository.postThunder(
            title = thunder3.title,
            content = thunder3.content,
            limitParticipantsCnt = thunder3.limitParticipantsCnt,
            hashtags = thunder3.hashtags,
            deadline = thunder3.deadline,
            userId = thunder3.thunderId
        )

        coEvery { thunderDataSource.getThunders() } returns
            listOf(thunderResponse1, thunderResponse2, thunderResponse3)
        assertEquals(
            thunderRepository.getThunders(),
            Result.success(listOf(thunder1, thunder2, thunder3))
        )
    }

    @Test
    fun enterThunderTest_network_success() = runBlocking {
        val thunderResponse = thunderResponse1.copy(participants = listOf(userResponse1))
        val afterThunder = thunder1.copy(participants = listOf(user1, user2))

        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(thunderResponse)
        assertNotEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )

        thunderRepository.enterThunder(thunder1.thunderId, user2.userId)

        val afterThunderResponse =
            thunderResponse1.copy(participants = listOf(userResponse1, userResponse2))
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns
            listOf(afterThunderResponse)

        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )
    }

    @Test
    fun cancelThunderTest_network_success() = runBlocking {
        val thunderResponse =
            thunderResponse1.copy(participants = listOf(userResponse1, userResponse2))
        val afterThunder = thunder1.copy(participants = listOf(user1))

        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns listOf(thunderResponse)
        assertNotEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )

        thunderRepository.cancelThunder(thunder1.thunderId, user2.userId)

        val afterThunderResponse =
            thunderResponse1.copy(participants = listOf(userResponse1))
        coEvery { thunderDataSource.getHashTaggedThunders("SPORT") } returns
            listOf(afterThunderResponse)

        assertEquals(
            thunderRepository.getHashTaggedThunders(SPORT),
            Result.success(afterThunder)
        )
    }
}
