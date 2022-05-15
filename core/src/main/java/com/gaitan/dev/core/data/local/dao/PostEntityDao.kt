package com.gaitan.dev.core.data.local.dao

import androidx.room.*
import com.gaitan.dev.core.data.local.entities.PostEntity
import com.gaitan.dev.core.data.local.entities.UserPostRelation
import com.gaitan.dev.core.utils.Constants

@Dao
interface PostEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: List<PostEntity>)

    @Query("SELECT * FROM ${Constants.POST_TABLE}")
    suspend fun getPosts(): List<PostEntity>

    @Transaction
    @Query("SELECT * FROM ${Constants.POST_TABLE} AS PST " +
            "INNER JOIN ${Constants.USER_TABLE} AS UT ON UT.ID = PST.userId " +
            "WHERE PST.userId=:userId ")
    suspend fun getPostByUserId(userId:Int): UserPostRelation


    @Transaction
    @Query("SELECT COUNT(*) FROM ${Constants.POST_TABLE} AS PST " +
            "INNER JOIN ${Constants.USER_TABLE} AS UT ON UT.ID = PST.userId " +
            "WHERE PST.userId=:userId ")
    suspend fun countPostByUser(userId:Int): Int

    @Transaction
    @Query("SELECT * FROM ${Constants.POST_TABLE} " +
            " WHERE userId=:userId ")
    suspend fun getPostByUserIdCode(userId:Int): List<PostEntity>
}