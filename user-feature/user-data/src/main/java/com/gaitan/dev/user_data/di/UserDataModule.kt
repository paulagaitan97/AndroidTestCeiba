package com.gaitan.dev.user_data.di

import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.user_data.remote.api.DetailRemoteUser
import com.gaitan.dev.user_data.repository.UserRepositoryImpl
import com.gaitan.dev.user_domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    fun provideRemoteServiceUser(retrofit: Retrofit): DetailRemoteUser {
       return retrofit.create(DetailRemoteUser::class.java)
    }

    @InternalCoroutinesApi
    @Provides
    @Singleton
    fun provideUserRepository(
        api: DetailRemoteUser,
        @Named("userDao") userDao: UserEntityDao
    ): UserRepository {
        return UserRepositoryImpl(
            api = api,
            dao = userDao
        )
    }
}