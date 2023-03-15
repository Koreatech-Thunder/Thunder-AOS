package com.koreatech.thunder.domain.usecase

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class PutUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(name: String, introduction: String, hashtags: List<Hashtag>) =
        userRepository.putUserProfile(name = name, introduction = introduction, hashtags = hashtags)
}
