package com.haha.fastproject.crashlytics

/**
 * @author zhe.chen
 * @date 4/29/21 09:42
 * Des:
 */
interface ICrash {

    fun setUserID(id: String)

    fun addCustomKey(key: String, value: String)

    fun addCustomKey(key: String, value: Boolean)

    fun addCustomKey(key: String, value: Int)

    fun addCustomKey(key: String, value: Long)

    fun addCustomKey(key: String, value: Float)

    fun addCustomKey(key: String, value: Double)

    /**
     * 上报非致命异常
     */
    fun recordException(e: Throwable)

}