package com.ulternativetechnology.kotlinwithkointest.util

import android.content.Context

object PlayStoreUtil {
    /**
     * 웹 URL을 써서 플레이 스토어로 이동하는 메서드
     * 스토어에 앱이 올라가 있지 않으면 결과값으로 null을 받는다
     */
    fun moveToStoreByUrl(context: Context): String {
        return String.format("https://play.google.com/store/apps/details?id=%s", context.applicationContext.packageName)
    }
}