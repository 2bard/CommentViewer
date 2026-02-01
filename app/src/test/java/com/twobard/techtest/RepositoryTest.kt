package com.twobard.techtest

import com.twobard.techtest.data.CommentDto
import com.twobard.techtest.data.CommentRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import com.twobard.techtest.data.JsonPlaceholderApiService
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
    fun `given list of CommentDto when getComments() then domain models are valid`() = runTest {

        val dtoList = listOf(
            CommentDto(postId = 1, id = 1, name = "Name1", email = "test@test.com", body = "Hello"),
            CommentDto(postId = 2, id = 2, name = "Name2", email = "test2@test.com", body = "Hello 2"),
        )
        whenever(api.getComments()).thenReturn(dtoList)

        val items = repository.getComments()

        assertEquals(dtoList.size, items.size)
        assertEquals(1, dtoList[0].postId)
        assertEquals(1, dtoList[0].id)
        assertEquals("Name1", dtoList[0].name)
        assertEquals("test@test.com", dtoList[0].email)
        assertEquals("Hello", dtoList[0].body)
    }

}