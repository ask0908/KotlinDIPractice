package com.ulternativetechnology.kotlinwithkointest

import com.ulternativetechnology.kotlinwithkointest.koin.HelloRepository
import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterfaceImpl
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiViewModel
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PostRepositoryTest: AbstractKoinTest() {
    private val repository by inject<HelloRepository>()
    private val retrofitRepository by inject<ApiInterface>()
    private val retrofitRepositoryImpl by inject<ApiInterfaceImpl>()
    private val testPresenter: ApiViewModel by inject()
    private val dispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        koinApplication {
            modules(appModule)
            checkModules()
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `에라 모르겠다`() {
        // Koin을 사용한 상태에서 단위 테스트를 하려면 startKoin이 아닌 koinApplication {}으로 구성한 후 단위 테스트를 수행해야 한다
        // loadFoo("test")는 ServerResponse에 맞춰 값을 받아오기 때문에 value를 출력하면 false가 출력된다
        val response = testPresenter.loadFoo("test")
        assertNotNull(response)
        println("assertNotNull(response) : ${assertNull(response.value)}")
        // response 안에 들어있는 value를 출력하려면 아래처럼 한다
        val value = response.value
        println("value.toBoolean() : ${value.toBoolean()}")
        assertFalse(value.toBoolean())
    }

    @Test
    fun `레트로핏 사용하지 않은 단위 테스트`() {
        val response = repository.giveHello()
        assertTrue(true)
        assertNotNull(response)
    }

    @Test
    fun `레트로핏 함수 테스트`() {
        val response = retrofitRepository.isRegisteredUserString("test").execute()
        assertNotNull(response)
        assertTrue(response.code() == 200)
        assertTrue(response.isSuccessful)
        assertNotNull(response.headers())
    }

    @Test
    fun `Rxjava 레트로핏 함수 테스트`() {
        val response = retrofitRepository.isRegisteredUser("test").take(1).blockingFirst()
        println("response.result : ${response.result}")
        assertFalse(response.result == true)
    }

    @Test
    fun `레트로핏을 사용하지 않은 RxJava 테스트`() {
        // just()로 임의의 문자열 스트림 방출
        val source = Observable.just("A", "Banana", "CarCar", "De", "Elen")
        // 방출되는 데이터 중 길이가 3 이상인 1번째 문자열을 firstItem에 담는다
//        val firstItem = source.filter { s -> s.length > 2 }.blockingFirst()
        // 방출되는 데이터 중 길이가 5 이상인
        val singleItem = source.filter { s -> s.length > 4 }.take(2)

//        println("firstItem : $firstItem")
//        println("singleItem : ${singleItem.count()}")
        println("singleItem.count() : ${singleItem.count().blockingGet()}")
        assertNotNull(singleItem.count())
    }

}