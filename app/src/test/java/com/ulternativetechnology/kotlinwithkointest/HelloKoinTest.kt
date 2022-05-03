package com.ulternativetechnology.kotlinwithkointest

import android.annotation.SuppressLint
import com.ulternativetechnology.kotlinwithkointest.koin.HelloRepository
import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ServerResponse
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HelloKoinTest: BaseTest() {
    val model by inject<ServerResponse>()
    val service by inject<ApiInterface>()

    @Rule
    @JvmField
    val rule = RxSchedulerRule()

    @SuppressLint("CheckResult")
    @Test
    fun `이상한_이메일_입력_시_false가_나와야_하는_테스트`() {
        var result: Boolean? = null
        service.isRegisteredUser("abcd@naver.com")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.run {
                    /* 아래처럼 테스트하면 실패하더라도 통과했다는 녹색 표시가 나와서 boolean 지역변수를 만들고 그 값으로 테스트하는 방식으로 변경 */
//                    assertTrue(!it.result)
                    result = it.result
                }
            }
        assertTrue(result!!)
    }

}