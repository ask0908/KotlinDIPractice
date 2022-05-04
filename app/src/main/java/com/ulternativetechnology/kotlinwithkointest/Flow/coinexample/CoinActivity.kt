package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ulternativetechnology.kotlinwithkointest.Flow.CoinPrice
import com.ulternativetechnology.kotlinwithkointest.Flow.UpbitResponse
import com.ulternativetechnology.kotlinwithkointest.Flow.coinexample.jsontest.Candle
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CoinActivity : AppCompatActivity() {

    val TAG = this.javaClass.simpleName
    lateinit var candleAccTraderPrice: CoinPrice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val viewModel: CoinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.requestPrice("CRIX.UPBIT.KRW-BTC")
//        viewModel.responseUpbitLive.observe(this) { data ->
//            // 데이터가 받아지는 곳
//            // 액티비티에서 해당 데이터를 사용하려면 DTO에 넣은 다음 필요한 값들을 가져와야 한다
//            val coinData : UpbitResponse = data
//            Log.e(TAG, "coinData : $coinData")
//
//            /* JSON to Kotlin */
//            val coinCandles: List<CoinPrice> = coinData.candles
//
//            val coinPrices = coinData.candles
//            val coinUnit = coinData.unit
//            for (i in coinPrices.indices) {
////                Log.e(TAG, "coinPrices 내부 데이터 : ${coinPrices[i]}")
//                val price = coinPrices[i].tradePrice
//                Log.e(TAG, "coinPrice - tradePrice : $price")
//            }
//        }

        /* JSON to Kotlin 플러그인 테스트용 API 호출 */
        viewModel.testUpbitLive.observe(this) { data ->
            Log.e(TAG, "test data : $data")
            // 클래스들이 들어있는 리스트 형태가 아닌 낱개 데이터는 아래처럼 그냥 뽑아오면 된다
            // 전역으로 lateinit var 프로퍼티들을 만든 다음 여기서 변수들을 초기화하면 될 듯. 근데 lateinit var 말고 다른 방법 없나?
            val businessTime = data.businessTime
            val closingTime = businessTime.closing
            val openingTime = businessTime.opening
            Log.e(TAG, "closingTime : $closingTime, openingTime : $openingTime")

            /* Candle(candleAccTradePrice=1.14639726684987E9, candleAccTradeVolume=23.43079596...형태의 데이터 파싱하는 방법 */
            // 1. 먼저 List<Candle> 형태로 들어있는 raw data들을 꺼내온다
            val candles = data.candles
            // 2. 리스트 안에 들어있는 데이터 자료형에 맞는 ArrayList들을 만든다
            // 코틀린에서 리스트는 기본적으로 변경 불가능하기 때문에 자바와 다르게 바로 ArrayList를 선언하는 게 좋다
            val list = ArrayList<Double>()
            val list2 = ArrayList<Double>()
            val list3 = ArrayList<Double>()

            // 3. 1번에서 받아온 데이터들을 for문으로 순회하면서 위에서 만든 리스트들에 넣는다
            for (i in candles.indices) {
                val coinPrices = candles[i]
                list.add(coinPrices.candleAccTradePrice)
                list2.add(coinPrices.highPrice)
                list3.add(coinPrices.lowPrice)
            }

            // 4. 각 리스트를 for문으로 순회하면서 원하는 값들이 들어갔는지 확인
            for (i in list.indices) {
                Log.e(TAG, "list 안의 값들 : ${list[i]}")
            }
            for (i in list2.indices) {
                Log.e(TAG, "list2 안의 값들 : ${list2[i]}")
            }
            for (i in list3.indices) {
                Log.e(TAG, "list3 안의 값들 : ${list3[i]}")
            }

            /* Candle(candleAccTradePrice=2.7070370111734E8, candleAccTradeVolume=5.52738969...형태로 출력됨 */
            val candleAccTradePrice = candles[0]
//            Log.e(TAG, "candleAccTradePrice : $candleAccTradePrice")
//            Log.e(TAG, "businessTime : $businessTime")
//            Log.e(TAG, "candles : $candles")
        }
    }
}