package com.twobard.techtest.data

import retrofit2.http.GET

interface JsonPlaceholderApiService {
    @GET("comments")
    suspend fun getComments(): List<CommentDto>
}