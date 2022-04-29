package com.ulternativetechnology.kotlinwithkointest.koin

import com.ulternativetechnology.kotlinwithkointest.koin.HelloRepository

/* RepositoryImpl에서 만든 메서드의 리턴값을 사용해 가공 */
class MySimplePresenter(private val repo: HelloRepository) {
    fun sayHello() = "${repo.giveHello()} from $this"
}