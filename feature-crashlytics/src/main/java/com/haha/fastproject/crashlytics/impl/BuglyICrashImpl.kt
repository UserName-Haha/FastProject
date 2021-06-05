package com.haha.fastproject.crashlytics.impl

import com.haha.fastproject.crashlytics.ICrash

/**
 * @author zhe.chen
 * @date 4/29/21 09:58
 * Des:Bugly的实现
 * TODO:
 */
class BuglyICrashImpl : ICrash {

    override fun setUserID(id: String) {
    }

    override fun addCustomKey(key: String, value: String) {
    }

    override fun addCustomKey(key: String, value: Boolean) {
    }

    override fun addCustomKey(key: String, value: Int) {
    }

    override fun addCustomKey(key: String, value: Long) {
    }

    override fun addCustomKey(key: String, value: Float) {
    }

    override fun addCustomKey(key: String, value: Double) {
    }

    override fun recordException(e: Throwable) {
    }
}