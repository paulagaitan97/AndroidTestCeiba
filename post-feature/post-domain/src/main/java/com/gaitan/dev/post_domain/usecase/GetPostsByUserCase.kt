package com.gaitan.dev.post_domain.usecase

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.post_domain.model.UserPostModel
import com.gaitan.dev.post_domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsByUserCase (private val postRepository: PostRepository) {
    suspend operator fun invoke(userId: Int): Flow<Resource<UserPostModel>> {
        return postRepository.getPostsByUser(userId)
    }
}