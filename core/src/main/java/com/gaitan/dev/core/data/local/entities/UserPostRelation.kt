package com.gaitan.dev.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.gaitan.dev.core.data.local.entities.PostEntity
import com.gaitan.dev.core.data.local.entities.UserEntity

data class UserPostRelation(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val posts: List<PostEntity>
)
