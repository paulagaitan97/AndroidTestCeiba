package com.gaitan.dev.user_data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gaitan.dev.core.data.local.AppDatabase
import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.user_data.source.mapper.toUserEntity
import com.gaitan.dev.user_data.util.mockedUser
import com.gaitan.dev.user_domain.model.User
import com.google.common.truth.Truth.assertThat
import io.mockk.mockkClass
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest: TestCase() {
    private lateinit var db: AppDatabase
    private var userEntityDao = mockkClass( UserEntityDao ::class)

    @Before
    fun setUpDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userEntityDao = db.userDao()
    }

    @Test
    fun `write and read user of the database local, correct`() = runBlocking {
        val userList = listOf<User>  ( mockedUser )
        val userListEntity = userList.map { it.toUserEntity() }
        userEntityDao.insertUser(userList.map { it.toUserEntity() })
        val userListDao = userEntityDao.getUsers()
        assertThat(userListDao.contains(userListEntity[0])).isTrue()
    }


    @After
    fun closeDb(){
        db.close()
    }
}