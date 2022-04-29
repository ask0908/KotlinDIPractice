package com.ulternativetechnology.kotlinwithkointest

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ulternativetechnology.kotlinwithkointest.util.NetworkManager.isNetworkConnected

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {
    lateinit var binding: T
    /* BaseActivity에서 onCreate()를 재정의하기 때문에 이 클래스를 상속한 액티비티는 레이아웃 리소스를 정의할 곳이 없다
    * 그래서 BaseActivity에서 레이아웃 리소스 id(R.layout.activity_main)를 받아서 여기서 데이터바인딩 시킨다 */
    abstract val layoutResourceId: Int

    lateinit var deviceId: String
    internal val toolbar: Toolbar? = null
    internal val mAlertDialog: AlertDialog? = null
    lateinit var mProgressDialog: ProgressDialog

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (isNetworkConnected(this)) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, layoutResourceId)
            deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            //
        }
    }

    private fun initializeProgressDialog() {
        mProgressDialog = ProgressDialog(this, R.style.AppProgressDialog)
    }

    fun showProgressDialog() {
        if (!isFinishing) {
            initializeProgressDialog()
            if (mProgressDialog.isShowing) return
            mProgressDialog.setCancelable(false)
            mProgressDialog.show()
        }
    }

    fun dismissProgressDialog() {
        if (!isFinishing && mProgressDialog.isShowing)
            mProgressDialog.dismiss()
    }

    override fun onPause() {
        super.onPause()
        dismissProgressDialog()
    }

}