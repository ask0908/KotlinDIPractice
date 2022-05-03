package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import com.ulternativetechnology.kotlinwithkointest.Flow.UpbitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {
    @GET("v1/crix/candles/lines")
    fun loadPrice(
        @Query("code") code: String
    ): Call<UpbitResponse>
}