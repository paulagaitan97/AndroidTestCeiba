package com.gaitan.dev.user_data.remote.api

import com.gaitan.dev.user_data.remote.dto.UserDto
import com.gaitan.dev.user_domain.model.User
import retrofit2.http.GET

interface DetailRemoteUser {
    @GET("/users")
    suspend fun getUsers(): List<UserDto>
}