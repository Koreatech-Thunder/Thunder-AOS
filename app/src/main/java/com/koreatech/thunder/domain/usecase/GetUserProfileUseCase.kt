package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<User> = userRepository.getUserProfile()
}
