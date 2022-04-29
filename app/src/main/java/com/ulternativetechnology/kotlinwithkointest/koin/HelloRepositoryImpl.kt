package com.ulternativetechnology.kotlinwithkointest.koin

/* 인터페이스의 구현 클래스. 이게 없으면 No ~~ class 에러가 발생한다 */
class HelloRepositoryImpl: HelloRepository {
    override fun giveHello() = "안녕, Koin!"
}