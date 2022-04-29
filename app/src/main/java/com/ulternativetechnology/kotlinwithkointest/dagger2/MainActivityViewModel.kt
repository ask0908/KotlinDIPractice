package com.ulternativetechnology.kotlinwithkointest.dagger2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ulternativetechnology.kotlinwithkointest.dagger2.di.RetroServiceInterface
import com.ulternativetechnology.kotlinwithkointest.dagger2.model.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    /* @Inject : Dagger에 의존성을 요청하는 어노테이션 */
    @Inject
    lateinit var mService: RetroServiceInterface
    private var liveDataList: MutableLiveData<RecyclerList>

    /* 생성자를 통해 인스턴스가 생성될 때 호출되는 함수. 매개변수, 리턴값이 없음 */
    init {
        (application as MyApplication).getRetroComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<RecyclerList> = liveDataList

    fun makeApiCall() {
        val call: Call<RecyclerList>? = mService.getDataFromApi("atl")
        call?.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }

}