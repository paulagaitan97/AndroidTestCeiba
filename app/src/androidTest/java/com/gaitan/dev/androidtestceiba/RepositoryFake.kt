package com.gaitan.dev.androidtestceiba

import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.user_data.source.mapper.toUserModel
import com.gaitan.dev.user_domain.model.User
import com.gaitan.dev.user_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class RepositoryFake: UserRepository {
    var shouldReturnError = false
    var getUsersFake = listOf<User>()
    private val getUserFlow = MutableSharedFlow<Resource<List<User>>>(replay = 1)

    override suspend fun getUsers(): Flow<Resource<List<User>>> {
        return getUserFlow
    }

    override suspend fun getUsersByName(name: String): Flow<Resource<List<User>>> {
       return flow {
           if(shouldReturnError) {
               emit(Resource.Fail("Error Cargando la información"))
           }else {
               emit(Resource.Success( data = getUsersFake ) )
           }
       }
    }
}