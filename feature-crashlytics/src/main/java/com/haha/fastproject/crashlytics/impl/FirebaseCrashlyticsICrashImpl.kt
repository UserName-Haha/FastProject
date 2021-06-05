package com.haha.fastproject.crashlytics.impl

import com.haha.fastproject.crashlytics.ICrash
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * @author zhe.chen
 * @date 4/29/21 09:41
 * Des:
 */
class FirebaseCrashlyticsICrashImpl : ICrash {

    override fun setUserID(id: String) {
        FirebaseCrashlytics.getInstance().setUserId(id)
    }

    override fun addCustomKey(key: String, value: String) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun addCustomKey(key: String, value: Boolean) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun addCustomKey(key: String, value: Int) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun addCustomKey(key: String, value: Long) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)

    }

    override fun addCustomKey(key: String, value: Float) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun addCustomKey(key: String, value: Double) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    override fun recordException(e: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(e)
    }

}