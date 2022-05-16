package com.gaitan.dev.post_domain.di

import com.gaitan.dev.post_domain.repository.PostRepository
import com.gaitan.dev.post_domain.usecase.AllCasesOfPost
import com.gaitan.dev.post_domain.usecase.GetPostsByUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PostsDomainModule {
    @ViewModelScoped
    @Provides
    fun provideAllCasesOfPost(
        postRepository: PostRepository
    ): AllCasesOfPost {
        return AllCasesOfPost(
            getPostsByUserCase = GetPostsByUserCase(postRepository)
        )
    }
}