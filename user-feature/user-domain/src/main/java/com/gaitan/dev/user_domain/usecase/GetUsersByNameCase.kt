package com.gaitan.dev.user_domain.usecase

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.user_domain.model.User
import com.gaitan.dev.user_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersByNameCase (private val userRepository: UserRepository) {
    suspend operator fun invoke(name: String): Flow<Resource<List<User>>> {
        return userRepository.getUsersByName(name)
    }
}