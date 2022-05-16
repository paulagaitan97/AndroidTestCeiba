package com.gaitan.dev.user_data.source.mapper

import com.gaitan.dev.core.data.local.entities.UserEntity
import com.gaitan.dev.user_domain.model.User

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