package com.twobard.techtest.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.twobard.techtest.data.repository.NetworkError
import com.twobard.techtest.di.dispatchers.DispatcherProvider
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.GetCommentUseCase
import com.twobard.techtest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getCommentUseCase: GetCommentUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel() {

    val commentId: Int = checkNotNull(savedStateHandle["commentId"])

    val _comment = MutableStateFlow<Comment?>(null)
    val comment: StateFlow<Comment?> = _comment

    init {
        loadComment()
    }

    fun loadComment(){
        viewModelScope.launch {

            val commentResult = withContext(dispatcherProvider.io) {
                getCommentUseCase.invoke(commentId)
            }

            commentResult.getOrNull()?.let {
                _comment.value = it
                finishLoading()
            } ?: run {
                finishLoading()
                handleError(commentResult.exceptionOrNull() as NetworkError?)
            }
        }
    }

    override fun getTag(): String {
        return "DetailViewModel"
    }
}