package com.haha.fastproject.crashlytics

import com.haha.fastproject.crashlytics.impl.FirebaseCrashlyticsICrashImpl

/**
 * @author zhe.chen
 * @date 4/29/21 09:47
 * Des:
 */
object CrashUtils {

    val createICreash by lazy {
        FirebaseCrashlyticsICrashImpl()
    }

    /**
     * 异常模拟
     */
    fun mockCrash() {
        throw RuntimeException("Mock Crash")
    }

}