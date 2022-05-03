package com.ulternativetechnology.kotlinwithkointest

import android.os.Bundle
import com.ulternativetechnology.kotlinwithkointest.base.BaseActivity
import com.ulternativetechnology.kotlinwithkointest.databinding.ActivityMainBinding
import com.ulternativetechnology.kotlinwithkointest.kointest.ApiViewModel
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAG = this.javaClass.simpleName
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    private val testPresenter: ApiViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testPresenter.test("abcd@sdf.com").observe(this) {
            if (it.result) {
                LogUtil.e(TAG, "액티비티에서 확인한 result 값이 true : ${it.result}");
            } else {
                LogUtil.e(TAG, "액티비티에서 확인한 result 값이 false : ${it.result}");
            }
        }
    }
}