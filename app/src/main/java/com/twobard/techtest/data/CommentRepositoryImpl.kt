package com.twobard.techtest.data

import CommentRepository
import com.twobard.techtest.domain.repository.Comment

class CommentRepositoryImpl(apiService: ItemApiService) : CommentRepository {
    private val api = apiService

    override suspend fun getComments(): List<Comment> {
        return api.getComments().map { it.toDomainModel() }
    }
}