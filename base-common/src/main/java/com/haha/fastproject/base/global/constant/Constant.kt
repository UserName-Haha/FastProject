package com.haha.fastproject.base.global.constant

import me.goldze.mvvmhabit.utils.SPUtils

/**
 * @author zhe.chen
 * @date 4/27/21 09:31
 * Des:
 */
object Constant {

    /**
     * 正式线
     */
    @JvmField
    var BASEURL = "https://xxxxxxx/"

    /**
     * 测试线
     */
    @JvmField
    var BASEURL_TEST = "https://xxxxxxx/"

    /**
     * 换区当前的主机地址
     */
    @JvmStatic
    fun getBaseUrl(): String {
        val releaseEnv = SPUtils.getInstance().getBoolean(SPConstant.KEY_IS_RELEASE_ENV, true)
        return if (!releaseEnv) {
            BASEURL_TEST
        } else BASEURL
    }

    /**
     * Google登录Token
     */
    const val RequestIdToken =
        "xxxxxxxxxxx"

    /**
     * DoraemonKit Id
     */
    const val DoraemonKitProductId = "2dd7ae3a7c1957c8dfdab5ee7ef4cf32"

}