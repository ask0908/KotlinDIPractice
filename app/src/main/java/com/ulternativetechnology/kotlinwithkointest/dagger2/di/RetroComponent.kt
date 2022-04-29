package com.ulternativetechnology.kotlinwithkointest.dagger2.di

import com.ulternativetechnology.kotlinwithkointest.dagger2.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

/* 이 인터페이스를 한 번 만들면 Application이 사라질 때까지 재사용(=싱글턴) */
@Singleton
/* 인터페이스에만 사용 가능. Dagger가 이 인터페이스를 구현한 클래스를 자동 생성함
* Component를 구성하는 모든 @Module 클래스 목록을 써야 해서 () 안에 modules로 클래스 선언 */
@Component(modules = [RetroModule::class])
/* ApiInterface */
interface RetroComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}