package com.twobard.techtest.data

import kotlinx.serialization.Serializable
import com.squareup.moshi.JsonClass
import com.twobard.techtest.domain.repository.Comment

@Serializable
@JsonClass(generateAdapter = true)
data class CommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

fun CommentDto.toDomainModel() = Comment(
    postId = this.postId,
    id = this.id,
    name = this.name,
    email = this.email,
    body = this.body
)