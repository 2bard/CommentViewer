package com.twobard.techtest.data

import com.twobard.techtest.data.CommentDto
import retrofit2.http.GET

interface ItemApiService {
    @GET("comments")
    suspend fun getComments(): List<CommentDto>
}