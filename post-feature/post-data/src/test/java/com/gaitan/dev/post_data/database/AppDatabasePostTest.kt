package com.gaitan.dev.post_data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gaitan.dev.core.data.local.AppDatabase
import com.gaitan.dev.core.data.local.dao.PostEntityDao
import com.gaitan.dev.post_data.source.mapper.toPostEntity
import com.gaitan.dev.post_data.util.mockedPost
import com.gaitan.dev.post_domain.model.Post
import com.google.common.truth.Truth.assertThat
import io.mockk.mockkClass
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabasePostTest: TestCase() {
    private lateinit var db: AppDatabase
    private var postEntityDao = mockkClass( PostEntityDao ::class)

    @Before
    fun setUpDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postEntityDao = db.postDao()
    }

    @Test
    fun `write and read post of the database local, correct`() = runBlocking {
        val postList = listOf<Post>  ( mockedPost )
        val postListEntity = postList.map { it.toPostEntity() }
        postEntityDao.insertPost(postListEntity)
        val postListDao = postEntityDao.getPosts()
        assertThat(postListDao.contains(postListDao[0])).isTrue()
    }


    @After
    fun closeDb(){
        db.close()
    }
}