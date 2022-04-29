package com.ulternativetechnology.kotlinwithkointest.util

import android.util.Log
import com.ulternativetechnology.kotlinwithkointest.BaseApplication

/* 로그 출력 관련 기능들을 모아놓은 object */
object LogUtil {
    /**
     * 스토어에 앱을 릴리즈 버전으로 올렸을 때 로그를 표시하지 않기 위해서 필요한 메서드
     */
    private fun buildLogMessage(message: String): String {
        val ste: StackTraceElement = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append(" : ")
        sb.append(ste.methodName)
        sb.append("] ")
        sb.append(message)

        return sb.toString()
    }

    fun e(TAG: String, message: String) {
        if (BaseApplication.DEBUG) Log.e(TAG, buildLogMessage(message))
    }

    fun d(TAG: String, message: String) {
        if (BaseApplication.DEBUG) Log.d(TAG, buildLogMessage(message))
    }

}