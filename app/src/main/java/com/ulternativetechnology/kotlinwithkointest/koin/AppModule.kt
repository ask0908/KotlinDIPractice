package com.ulternativetechnology.kotlinwithkointest.koin

import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterfaceImpl
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiViewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    /* single : 싱글톤 빈 정의를 제공. 즉 1번만 객체를 생성한다 */
    single<HelloRepository> { HelloRepositoryImpl() }
    single<ApiInterface> { ApiInterfaceImpl() }
    single { ApiInterfaceImpl() }

    // Simple Presenter Factory
    /* factory : 호출될 때마다 객체 생성 */
    factory { MySimplePresenter(get()) }
    /* 아래처럼 써도 작동한다 */
    factory { HelloRepositoryImpl() }
    factory { ApiViewModel(get()) }
}