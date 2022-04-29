package com.ulternativetechnology.kotlinwithkointest.kointest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(setHttpLoggingInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://dev-jstock.ulternativetechnology.com/")
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun setHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message: String ->
            LogUtil.e(
                "HttpLoggingInterceptor",
                "message : $message"
            )
        }
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun getApiInterface(): ApiInterface = retrofit.create(ApiInterface::class.java)
}