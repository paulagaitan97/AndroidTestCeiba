package com.gaitan.dev.post_domain.repository

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.post_domain.model.UserPostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostsByUser(userId: Int): Flow<Resource<UserPostModel>>
}