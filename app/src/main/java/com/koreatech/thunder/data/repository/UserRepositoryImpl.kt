package com.koreatech.thunder.data.repository

import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUserProfile(): Result<User> {
        TODO("Not yet implemented")
    }
}
