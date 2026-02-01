package com.twobard.techtest.domain.repository

interface CommentRepository {
    suspend fun getComments(): Result<List<Comment>>
}