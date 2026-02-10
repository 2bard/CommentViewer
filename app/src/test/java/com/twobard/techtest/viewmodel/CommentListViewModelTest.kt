package com.twobard.techtest.viewmodel

import TestDispatcherProvider
import com.twobard.techtest.di.dispatchers.DispatcherProvider
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import com.twobard.techtest.ui.list.CommentListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE,
    sdk = [33])
class CommentViewModelTest {

    private lateinit var useCase: SortedCommentsUseCase
    private lateinit var viewModel: CommentListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = mock()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a list of comments when UseCase invoked then StateFlow should emit the list`() = runTest {
        val comments = listOf(
            Comment(postId = 1, id = 1, name = "C Comment", email = "test@test.com", body = "Hello 1"),
            Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
            Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3"),
        )

        whenever(useCase.invoke()).thenReturn(Result.success(comments))
        viewModel = CommentListViewModel(useCase, TestDispatcherProvider(StandardTestDispatcher()))
        advanceUntilIdle()

        // Check the state flow emitted the comments
        val emitted = viewModel.comments.first()
        assertEquals(3, emitted.size)
    }
}
