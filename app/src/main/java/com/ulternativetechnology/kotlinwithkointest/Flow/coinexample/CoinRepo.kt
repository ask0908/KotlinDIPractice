package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

// DI를 사용하지 않아 레포지토리 역할을 해줄 싱글톤을 선언
@ExperimentalCoroutinesApi
object CoinRepo {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://crix-api-endpoint.upbit.com/")
            .client(okhttp)
            .build()
    }
    val webService: CoinService by lazy { retrofit.create(CoinService::class.java) }


    private val okhttp by lazy { OkHttpClient.Builder()
        .addInterceptor(setHttpLoggingInterceptor())
        .build()
    }
}

private fun setHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor { message: String ->
        Log.e(
            "HttpLoggingInterceptor",
            "message : $message"
        )
    }
    return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
}

/* 레트로핏의 Call 값을 CallbackFlow로 변환 */
@ExperimentalCoroutinesApi
fun <T> Call<T>.asCallbackFLow() = callbackFlow<T> {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.e("CoinRepo", "response : $response")
            if (response.isSuccessful) {
                response.body()?.let { trySend(it).isSuccess } ?: close(EmptyBodyError())
            } else {
                Log.e("CoinRepo", "에러 : ${response.errorBody().toString()}")
            }
        }
        override fun onFailure(call: Call<T>, throwable: Throwable) {
            close(FailureError(throwable.message!!))
        }
    })

    awaitClose() //close가 호출될때까지 기다립니다.
}

class EmptyBodyError : Throwable()
class FailureError(message: String): Throwable()
class FailNetworkError(message: String): Exception()