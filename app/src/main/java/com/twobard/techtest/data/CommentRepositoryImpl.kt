package com.twobard.techtest.data

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import jakarta.inject.Inject

class CommentRepositoryImpl @Inject constructor(val apiService: JsonPlaceholderApiService) : CommentRepository {
    private val api = apiService

    override suspend fun getComments(): List<Comment> {
        return api.getComments().map { it.toDomainModel() }
    }
}