package com.ulternativetechnology.kotlinwithkointest.Flow

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    /* CoroutineScope : 코루틴이 실행되는 범위. 코루틴을 실행하고 싶은 lifeCycle에 따라 원하는 scope를 만들어 코루틴이
    * 실행될 작업 범위 지정 가능
    * - 사용자 지정 CoroutineScope: CoroutineScope(CoroutineContext) */
//    CoroutineScope(Dispatchers.Default).launch {
//        // 메인 쓰레드 작업
//        delay(500L)
//        println("scope.launch{} 내부 코드 실행")
//    }
//    delay(2000L)
//    println("scope.launch{} 외부 코드 실행")

    /* GlobalScope : 앱 실행~종료될 때까지 실행 */
//    GlobalScope.launch {
//        launch(Dispatchers.Default) {
//            repeat(1000) { i ->
//                delay(500L)
//                println("i : $i")
//            }
//            println("Dispatchers.Default 내부 실행")
//        }
//        launch(Dispatchers.Main) {
//            delay(1500L)
//            println("메인 쓰레드로 전환됨")
//        }
//    }

    /* CoroutineBuilder.launch() : Job 객체. 결과값 리턴 x */
    CoroutineScope(Dispatchers.Default).launch {
        println("launch {} 내부 실행")
    }
    delay(1000L)
    println("1초 지연됨!!")
    println("launch {} 외부 실행")

    /* 대상 코루틴의 Job 객체 참조를 유지하며 별도의 코루틴에서 취소하게 매번 지정하는 건 번거롭다
    * 코루틴 프레임워크는 이 작업을 대신할 withTimeout()을 갖고 있다
    * 아래 코드를 실행하면 TimeoutCancellationException이 발생하는데 메인 함수에서 실행됐기 때문이다
    * 코루틴이 취소될 경우 발생하는 CancellationException은 코루틴에서 일반적인 종료 상황 중 하나로 간주된다 */
//    withTimeout(1300L) {
//        launch {
//            try {
//                repeat(1000) { i ->
//                    println("I'm sleeping $i...")
//                    delay(500L)
//                }
//            }   finally {
//                println("main : I'm running finally!")
//            }
//        }
//    }

    /* 코루틴에 제한 시간을 설정하고 이 시간을 넘을 경우 코루틴이 취소되게 구현
    * 이 코드를 실행하려면 runBlocking 뒤에 <Unit>을 붙여야 한다 */
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i...")
//                delay(500L)
//            }
//        }   finally {
//            println("main : I'm running finally!")
//        }
//    }
//
//    launch {
//        delay(1300L)
//        println("main : I'm tired of waiting. Cancel the job!")
//        if (job.isActive) {
//            job.cancelAndJoin()
//        }
//    }

    /* 이미 CancellationException이 발생한 코루틴의 finally {} 안에서 중단 함수를 호출하면 코루틴은 이미 취소된 상태라
    * CancellationException이 발생한다
    * 리소스 정리 함수들은 보통 Non-blocking으로 동작하기 때문에 문제되지 않지만 이미 취소된 코루틴 안에서 동기적으로 어떤 중단 함수를 호출해야 한다면
    * withContext {} 코루틴 빌더에 NonCancellable 컨텍스트를 전달해서 처리 가능하다 */
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i...")
//                delay(500L)
//            }
//        }   finally {
//            withContext(NonCancellable) {
//                delay(1000)
//                println("main : I'm running finally!")
//            }
//        }
//    }
//    delay(1300L)
//    println("main : I'm tired of waiting!!")
//    job.cancelAndJoin()
//    println("main : Now I can quit")

    /* try-finally 방식으로 CancellationException 처리 */
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i...")
//                delay(500L)
//            }
//        }   finally {
//            println("main : I'm running finally!")
//        }
//    }
//    delay(1300L)
//    println("main : I'm tired of waiting!!")
//    job.cancelAndJoin()
//    println("main : Now I can quit")

    /* 코루틴을 취소 요청에 친화적인 코드로 만들기 - isActive 사용
    * 취소 가능한 중단함수들은 취소되면 CancellationException을 발생시킨다. 이 때 일반적인 예외처리 방식과 똑같이 처리 가능하다 */
//    val job = launch(Dispatchers.Default) {
//        for (i in 1..10) {
//            if (!isActive) break
//            println("I'm sleeping $i...")
//            Thread.sleep(500L)  // 0.5초마다 for문을 반복하면서 출력
//        }
//    }
//    // 메인 쓰레드는 1.3초가 지나면 아래 코드를 실행시킨다
//    delay(1300L)
//    println("main : I'm tired of waiting!!")
//    // 이 시점에서 코루틴 작동이 취소된다
//    job.cancelAndJoin()
//    println("main : Now I can quit")

    /* 코루틴을 취소 요청에 친화적인 코드로 만들기 - yield() */
//    val job = launch(Dispatchers.Default) {
//        for (i in 1..10) {
//            yield()
//            println("I'm sleeping $i ...")
//            Thread.sleep(500L) // 0.5초마다 for문을 반복하면서 출력
//        }
//    }
//    delay(1300L)
//    println("main : I'm tired of waiting!!")
//    job.cancelAndJoin()
//    println("main : Now I can quit")

//    repeat(100_000) {
//        launch {
//            delay(1000L)
//            print(".")
//        }
//    }

//    launch {
//        doWorld()
//    }
//    println("Hello, ")

//    launch {
//        delay(200L)
//        println("Task from runBlocking")
//    }
//
//    coroutineScope {
//        launch {
//            delay(500L)
//            println("Task from nested launch")
//        }
//        delay(100L)
//        println("Task from coroutine scope")
//    }
//    println("Coroutine scope is over")
}

/* 코루틴 안에서 실행되는 중단 함수들은 suspend를 함수명 앞에 붙여서 만들 수 있다
* 일반 함수와의 차이는 delay() 같은 중단 함수들을 호출할 수 있다는 것이다 */
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}