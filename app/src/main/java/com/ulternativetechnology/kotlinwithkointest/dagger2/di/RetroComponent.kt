package com.ulternativetechnology.kotlinwithkointest.dagger2.di

import com.ulternativetechnology.kotlinwithkointest.dagger2.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}