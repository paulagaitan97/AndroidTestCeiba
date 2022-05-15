package com.gaitan.dev.core.data.local.dao

import androidx.room.*
import com.gaitan.dev.core.data.local.entities.UserEntity
import com.gaitan.dev.core.data.local.entities.UserPostRelation
import com.gaitan.dev.core.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: List<UserEntity>)

    @Query("SELECT * FROM ${Constants.USER_TABLE}")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM ${Constants.USER_TABLE} where LOWER(name) LIKE '%'||LOWER(:searchText)||'%'")
    suspend fun getUsersByName(searchText: String): List<UserEntity>

    @Transaction
    @Query("SELECT * FROM ${Constants.USER_TABLE} WHERE id=:id ")
    fun getUserWithPost(id:Int): Flow<List<UserPostRelation>>

    @Query("SELECT COUNT(*) FROM ${Constants.USER_TABLE}")
    suspend fun getCount(): Int

}