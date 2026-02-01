package com.twobard.techtest.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobard.techtest.data.NetworkError
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.SortedCommentsUseCase
import com.twobard.techtest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel @Inject constructor(
    val sortedCommentsUseCase: SortedCommentsUseCase
) : BaseViewModel() {

    private val _comments = MutableStateFlow<List<Comment>>(listOf())
    val comments: MutableStateFlow<List<Comment>> = _comments

    init {
        loadComments()
    }

    fun loadComments() {
        viewModelScope.launch {
            val result = sortedCommentsUseCase.invoke()
            result.getOrNull()?.let {
                _comments.value = it
            } ?: run {
                handleError(result.exceptionOrNull() as NetworkError?)
            }
        }
    }


    override fun getTag(): String {
        return "CommentListViewModel"
    }
}