package com.haha.fastproject.base.global.constant

import com.haha.fastproject.base.global.AccountManger

/**
 * @author zhe.chen
 * @date 2021/5/17 16:33
 * Des: 全局SharedPreferences Key 统一存放在这里，单个组件中的key可以另外在组件中定义
 */
object SPConstant {

    /**
     * 是否正式环境
     */
    @JvmField
    var KEY_IS_RELEASE_ENV = "key_isrelease_env"

    /**
     * 是否登录
     */
    @JvmField
    val KEY_LOGIN_STATE = "key_login_state"

    /**
     * 用户信息
     */
    @JvmField
    val KEY_ACCOUNT = "key_account"

    /**
     * 首次进入
     */
    @JvmField
    val KEY_FIRST_ENTER = "key_first_enter"

    /**
     * 本地礼物版本
     */
    @JvmField
    val KEY_LOCAL_GIFT_VER = "key_local_gift_ver"

    /**
     * 本地礼物数据
     */
    @JvmField
    val KEY_LOCAL_GIFT_DATA = "key_local_gift_data"

    /**
     * 手机登录时的手机号码
     */
    val KEY_PHONE_LOGIN_NUMBER = "key_phone_login_number"

    /**
     * 最后一条消息时间
     */
    private val KEY_LOCAL_LAST_MSG_TIME = "key_local_last_msg_time"

    /**
     * 获取最后一条消息时间的key，需要结合当前用户id,避免切换账号后消息串了
     */
    fun getLastMsgTimeKey(): String {
        return KEY_LOCAL_LAST_MSG_TIME + AccountManger.getUid()
    }

}