package com.ulternativetechnology.kotlinwithkointest.city

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(myModule)
        }
    }
}