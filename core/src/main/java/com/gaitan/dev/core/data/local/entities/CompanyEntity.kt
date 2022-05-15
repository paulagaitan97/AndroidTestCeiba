package com.gaitan.dev.core.data.local.entities

import androidx.room.Entity
import com.gaitan.dev.core.utils.Constants

@Entity(tableName = Constants.COMPANY_TABLE)
data class CompanyEntity(

    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)
