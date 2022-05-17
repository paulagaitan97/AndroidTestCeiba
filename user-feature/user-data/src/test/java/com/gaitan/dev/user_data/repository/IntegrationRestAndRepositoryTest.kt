package com.gaitan.dev.user_data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gaitan.dev.core.data.local.AppDatabase
import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.user_data.remote.api.DetailRemoteUser
import com.gaitan.dev.user_data.util.validUserResponse
import io.mockk.mockkClass
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class IntegrationRestAndRepositoryTest: TestCase()  {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var repository: UserRepositoryImpl

    private lateinit var userDetailApi: DetailRemoteUser

    private lateinit var db: AppDatabase
    private var userEntityDao = mockkClass( UserEntityDao ::class)



    @Before
    fun setUpInitial() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userEntityDao = db.userDao()

        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        userDetailApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .build().create(DetailRemoteUser::class.java)

        repository = UserRepositoryImpl(
            api = userDetailApi,
            userEntityDao
        )
    }


    @Test
    fun testApiSuccess() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserResponse)
        )

        val response = runBlocking {
            repository.getUsers()

        }

        Assert.assertNotNull(response)
    }

    @After
    fun close(){
        mockWebServer.shutdown()
        db.close()
    }
}
