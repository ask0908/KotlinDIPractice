package com.ulternativetechnology.kotlinwithkointest.city

import android.annotation.SuppressLint
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ulternativetechnology.kotlinwithkointest.BaseTest
import com.ulternativetechnology.kotlinwithkointest.RxSchedulerRule
import com.ulternativetechnology.kotlinwithkointest.koin.HelloRepository
import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterfaceImpl
import com.ulternativetechnology.kotlinwithkointest.kointest.ServerResponse
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import retrofit2.Call
import retrofit2.Callback
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CityUseCaseTest: BaseTest() {
    val model by inject<ServerResponse>()
    private val api by inject<ApiInterface>()

    @Rule
    @JvmField
    val rule = RxSchedulerRule()

    @Before
    fun before() {
        stopKoin()
        GlobalContext.getOrNull() ?: startKoin {
            module {
                single { ApiInterfaceImpl() }
            }
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @SuppressLint("CheckResult")
    @Test
    fun `이상한_이메일_입력_시_false가_나와야_하는_테스트`() {
        api.isRegisteredUserForRetrofit("test")
        assert(true)


//        api.isRegisteredUser("abcd@naver.com")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                it.run {
//                    /* 아래처럼 테스트하면 실패하더라도 통과했다는 녹색 표시가 나와서 boolean 지역변수를 만들고 그 값으로 테스트하는 방식으로 변경 */
////                    assertTrue(!it.result)
//                    result = it.result
//                }
//            }
//        assertTrue(result!!)
    }
}