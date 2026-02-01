package com.twobard.techtest.data

class CommentRepositoryImpl(apiService: ItemApiService) : CommentRepository {
    private val api = apiService

    override suspend fun getComments(): List<Comment> {
        return api.getComments().map { dto ->
            Comment(
                id = dto.id,
                name = dto.name,
                email = dto.email,
                body = dto.body
            )
        }
    }
}