package com.twobard.techtest

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.repository.CommentRepository
import com.twobard.techtest.domain.usecase.GetCommentUseCase
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class GetCommentUseCaseTest : BaseUseCaseTest() {

    private lateinit var useCase: GetCommentUseCase

    @Before
    override fun setup() {
        super.setup()
        useCase = GetCommentUseCase(repository)
    }

    @Test
    fun `given a list of unordered Comments when an ID is provided then return the Comment matching the ID`() = runTest {

        whenever(repository.getComments()).thenReturn(Result.success(testComments()))

        val result = useCase.invoke(1).getOrThrow()

        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("C Comment", result.name)
    }
}
