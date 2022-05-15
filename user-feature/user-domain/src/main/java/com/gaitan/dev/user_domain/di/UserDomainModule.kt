package com.gaitan.dev.user_domain.di

import com.gaitan.dev.user_domain.repository.UserRepository
import com.gaitan.dev.user_domain.usecase.AllCasesOfUser
import com.gaitan.dev.user_domain.usecase.GetUsersByNameCase
import com.gaitan.dev.user_domain.usecase.GetUsersCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserDomainModule {
    @ViewModelScoped
    @Provides
    fun provideAllCasesOfUser(
        userRepository: UserRepository
    ): AllCasesOfUser {
        return AllCasesOfUser(
            getUsersCase = GetUsersCase(userRepository),
            getUsersByNameCase = GetUsersByNameCase(userRepository)
        )
    }
}