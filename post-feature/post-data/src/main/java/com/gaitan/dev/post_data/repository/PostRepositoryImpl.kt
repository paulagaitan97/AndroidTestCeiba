package com.gaitan.dev.post_data.repository

import com.gaitan.dev.core.data.local.dao.PostEntityDao
import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.post_data.remote.api.DetailRemotePost
import com.gaitan.dev.post_data.source.mapper.toPostEntity
import com.gaitan.dev.post_data.source.mapper.toUserPostModel
import com.gaitan.dev.post_domain.model.UserPostModel
import com.gaitan.dev.post_domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(private val api: DetailRemotePost, val dao: PostEntityDao) :
    PostRepository {

    override suspend fun getPostsByUser(userId: Int): Flow<Resource<UserPostModel>> {
        return flow {
            emit(Resource.Loading(true))

            val localCountPost = dao.countPostByUser(userId)

            var localDataPosts = dao.getPostByUserId(userId)

            if (localCountPost == 0) {
                try {
                    val responseData = api.getPostByUser(userId)

                    dao.insertPost(responseData.map { it.toPostEntity() })
                    localDataPosts = dao.getPostByUserId(userId)

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
                data = localDataPosts.toUserPostModel()
            ))

        }
    }
}

