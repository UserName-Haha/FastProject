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
    var BASEURL = "https://phone.auchat.me/"

    /**
     * 测试线
     */
    @JvmField
    var BASEURL_TEST = "https://phone-test.auchat.me/"

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
        "1053771142816-dlmonavrgbs5qp83rv5epug6epvb9mp0.apps.googleusercontent.com"

    /**
     * DoraemonKit Id
     */
    const val DoraemonKitProductId = "2dd7ae3a7c1957c8dfdab5ee7ef4cf32"

    /**
     * 网络数据加解密key(十六进制)
     */
    const val HEX_KEY = "e572dc7d49b4b152bd18f339c2a20fea"

    /**
     * 隐私协议地址
     */
    val PrivacyProtocol = getBaseUrl() + "other/eula"

}