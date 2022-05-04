package com.ulternativetechnology.kotlinwithkointest.city

import org.koin.dsl.module

var myModule = module {
    single<SleepTimeUseCase> { SleepTimeUseCase() }
    single<CityRepository> { CityRepository() }
    single<CityUseCase> { CityUseCase(get()) }
}