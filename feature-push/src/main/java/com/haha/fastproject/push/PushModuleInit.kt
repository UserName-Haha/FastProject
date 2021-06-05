package com.haha.fastproject.push

import android.app.Application
import android.os.Build
import com.haha.fastproject.base.base.IModuleInit
import com.haha.fastproject.base.global.config.AppConfig
import com.haha.fastproject.push.xpush.CustomPushReceiver
import com.haha.fastproject.push.xpush.FCMPushClient
import com.xuexiang.xpush.XPush
import me.goldze.mvvmhabit.utils.KLog


/**
 * @author zhe.chen
 * @date 2021/5/10 09:33
 * Des:
 */
class PushModuleInit : IModuleInit {

    override fun onInitAhead(application: Application): Boolean {
        KLog.e("Push模块--->onInitAhead")
        return false
    }

    override fun onInitLow(application: Application): Boolean {
        KLog.w("Push模块--->onInitLow")
        XPush.debug(AppConfig.LOG_ENABLE)
        XPush.init(application, FCMPushClient())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            XPush.registerPushReceiver(CustomPushReceiver())
        }
        XPush.register()
        return false
    }
}