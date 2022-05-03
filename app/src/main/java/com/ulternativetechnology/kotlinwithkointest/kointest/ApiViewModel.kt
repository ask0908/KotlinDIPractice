package com.ulternativetechnology.kotlinwithkointest.kointest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ulternativetechnology.kotlinwithkointest.base.BaseViewModel
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiViewModel(private val repo: ApiInterface) : BaseViewModel() {
    val TAG = this.javaClass.simpleName

    private val repos by lazy {
        viewModelScope.async(Dispatchers.IO) {
            repo.isRegisteredUserForRetrofit(email = "")
        }
    }

    val list = MutableLiveData<ArrayList<ServerResponse>>().apply { value = arrayListOf() }
    val emptyList: LiveData<Boolean> get() = Transformations.map(list) {
        it.isEmpty()
    }

    // is True = ProgressView VISIBLE , is False = ProgressView GONE
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> get() = _dataLoading

    private val _isRegisteredUserData = MutableLiveData<ServerResponse>()
    val isRegisteredUserData: LiveData<ServerResponse>
        get() = _isRegisteredUserData

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

    fun isRegisteredUserForRetrofit(email: String) {
        _dataLoading.value = true
        list.value?.clear()

        viewModelScope.launch(ioDispatchers) {
            try {
                repo.isRegisteredUserForRetrofit(email).enqueue(object : Callback<ServerResponse> {
                    override fun onResponse(
                        call: Call<ServerResponse>,
                        response: Response<ServerResponse>
                    ) {
                        LogUtil.e(TAG, "뷰모델에서 성공 확인 : ${response.body()}");
                    }

                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                        LogUtil.e(TAG, "뷰모델에서 실패 확인 : ${t.message}");
                    }
                })
            }   catch (e: Throwable) {
                LogUtil.e(TAG, "에러 : ${e.message}");
            }
        }
    }

}