package com.haha.fastproject.platform_login

import android.app.Application
import com.haha.fastproject.base.base.IModuleInit
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import me.goldze.mvvmhabit.utils.KLog

/**
 * @author zhe.chen
 * @date 4/28/21 10:52
 * Des:
 */
class PlatformLoginModuleInit : IModuleInit {

    override fun onInitAhead(application: Application?): Boolean {
        KLog.w("三方登录模块 -- onInitAhead 开始")
        return false
    }

    override fun onInitLow(application: Application?): Boolean {
        KLog.w("三方登录模块 -- onInitLow 开始")
        FacebookSdk.sdkInitialize(application)
        AppEventsLogger.activateApp(application)
        return false
    }
}