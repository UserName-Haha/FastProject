package com.haha.fastproject.library_net.source.local

/**
 * @author zhe.chen
 * @date 4/8/21 16:33
 * Des:
 */
interface LocalSouceData {

    /**
     * 保存当前登录的手机号
     */
    fun saveLoginPhoneNumber(phoneNumber: String)

}