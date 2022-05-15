package com.gaitan.dev.core.di

import android.app.Application
import androidx.room.Room
import com.gaitan.dev.core.data.remote.api.Contracts
import com.gaitan.dev.core.data.local.AppDatabase
import com.gaitan.dev.core.data.local.dao.PostEntityDao
import com.gaitan.dev.core.data.local.dao.UserEntityDao
import com.gaitan.dev.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Contracts.BASE_ENDPOINT)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : AppDatabase {
        return  Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Named("userDao")
    fun provideUserTransaction(db: AppDatabase) : UserEntityDao {
        return  db.userDao()
    }

    @Provides
    @Named("postDao")
    fun providePostTransaction(db: AppDatabase) : PostEntityDao {
        return  db.postDao()
    }






}