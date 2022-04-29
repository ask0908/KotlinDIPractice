package com.ulternativetechnology.kotlinwithkointest.dagger2.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/* @Provides 메서드를 가진 클래스에 사용. 모든 @Provides 메서드가 이 클래스 안에 있어야 한다
* 이 클래스를 ApiClient로 써도 될 듯 */
@Module
class RetroModule {
    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit): RetroServiceInterface = retrofit.create(RetroServiceInterface::class.java)

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}