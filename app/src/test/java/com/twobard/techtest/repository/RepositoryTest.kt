package com.twobard.techtest.repository

import com.twobard.techtest.R
import com.twobard.techtest.data.dto.CommentDto
import com.twobard.techtest.data.repository.CommentRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import com.twobard.techtest.data.api.JsonPlaceholderApiService
import com.twobard.techtest.data.repository.NetworkError
import org.mockito.Mockito.doAnswer
import java.io.IOException
import java.net.UnknownHostException
import kotlin.test.assertEquals


class CommentRepositoryTest {

    private lateinit var api: JsonPlaceholderApiService
    private lateinit var repository: CommentRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        repository = CommentRepositoryImpl(api)
    }

    @Test
    fun `given a NoInternet error when getComments() then check NoInternet is returned`() = runTest {
        doAnswer { throw UnknownHostException() }.`when`(api).getComments()

        val result = repository.getComments()

        assert(result.isFailure)
        val res = result.exceptionOrNull() as? NetworkError.NoInternet
        assertEquals(R.string.no_internet, res?.messageRes)
    }

    @Test
    fun `given an IOException error when getComments() then check IOException is returned`() = runTest {
        doAnswer { throw IOException() }.`when`(api).getComments()

        val result = repository.getComments()

        assert(result.isFailure)
        val res = result.exceptionOrNull() as? NetworkError.NetworkFailure
        assertEquals(R.string.network_error, res?.messageRes)
    }

    @Test
    fun `given list of CommentDto when getComments() then domain models are valid`() = runTest {

        val dtoList = listOf(
            CommentDto(postId = 1, id = 1, name = "Name1", email = "test@test.com", body = "Hello"),
            CommentDto(postId = 2, id = 2, name = "Name2", email = "test2@test.com", body = "Hello 2"),
        )
        whenever(api.getComments()).thenReturn(dtoList)

        val items = repository.getComments().getOrThrow()

        assertEquals(dtoList.size, items.size)
        assertEquals(1, dtoList[0].postId)
        assertEquals(1, dtoList[0].id)
        assertEquals("Name1", dtoList[0].name)
        assertEquals("test@test.com", dtoList[0].email)
        assertEquals("Hello", dtoList[0].body)
    }

}