package com.ulternativetechnology.kotlinwithkointest.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

open class BaseViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler {
        coroutineContext, throwable -> throwable.printStackTrace()
    }
    protected val ioDispatchers = Dispatchers.IO + coroutineExceptionHandler
    protected val uiDispatchers = Dispatchers.Main + coroutineExceptionHandler

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}