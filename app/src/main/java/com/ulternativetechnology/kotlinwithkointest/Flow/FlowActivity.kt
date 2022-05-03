package com.ulternativetechnology.kotlinwithkointest.Flow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.MutableLiveData
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.base.BaseActivity
import com.ulternativetechnology.kotlinwithkointest.databinding.ActivityFlowBinding
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiClient
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiInterface
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiViewModel
import com.ulternativetechnology.kotlinwithkointest.kointest.ServerResponse
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class FlowActivity : BaseActivity<ActivityFlowBinding>(), CoroutineScope {

    val TAG = this.javaClass.simpleName
    override val layoutResourceId: Int
        get() = R.layout.activity_flow

    private lateinit var apiViewModel: ApiViewModel
    private lateinit var job: Job
    val api = ApiClient.getApiInterface()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiViewModel = ApiViewModel(api)
        job = Job()

        /* launch {} 밖의 코드들이 먼저 실행된 다음 내부 코드들이 실행된다 */
        launch {
            showProgressDialog()
            LogUtil.e(TAG, "launch {} 내부 실행");
            apiViewModel.isRegisteredUserForRetrofit("test").observe(this@FlowActivity) { data ->
                LogUtil.e(TAG, "액티비티에서 data.result : ${data!!.result}");
            }
            dismissProgressDialog()
        }

        LogUtil.e(TAG, "launch {} 외부 실행");
    }
}