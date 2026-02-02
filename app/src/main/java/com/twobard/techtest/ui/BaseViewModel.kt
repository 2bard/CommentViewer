package com.twobard.techtest.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.twobard.techtest.data.repository.NetworkError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errors = MutableSharedFlow<NetworkError?>()
    val errors: SharedFlow<NetworkError?> = _errors

    suspend fun handleError(e: NetworkError?){
        _errors.emit(e)
        log("Network Error", e)
    }

    abstract fun getTag(): String

    fun isLoading(){
        _loading.value = true
    }

    fun finishLoading(){
        _loading.value = false
    }

    fun log(msg: String, e: Throwable?){
        //Note: Would usually call Timber here, or some injected logger class
        Log.e(getTag(), msg, e)
    }
}