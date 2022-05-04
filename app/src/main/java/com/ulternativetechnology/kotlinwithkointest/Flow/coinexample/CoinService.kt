package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import com.ulternativetechnology.kotlinwithkointest.Flow.UpbitResponse
import com.ulternativetechnology.kotlinwithkointest.Flow.coinexample.jsontest.CoinData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {
    @GET("v1/crix/candles/lines")
    fun loadPrice(
        @Query("code") code: String
    ): Call<UpbitResponse>

    @GET("v1/crix/candles/lines")
    fun loadPriceTest(
        @Query("code") code: String
    ): Call<CoinData>
}