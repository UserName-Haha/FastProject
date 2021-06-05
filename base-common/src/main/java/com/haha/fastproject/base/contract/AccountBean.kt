package com.haha.fastproject.base.contract

/**
 * @author zhe.chen
 * @date 2021/5/10 15:29
 * Des:
 */
class AccountBean {
    /**
     * 本地自定义字段
     */
    var custom_open_id: String? = null

    /**
     * 本地自定义字段（Google登录获取到的idToken）
     */
    var custom_access_token: String? = null

}