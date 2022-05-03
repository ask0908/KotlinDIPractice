package com.ulternativetechnology.kotlinwithkointest.kointest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.ulternativetechnology.kotlinwithkointest.base.BaseViewModel
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class ApiViewModel(private val repo: ApiInterface) : BaseViewModel() {
    val TAG = this.javaClass.simpleName

    // is True = ProgressView VISIBLE , is False = ProgressView GONE
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> get() = _dataLoading

    private val _isRegisteredUserData = MutableLiveData<ServerResponse>()

    private val _foo = MutableLiveData<List<String>>()
    val foo: LiveData<List<String>> get() = _foo

    private val apiRepository = ApiRepository(ApiClient.getApiInterface())

    /* flow */
    fun loadFoo(email: String): MutableLiveData<String> {
        val data = MutableLiveData<String>()
        viewModelScope.launch {
            data.value = apiRepository.getFoo(email)
                .catch { exception -> exception.printStackTrace() }
                .collect { items ->
                    LogUtil.e(TAG, "items : $items")
                    _foo.value = items
                }.toString()
        }
        return data
    }

    /* MainActivity에서 사용 */
    fun isRegisteredUser(email: String): MutableLiveData<ServerResponse> {
        addDisposable(repo.isRegisteredUser(email)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (result!!) {
                        LogUtil.e(TAG, "result가 true : $result");
                    } else {
                        LogUtil.e(TAG, "result가 false : $result");
                    }
                    _isRegisteredUserData.postValue(this)
                }
            }, {
                LogUtil.e(TAG, "error : ${it.printStackTrace()}");
            })
        )
        return _isRegisteredUserData
    }

    fun isRegisteredUserForRetrofit(email: String): MutableLiveData<ServerResponse?> {
        val responses = MutableLiveData<ServerResponse?>()
        _dataLoading.value = true

        viewModelScope.launch(ioDispatchers) {
            try {
                repo.isRegisteredUserForRetrofit(email).enqueue(object : Callback<ServerResponse> {
                    override fun onResponse(
                        call: Call<ServerResponse>,
                        response: Response<ServerResponse>
                    ) {
                        if (response.isSuccessful) {
                            LogUtil.e(TAG, "뷰모델에서 성공 확인 : ${response.body()}");
                            responses.value = response.body()
                        } else {
                            try {
                                LogUtil.e(TAG, "뷰모델에서 실패 확인 : ${response.errorBody()?.string()}");
                            }   catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                        LogUtil.e(TAG, "뷰모델에서 실패 확인 : ${t.message}");
                        responses.value = null
                    }
                })
            }   catch (e: Throwable) {
                LogUtil.e(TAG, "에러 : ${e.message}");
                responses.value = null
            }
        }

        return responses
    }

    fun isRegisteredUserString(email: String): MutableLiveData<String> {
        val stringData = MutableLiveData<String>()

        viewModelScope.launch(ioDispatchers) {
            repo.isRegisteredUserString(email).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        LogUtil.e(TAG, "뷰모델에서 성공 확인 : ${response.body()}");
                        stringData.value = response.body()
                    } else {
                        try {
                            stringData.value = response.errorBody()?.string()
                        }   catch (e: IOException) {
                            LogUtil.e(TAG, "실패!! ${e.message}");
                        }
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    LogUtil.e(TAG, "뷰모델에서 에러 확인 : ${t.message}");
                }
            })
        }

        return stringData
    }

}