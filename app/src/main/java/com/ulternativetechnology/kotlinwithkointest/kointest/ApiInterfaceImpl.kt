package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable
import retrofit2.Call

class ApiInterfaceImpl: ApiInterface {
    override fun isRegisteredUser(email: String): Observable<ServerResponse> =
        ApiClient.getApiInterface().isRegisteredUser(email)

    override fun isRegisteredUserForRetrofit(email: String): Call<ServerResponse> =
        ApiClient.getApiInterface().isRegisteredUserForRetrofit(email)

    override fun isRegisteredUserString(email: String): Call<String> =
        ApiClient.getApiInterface().isRegisteredUserString(email)

    override fun isRegisteredUserForFlow(email: String): List<String> =
        ApiClient.getApiInterface().isRegisteredUserForFlow(email)
}