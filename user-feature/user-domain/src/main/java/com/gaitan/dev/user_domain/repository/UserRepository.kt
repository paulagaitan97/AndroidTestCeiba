package com.gaitan.dev.user_domain.repository

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.user_domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(): Flow<Resource<List<User>>>
    suspend fun getUsersByName(name:String): Flow<Resource<List<User>>>
}