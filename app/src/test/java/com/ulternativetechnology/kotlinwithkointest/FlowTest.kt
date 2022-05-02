package com.ulternativetechnology.kotlinwithkointest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowTest {
    @Test
    // 이렇게 하면 runBlocking {} 빌더로 생성된 코루틴 블록(=메인 코루틴)은 GlobalScope.launch {} 빌더로 만든 코루틴이 종료될 때까지 대기한 후 종료됨
    fun flowTest() = runBlocking {

        // launch {} 코루틴 빌더 쓰기 전 하나의 자식 코루틴만 사용할 경우의 예시
//        val job = GlobalScope.launch {
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello, ")
//        // 자식 코루틴의 실행 흐름에 연결
//        job.join()

        /* 메인 코루틴 안에서 2개 이상의 자식 코루틴들이 수행되고 모든 자식 코루틴의 종료를 기다려야 한다면 어떻게 해야 하나?
        * 모든 자식 코루틴에 대응하는 Job 객체들의 참조를 어디에 유지하고 있다가 부모 코루틴이 종료돼야 하는 시점에 모든 자식 코루틴의
        * Job에 join해서 자식 코루틴들의 종료를 기다려야 한다. 이 때 코루틴 스코프가 사용된다
        * 모든 코루틴은 각자의 스코프를 가진다. runBlocking {} 코루틴 빌더 등으로 생성된 코루틴 블록 안에서 launch {} 코루틴 빌더를
        * 써서 새 코루틴을 만들면 현재 위치한 부모 코루틴에 join()을 명시적으로 호출할 필요 없이 자식 코루틴들을 실행하고 종료될 때까지
        * 대기할 수 있다 */
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello, ")

        /* CoroutineScope {} 빌더 : 이 빌더로 만들어진 코루틴은 모든 자식 코루틴이 끝날 때까지 종료되지 않는 스코프를 정의하는 코루틴
        * - runBlocking, coroutineScope {}의 차이
        * 1. runBlocking : 자식들의 종료를 기다리는 동안 현재 쓰레드를 block
        * 2. coroutineScope : 자식들의 종료를 기다리는 동안 현재 쓰레드를 block하지 않음 */

//        GlobalScope.launch {
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello, ")
//        // runBlocking {} : 중단 함수가 현재 쓰레드를 멈추게 할 수 있는 걸 명시적으로 나타냄
//        // 주어진 블록이 완료될 때까지 현재 쓰레드를 멈추는 새 코루틴을 만들어 실행하는 코루틴 빌더
//        // 코루틴 안에서 runBlocking {} 사용은 권장되지 않음. 일반 함수 코드 블록에서 중단 함수를 호출하기 위해 쓰는 장치
//        runBlocking {
//            delay(2000L)
//        }

//        simple().forEach { value ->
//            println(value)
//        }
    }

}