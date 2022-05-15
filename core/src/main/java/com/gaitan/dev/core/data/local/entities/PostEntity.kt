package com.gaitan.dev.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaitan.dev.core.utils.Constants

@Entity(tableName = Constants.POST_TABLE)
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId : Int,
    val title: String,
    val body: String
)
