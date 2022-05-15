package com.gaitan.dev.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaitan.dev.core.data.local.dao.PostEntityDao
import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.core.data.local.entities.PostEntity
import com.gaitan.dev.core.data.local.entities.UserEntity

@Database(entities = [
    UserEntity::class,
    PostEntity::class
], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserEntityDao

    abstract fun postDao(): PostEntityDao
}