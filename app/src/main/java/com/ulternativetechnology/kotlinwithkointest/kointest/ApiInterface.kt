package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/* suspend 키워드를 사용할 때 Retrofit 2.6.0 이상에서 진행하지 않으면 에러가 발생한다 */
interface ApiInterface {
    @GET("api/user/email/{userEmail}")
    fun isRegisteredUser(
        @Query("userEmail") email: String
    ): Observable<ServerResponse>

    @GET("api/user/email/{userEmail}")
    fun isRegisteredUserForRetrofit(
        @Query("userEmail") email: String
    ): Call<ServerResponse>

    @GET("api/user/email/{userEmail}")
    fun isRegisteredUserString(
        @Query("userEmail") email: String
    ): Call<String>

    @GET("api/user/email/{userEmail}")
    fun isRegisteredUserForFlow(
        @Query("userEmail") email: String
    ): List<String>
}