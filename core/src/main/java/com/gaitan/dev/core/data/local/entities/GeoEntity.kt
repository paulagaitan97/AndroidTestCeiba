package com.gaitan.dev.core.data.local.entities

import androidx.room.Entity
import com.gaitan.dev.core.utils.Constants

@Entity(tableName = Constants.GEO_TABLE)
data class GeoEntity(
    val lat: String?,
    val lng: String?
)
