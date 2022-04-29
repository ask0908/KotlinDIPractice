package com.ulternativetechnology.kotlinwithkointest.util

import android.annotation.SuppressLint
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.NonNull

object WebViewUtil {
    /* 웹뷰 기본 설정 */
    @SuppressLint("SetJavaScriptEnabled")
    fun setWebViewByDefault(webview: WebView, userAgent: String, useCache: Boolean) {
        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true)

        val webSettings = webview.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.setSupportMultipleWindows(true) // 여러 윈도우를 쓸 수 있게 설정
        webSettings.allowFileAccess = false
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE;  // 웹뷰가 바뀌면 바로 바뀔 수 있게 캐시는 사용하지 않음
    }

    /* 웹뷰가 파괴될 때 인터페이스 이름과 같이 호출해서 세팅돼 있던 설정들을 없애는 메서드 */
    fun onDestroy(@NonNull webview: WebView, interfaceName: String) {
        if (webview != null) {
            webview.clearHistory()
            webview.clearFocus()
            webview.clearFormData()
            webview.clearAnimation()
        }
    }

    /* 웹뷰에서 캐시만 지우고 싶을 때 사용하는 메서드 */
    fun clearWebViewCache(webview: WebView, includeDiskFile: Boolean) {
        if (webview != null) {
            webview.clearCache(includeDiskFile)
            webview.clearFormData()
            webview.clearHistory()
        }
    }

}