package com.ulternativetechnology.kotlinwithkointest.Flow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiClient
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ServerResponse
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowActivity : AppCompatActivity() {

    val TAG = this.javaClass.simpleName

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

//        launch {
//            delay(200L)
//            println("Task From runBlocking")    // (2)
//        }
//
//        coroutineScope {
//            launch {
//                delay(500L)
//                println("Task from nested launch")  // (3)
//            }
//            delay(100L)
//            println("Task from coroutine scope")    // (1)
//        }
//        println("CoroutineScope 종료")                // (4)
    }
}