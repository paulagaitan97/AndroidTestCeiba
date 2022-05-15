package com.gaitan.dev.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import com.gaitan.dev.core.utils.Constants

@Entity(tableName = Constants.ADDRESS_TABLE)
data class AddressEntity(

    val street: String,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    @Embedded
    val geo: GeoEntity?
)
