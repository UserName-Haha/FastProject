package com.haha.fastproject.base.global.constant


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

}