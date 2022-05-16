package com.gaitan.dev.user_data.source.mapper

import com.gaitan.dev.core.data.local.entities.UserEntity
import com.gaitan.dev.user_data.remote.dto.UserDto
import com.gaitan.dev.user_domain.model.User

fun UserDto.toUserModel(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}