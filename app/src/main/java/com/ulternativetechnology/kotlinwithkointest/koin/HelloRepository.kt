package com.ulternativetechnology.kotlinwithkointest.koin

/* DI로 가져와서 사용할 메서드의 추상 메서드를 정의한 인터페이스 */
interface HelloRepository {
    fun giveHello(): String
}