package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/user/email/{userEmail}")
    fun isRegisteredUser(
        @Query("userEmail") email: String
    ): Observable<ServerResponse>
}