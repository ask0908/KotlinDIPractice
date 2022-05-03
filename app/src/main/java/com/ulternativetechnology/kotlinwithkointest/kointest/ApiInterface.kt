package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/user/email/{userEmail}")
    fun isRegisteredUser(
        @Query("userEmail") email: String
    ): Observable<ServerResponse>

    @GET("api/user/email/{userEmail}")
    fun isRegisteredUserForRetrofit(
        @Query("userEmail") email: String
    ): Call<ServerResponse>
}