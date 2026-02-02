package com.twobard.techtest.usecase

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import org.junit.Before
import org.mockito.kotlin.mock

open class BaseUseCaseTest {

    internal lateinit var repository: CommentRepository

    @Before
    open fun setup() {
        repository = mock()
    }

    fun testComments() = listOf(
        Comment(postId = 1, id = 1, name = "C Comment", email = "test@test.com", body = "Hello 1"),
        Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
        Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3"),
    )
}