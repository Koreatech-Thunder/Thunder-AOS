package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.User

interface UserRepository {
    suspend fun getUserProfile(): Result<User>
}
