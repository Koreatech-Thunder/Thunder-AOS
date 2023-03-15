package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.UserRepository
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetUserProfileUseCase
import com.koreatech.thunder.feature.profile.ProfileEditViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import com.koreatech.thunder.util.getPrivateProperty
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ProfileEditViewModelTest {
    private val userRepository: UserRepository = mockk()
    private val getUserProfileUseCase = GetUserProfileUseCase(userRepository)
    private val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()
    private lateinit var profileEditViewModel: ProfileEditViewModel

    @BeforeEach
    fun setUp() {
        profileEditViewModel = ProfileEditViewModel(
            getAllSelectableHashtagUseCase,
            getUserProfileUseCase
        )
    }

    @DisplayName("해시태그를 클릭하면 해당 index 에 있는 해시태그의 isSelected 값을 반전시킨다.")
    @Test
    fun hashtagTest2() {
        coEvery { userRepository.getProfile() } returns Result.success(dummyUsers[0])
        profileEditViewModel.getUserProfile()

        assertEquals(profileEditViewModel.user.value.hashtags[0].isSelected, true)

        profileEditViewModel.selectHashtag(0)

        assertEquals(profileEditViewModel.user.value.hashtags[0].isSelected, false)

        profileEditViewModel.selectHashtag(0)

        assertEquals(profileEditViewModel.user.value.hashtags[0].isSelected, true)
    }

    @DisplayName("프로필 수정 뷰 진입 시 유저의 프로필 정보를 불러온다.")
    @Test
    fun profileTest() = runTest {
        coEvery { userRepository.getProfile() } returns Result.success(dummyUsers[0])

        profileEditViewModel.getUserProfile()

        assertEquals("KWY", profileEditViewModel.user.value.name)
        assertEquals("컴퓨터 공학부", profileEditViewModel.user.value.introduction)
        assertEquals(36, profileEditViewModel.user.value.temperature)
        assertEquals(Hashtag.values().size, profileEditViewModel.user.value.hashtags.size)
        assertEquals(true, profileEditViewModel.user.value.hashtags[0].isSelected)
        assertEquals(true, profileEditViewModel.user.value.hashtags[1].isSelected)
        assertEquals(false, profileEditViewModel.user.value.hashtags[2].isSelected)
        assertEquals(false, profileEditViewModel.user.value.hashtags[3].isSelected)
    }

    @DisplayName("처음 유저의 프로필 정보를 저장하고 있다.")
    @Test
    fun profileTest2() {
        coEvery { userRepository.getProfile() } returns Result.success(dummyUsers[0])

        profileEditViewModel.getUserProfile()

        val cacheUser =
            profileEditViewModel.getPrivateProperty<ProfileEditViewModel, MutableStateFlow<User>>("cacheUser")

        assertEquals("KWY", cacheUser?.value!!.name)
        assertEquals("컴퓨터 공학부", cacheUser.value.introduction)
        assertEquals(36, cacheUser.value.temperature)
        assertEquals(Hashtag.values().size, cacheUser.value.hashtags.size)
        assertEquals(true, cacheUser.value.hashtags[0].isSelected)
        assertEquals(true, cacheUser.value.hashtags[1].isSelected)
        assertEquals(false, cacheUser.value.hashtags[2].isSelected)
        assertEquals(false, cacheUser.value.hashtags[3].isSelected)
    }

    @DisplayName("유저의 정보가 하나라도 변경될 때 버튼이 활성화 된다.")
    @Test
    fun buttonStateTest() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { profileEditViewModel.buttonState.collect() }
        coEvery { userRepository.getProfile() } returns Result.success(dummyUsers[0])
        profileEditViewModel.getUserProfile()

        assertEquals(profileEditViewModel.buttonState.value, false)
        profileEditViewModel.selectHashtag(0)
        assertEquals(profileEditViewModel.buttonState.value, true)

        profileEditViewModel.selectHashtag(0)
        assertEquals(profileEditViewModel.buttonState.value, false)

        collectJob.cancel()
    }
}
