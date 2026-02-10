package com.twobard.techtest.viewmodel

import TestDispatcherProvider
import androidx.lifecycle.SavedStateHandle
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.GetCommentUseCase
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import com.twobard.techtest.ui.detail.DetailViewModel
import com.twobard.techtest.ui.list.CommentListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
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
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE,
    sdk = [33])
class CommentDetailViewModelTest {

    private lateinit var useCase: GetCommentUseCase

    val commentId = 2

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = mock()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a commentId when loadComment then StateFlow should emit matching comment`() = runTest {

        var viewModel = DetailViewModel(SavedStateHandle(
            mapOf("commentId" to commentId)
        ), useCase, TestDispatcherProvider(StandardTestDispatcher()))

        val comment = Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2")

        whenever(useCase.invoke(commentId)).thenReturn(Result.success(comment))

        advanceUntilIdle()

        val emitted = viewModel.comment.first()

        assertNotNull(emitted)
        assertEquals(commentId, emitted.id)
    }
}
