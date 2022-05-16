package com.gaitan.dev.post_data.source.mapper

import com.gaitan.dev.core.data.local.entities.PostEntity
import com.gaitan.dev.post_data.remote.dto.PostDto
import com.gaitan.dev.post_domain.model.Post

fun PostDto.toPostModel(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}