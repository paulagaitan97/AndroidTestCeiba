package com.gaitan.dev.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaitan.dev.core.utils.Constants

@Entity(tableName = Constants.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    //@Embedded
    //val address: AddressEntity,
    val phone: String,
    val website: String,
   // @Embedded
   // val company: CompanyEntity
)
