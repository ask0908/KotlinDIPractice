package com.ulternativetechnology.kotlinwithkointest.kointest

import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

class ApiInterfaceImpl: ApiInterface {
    override fun isRegisteredUser(email: String): Observable<ServerResponse> =
        ApiClient.getApiInterface().isRegisteredUser(email)

    override fun isRegisteredUserForFlow(email: String): Flow<ServerResponse> =
        ApiClient.getApiInterface().isRegisteredUserForFlow(email)
}