package com.ulternativetechnology.kotlinwithkointest.Flow

import android.annotation.SuppressLint
import android.os.Bundle
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.base.BaseActivity
import com.ulternativetechnology.kotlinwithkointest.databinding.ActivityFlowBinding
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiClient
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiViewModel
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import kotlinx.android.synthetic.main.activity_flow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

/* Koin + Coroutine + Retrofit + ViewModel, Observer */
class FlowActivity : BaseActivity<ActivityFlowBinding>(), CoroutineScope {

    val TAG = this.javaClass.simpleName
    override val layoutResourceId: Int
        get() = R.layout.activity_flow
    private val apiViewModelInject: ApiViewModel by inject()

    private lateinit var apiViewModel: ApiViewModel
    private lateinit var job: Job
    val api = ApiClient.getApiInterface()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiViewModel = ApiViewModel(api)
        job = Job()

        /* launch {} 밖의 코드들이 먼저 실행된 다음 내부 코드들이 실행된다 */
        launch {
            showProgressDialog()
            apiViewModelInject.isRegisteredUserString("test").observe(this@FlowActivity) { data ->
                if (data != null) {
                    LogUtil.e(TAG, "액티비티에서 성공 확인 : $data");
                } else {
                    LogUtil.e(TAG, "액티비티에서 확인한 결과가 null");
                }
            }
            /* 서버 응답을 깡 String으로 받아올 경우. 이 경우 처음부터 파싱해서 필요한 값을 빼와야 한다 */
//            apiViewModel.isRegisteredUserString("test").observe(this@FlowActivity) { data ->
//                if (data != null) {
//                    LogUtil.e(TAG, "액티비티에서 성공 확인 : $data");
//                    result_textview.text = "결과는 $data"
//                } else {
//                    LogUtil.e(TAG, "액티비티에서 확인한 값이 null");
//                }
//            }
            /* 서버 응답을 ServerResponse에 맞춰 받아올 경우. 이 경우 파싱 단계가 1차례 줄어든다 */
//            apiViewModel.isRegisteredUserForRetrofit("test").observe(this@FlowActivity) { data ->
//                LogUtil.e(TAG, "액티비티에서 data.result : ${data!!.result}");
//                result_textview.text = "결과는 ${data.result}"
//            }
            dismissProgressDialog()
        }
    }
}