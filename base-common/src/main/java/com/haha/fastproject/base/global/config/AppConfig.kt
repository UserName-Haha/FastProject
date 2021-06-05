package com.haha.fastproject.base.global.config


/**
 * @author zhe.chen
 * @date 4/7/21 10:49
 * Des:
 */
object AppConfig {

    /**
     * 是否开启日志
     */
    const val LOG_ENABLE = true

    @JvmField
    var DEBUG = LOG_ENABLE

    /**
     * 是否开启网络日志
     */
    const val NET_LOG_ENABLE = true

    /**
     * 是否检查GooglePlay服务
     */
    @JvmField
    var CHECK_GOOGLE_SERVICE = true

    /**
     * 是否使用模拟数据
     */
    @JvmField
    var HTTP_DATA_MOCK_ENABLE = false



}