package com.twobard.techtest.data

import com.twobard.techtest.data.CommentDto
import retrofit2.http.GET

interface ItemApiService {
    @GET("items")
    suspend fun getItems(): List<CommentDto>
}