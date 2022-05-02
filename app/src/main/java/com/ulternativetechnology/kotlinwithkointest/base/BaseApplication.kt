package com.ulternativetechnology.kotlinwithkointest.base

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    companion object {
        var DEBUG = false;
    }
    var instance: BaseApplication? = null
    var context: Context? = null
    val TAG: String = this.javaClass.simpleName

    override fun onCreate() {
        super.onCreate()

        DEBUG = isDebuggable(this)
        context = applicationContext
        instance = this

        /* startKoin {} : GlobalContext API를 쓰기 위해 KoinApplication 컨테이너를 구성하고 GlobalContext에 등록
        * koinApplication {} : KoinApplication 컨테이너 구성 생성 */
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            // 위의 메서드 2개는 없어도 작동하긴 한다
            /* modules() : 컨테이너에 로드할 Koin 모듈 목록 설정. listOf()로 appModule을 구성한 예제가 있었다 */
            modules(appModule)
        }
    }

    /**
     * 앱이 디버그, 릴리즈 모드 중 어떤 상태인지를 T/F 값으로 알려주는 메서드
     */
    private fun isDebuggable(context: Context): Boolean {
        var isDebuggable = false
        val pm: PackageManager = context.packageManager
        try {
            val appInfo: ApplicationInfo = pm.getApplicationInfo(context.packageName, 0)
            isDebuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        }   catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return isDebuggable
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

}