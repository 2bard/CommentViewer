package com.twobard.techtest.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.twobard.techtest.data.NetworkError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _errors = MutableSharedFlow<NetworkError?>()
    val errors: SharedFlow<NetworkError?> = _errors

    suspend fun handleError(e: NetworkError?){
        _errors.emit(e)
        log("Network Error", e)
    }

    abstract fun getTag(): String

    fun log(msg: String, e: Throwable?){
        //Note: Would usually call Timber here, or some injected log class
        Log.e(getTag(), msg, e)
    }
}