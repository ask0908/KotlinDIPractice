package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample

import android.util.Log
import androidx.lifecycle.*
import com.ulternativetechnology.kotlinwithkointest.Flow.UpbitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class CoinViewModel : ViewModel() {

    private val requestCoinLive = MutableLiveData<String>()
    val responseUpbitLive: LiveData<UpbitResponse> = requestCoinLive.switchMap { query ->
        Log.e("CoinViewModel", "query : $query")
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            CoinRepo.webService.loadPrice("CRIX.UPBIT.KRW-BTC").asCallbackFLow().catch { e ->
                // 에러 스트림 처리
                Log.e("CoinViewModel", "e : $e")
            }.collect {
                // 최종적으로 데이터 스트림을 받고 liveData로 내보냅니다. 만약 Rx의 Single과같이 데이터를 하나만 받고
                // 스트림을 종료하기 위해서는 first() 메서드를 사용할 수 있습니다.
                this.emit(it)
            }
        }
    }

    // 호출할 코인코드를 받습니다.
    fun requestPrice(code: String) {
        this.requestCoinLive.postValue(code)
    }

}