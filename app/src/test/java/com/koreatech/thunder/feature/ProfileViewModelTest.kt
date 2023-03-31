package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.repository.UserRepository
import com.koreatech.thunder.domain.usecase.*
import com.koreatech.thunder.feature.profile.ProfileViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ProfileViewModelTest {
    private val userRepository: UserRepository = mockk()
    private val authRepository: AuthRepository = mockk()
    private val getUserProfileUseCase = GetUserProfileUseCase(userRepository)
    private val setSplashStateUseCase = SetSplashStateUseCase(authRepository)
    private val withdrawUserUseCase = WithdrawUserUseCase(userRepository)
    private val postLogoutUseCase = PostLogoutUseCase(authRepository)
    private val deleteTokensUseCase = DeleteTokensUseCase(authRepository)
    private lateinit var profileViewModel: ProfileViewModel

    @BeforeEach
    fun setUp() {
        profileViewModel = ProfileViewModel(
            getUserProfileUseCase,
            setSplashStateUseCase,
            withdrawUserUseCase,
            postLogoutUseCase,
            deleteTokensUseCase
        )
    }

    @DisplayName("프로필 뷰 진입 시 유저의 프로필 정보를 불러온다.")
    @Test
    fun profileTest() = runTest {
        coEvery { userRepository.getProfile() } returns Result.success(dummyUsers[0])

        profileViewModel.getUserProfile()

        assertEquals("KWY", profileViewModel.user.value.name)
        assertEquals("컴퓨터 공학부", profileViewModel.user.value.introduction)
        assertEquals(36, profileViewModel.user.value.temperature)
        assertEquals(2, profileViewModel.user.value.hashtags.size)
    }
}
