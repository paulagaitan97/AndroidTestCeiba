package com.gaitan.dev.post_data.source.mapper

import com.gaitan.dev.core.data.local.entities.PostEntity

import com.gaitan.dev.post_domain.model.Post


fun PostEntity.toPostModel(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )

}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}

/**
fun UserPostRelation.toUserPostModel(): UserPostModel {
    return UserPostModel(
        user = User(1,"", "","", "",""),
        posts = posts.map { it.toPostModel() }
    )
}

 **/