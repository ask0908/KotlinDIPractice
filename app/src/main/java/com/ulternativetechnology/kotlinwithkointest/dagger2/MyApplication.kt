package com.ulternativetechnology.kotlinwithkointest.dagger2

import android.app.Application
import com.ulternativetechnology.kotlinwithkointest.dagger2.di.DaggerRetroComponent
import com.ulternativetechnology.kotlinwithkointest.dagger2.di.RetroComponent
import com.ulternativetechnology.kotlinwithkointest.dagger2.di.RetroModule

class MyApplication: Application() {

    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()

        // DaggerRetroComponent가 import되지 않으면 rebuild ㄱㄱ
        /* @Component의 영향으로 RetroComponent 인터페이스 앞에 "Dagger"가 붙음 */
        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent = retroComponent

}