package com.twobard.techtest.usecase

import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals


class FilteredCommentsUseCaseTest : BaseUseCaseTest() {

    private lateinit var useCase: SortedCommentsUseCase

    @Before
    override fun setup() {
        super.setup()
        useCase = SortedCommentsUseCase(repository)
    }

    @Test
    fun `given a list of unordered Comments when UseCase invoked then Comments should be ordered alphabetically`() = runTest {

        whenever(repository.getComments()).thenReturn(Result.success(testComments()))

        val result = useCase.invoke().getOrThrow()

        assertEquals(3, result.size)

        //Check sort order, should be A, B, C
        assertEquals("A Comment", result[0].name)
        assertEquals("B Comment", result[1].name)
        assertEquals("C Comment", result[2].name)
    }
}
