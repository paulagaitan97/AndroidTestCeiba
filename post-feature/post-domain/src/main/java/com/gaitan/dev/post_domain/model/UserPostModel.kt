package com.gaitan.dev.post_domain.model

data class UserPostModel(
    val user: User,
    val posts: List<Post>
)
