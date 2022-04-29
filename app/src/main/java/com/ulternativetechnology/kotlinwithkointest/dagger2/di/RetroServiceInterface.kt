package com.ulternativetechnology.kotlinwithkointest.dagger2.di

import com.ulternativetechnology.kotlinwithkointest.dagger2.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {
    @GET("repositories")
    fun getDataFromApi(
        @Query("q") query: String
    ): Call<RecyclerList>?
}