package com.gaitan.dev.post_data.source.mapper

import com.gaitan.dev.core.data.local.entities.UserEntity
import com.gaitan.dev.core.data.local.entities.UserPostRelation
import com.gaitan.dev.post_domain.model.User
import com.gaitan.dev.post_domain.model.UserPostModel


fun UserEntity.toUserModel(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}

fun UserPostRelation.toUserPostModel(): UserPostModel {
    return UserPostModel(
        user =  user.toUserModel(),
        posts = posts.map { it.toPostModel() }
    )

}

fun UserPostModel.toUserPostRelation(): UserPostRelation {
    return UserPostRelation(
        user = user.toUserEntity(),
        posts = posts.map { it.toPostEntity() }
    )
}