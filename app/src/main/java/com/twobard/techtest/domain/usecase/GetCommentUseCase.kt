package com.twobard.techtest.domain.usecase

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import javax.inject.Inject

class GetCommentUseCase @Inject constructor(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(id: Int): Result<Comment?> {
        val comment = repository.getComments().map {
            it.first { it.id == id }
        }
        return comment
    }
}