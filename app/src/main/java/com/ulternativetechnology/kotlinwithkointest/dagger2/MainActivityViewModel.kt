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
    @Inject
    lateinit var mService: RetroServiceInterface
    private lateinit var liveDataList: MutableLiveData<RecyclerList>

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