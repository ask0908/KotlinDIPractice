package com.ulternativetechnology.kotlinwithkointest.kointest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulternativetechnology.kotlinwithkointest.BaseViewModel
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

class ApiViewModel(private val repo: ApiInterface) : BaseViewModel() {
    val TAG = this.javaClass.simpleName
    private val _isRegisteredUserData = MutableLiveData<ServerResponse>()
    val isRegisteredUserData: LiveData<ServerResponse>
        get() = _isRegisteredUserData

    //    fun test(email: String) = repo.isRegisteredUser(email)
    fun test(email: String): MutableLiveData<ServerResponse> {
        addDisposable(repo.isRegisteredUser(email)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (result) {
                        LogUtil.e(TAG, "test() 성공 : $result");
                    } else {
                        LogUtil.e(TAG, "test() 실패 : $result");
                    }
                    _isRegisteredUserData.postValue(this)
                }
            }, {
                LogUtil.e(TAG, "error : ${it.printStackTrace()}");
            })
        )
        return _isRegisteredUserData
    }
}