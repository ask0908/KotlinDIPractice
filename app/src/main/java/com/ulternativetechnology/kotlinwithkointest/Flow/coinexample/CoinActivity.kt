package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ulternativetechnology.kotlinwithkointest.R
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CoinActivity : AppCompatActivity() {

    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val viewModel: CoinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.requestPrice("CRIX.UPBIT.KRW-BTC")
        viewModel.responseUpbitLive.observe(this) { data ->
            // 데이터가 받아지는 곳
            Log.e(TAG, "data : $data")
        }
    }
}