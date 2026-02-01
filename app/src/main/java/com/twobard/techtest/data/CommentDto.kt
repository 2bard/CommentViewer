package com.twobard.techtest.data

import kotlinx.serialization.Serializable
import com.squareup.moshi.JsonClass

@Serializable
@JsonClass(generateAdapter = true)
data class CommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)