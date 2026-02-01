package com.twobard.techtest.domain.usecase

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import javax.inject.Inject

class FilteredCommentsUseCase @Inject constructor(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(): List<Comment> {
        val comments = repository.getComments()
        return comments.sortedBy { it.name } //Sorting by name - spec says 'alphabetical order' - assuming this is name
    }
}