package com.twobard.techtest

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals


class FilteredCommentsUseCaseTest {

    private lateinit var repository: CommentRepository
    private lateinit var useCase: SortedCommentsUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = SortedCommentsUseCase(repository)
    }

    @Test
    fun `given a list of unordered Comments when UseCase invoked then Comments should be ordered alphabetically`() = runTest {
        val comments = listOf(
            Comment(postId = 1, id = 1, name = "C Comment", email = "test@test.com", body = "Hello 1"),
            Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
            Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3"),
        )
        whenever(repository.getComments()).thenReturn(comments)

        val result = useCase.invoke()

        assertEquals(3, result.size)

        //Check sort order, should be A, B, C
        assertEquals("A Comment", result[0].name)
        assertEquals("B Comment", result[1].name)
        assertEquals("C Comment", result[2].name)
    }
}
