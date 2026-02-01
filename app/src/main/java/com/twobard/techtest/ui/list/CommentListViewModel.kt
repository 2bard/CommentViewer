package com.twobard.techtest.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel @Inject constructor(
    val sortedCommentsUseCase: SortedCommentsUseCase
) : ViewModel() {

    private val _comments = MutableStateFlow<List<Comment>>(listOf())
    val comments: MutableStateFlow<List<Comment>> = _comments

    fun loadComments() {
        viewModelScope.launch {
            val result = sortedCommentsUseCase()
            _comments.value = result
        }
    }
}