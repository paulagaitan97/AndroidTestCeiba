package com.gaitan.dev.post_data.remote.api

import com.gaitan.dev.post_data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailRemotePost {
    @GET("/posts/?")
    suspend fun getPostByUser(@Query("userId") userId: Int): List<PostDto>
}