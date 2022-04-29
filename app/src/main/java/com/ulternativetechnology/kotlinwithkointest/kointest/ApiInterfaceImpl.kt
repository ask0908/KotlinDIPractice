package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable

class ApiInterfaceImpl: ApiInterface {
    override fun isRegisteredUser(email: String): Observable<ServerResponse> =
        ApiClient.getApiInterface().isRegisteredUser(email)
}