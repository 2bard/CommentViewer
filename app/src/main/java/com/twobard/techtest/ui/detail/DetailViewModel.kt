package com.twobard.techtest.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobard.techtest.data.NetworkError
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.domain.usecase.GetCommentUseCase
import com.twobard.techtest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCommentUseCase: GetCommentUseCase
) : BaseViewModel() {

    val commentId: Int = checkNotNull(savedStateHandle["commentId"])

    val _comment = MutableStateFlow<Comment?>(null)
    val comment: StateFlow<Comment?> = _comment

    init {
        viewModelScope.launch {
                val commentResult = getCommentUseCase.invoke(commentId)
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