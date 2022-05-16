package com.gaitan.dev.post_data.util
import com.gaitan.dev.post_domain.model.Post
import com.gaitan.dev.post_domain.model.User
import com.gaitan.dev.post_domain.model.UserPostModel

val mockedPost = Post(userId = 1, id = 1, "Title of the post user", "Description post")
val mockedPostRelation = UserPostModel(
    user = User(0, "Paula Gaitan", "pgaitan", "paulagaiitan@gmail.com", "3057740877", ""),
    posts = listOf(
        mockedPost
    )
)

val mockedUserIdCode = 1

val mockError = "Error Cargando la información"