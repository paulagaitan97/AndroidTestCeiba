package com.gaitan.dev.post_data.di

import com.gaitan.dev.core.data.local.dao.PostEntityDao
import com.gaitan.dev.post_data.remote.api.DetailRemotePost
import com.gaitan.dev.post_data.repository.PostRepositoryImpl
import com.gaitan.dev.post_domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataModule {

    @Provides
    fun provideRemoteServicePost(retrofit: Retrofit): DetailRemotePost {
        return retrofit.create(DetailRemotePost::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: DetailRemotePost,
        @Named("postDao") postDao: PostEntityDao
    ): PostRepository{
        return PostRepositoryImpl(
            api = api,
            dao = postDao
        )
    }

}