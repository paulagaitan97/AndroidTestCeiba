package com.gaitan.dev.post_presentation.transactions

import com.gaitan.dev.post_domain.model.User
import com.gaitan.dev.post_domain.model.UserPostModel


data class PostViewState(
    val userId: Int = 0,
    val postsByUser: UserPostModel =
        UserPostModel(
        user = User(0, "", "", "", "", ""),
            posts = emptyList()
        ),
    val isLoading: Boolean = false,
)
