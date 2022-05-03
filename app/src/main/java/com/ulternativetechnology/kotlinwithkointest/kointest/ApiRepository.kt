package com.ulternativetechnology.kotlinwithkointest.kointest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository(private val api: ApiInterface) {
    fun getFoo(email: String): Flow<List<String>> {
        return flow {
            val fooList = api.isRegisteredUserForFlow(email)
            emit(fooList)
        }.flowOn(Dispatchers.IO)
    }

    fun getFooAlternative(email: String): Flow<String> {
        return api.isRegisteredUserForFlow(email)
            .asFlow()
            .flowOn(Dispatchers.IO)
    }
}