package com.gaitan.dev.user_data.repository

import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.user_data.remote.api.DetailRemoteUser
import com.gaitan.dev.user_data.source.mapper.toUserEntity
import com.gaitan.dev.user_data.source.mapper.toUserModel
import com.gaitan.dev.user_domain.model.User
import com.gaitan.dev.user_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class UserRepositoryImpl(private val api: DetailRemoteUser, val dao: UserEntityDao) :
    UserRepository {
    override suspend fun getUsers(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading(true))

            var localDataUsers = dao.getUsers()
            if(localDataUsers.isEmpty()) {
                try {
                    val responseData = api.getUsers()

                    dao.insertUser(
                        responseData.map { it.toUserEntity() }
                    )
                    localDataUsers = dao.getUsers()

                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Fail("Error Cargando la información"))

                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Fail("Error Cargando la información"))
                }
            }
            emit(Resource.Loading(false))
            emit(Resource.Success(
                data = localDataUsers.map { it.toUserModel() }
            ))
        }
    }

    override suspend fun getUsersByName(name: String): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val localDataUsers = dao.getUsersByName(name)
                emit(Resource.Success(
                    data = localDataUsers.map { it.toUserModel() }
                ))
                emit(Resource.Loading(false))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Fail("Error Cargando la información"))

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Fail("Error Cargando la información"))
            }
        }
    }
}