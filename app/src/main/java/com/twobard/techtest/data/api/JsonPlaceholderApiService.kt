package com.twobard.techtest.data.api

import com.twobard.techtest.data.dto.CommentDto
import retrofit2.http.GET

interface JsonPlaceholderApiService {
    @GET("comments")
    suspend fun getComments(): List<CommentDto>
}