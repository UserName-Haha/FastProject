package com.haha.fastproject.base.contract

import com.google.gson.annotations.SerializedName

/**
 * @author zhe.chen
 * @date 2021/5/25 14:45
 * Des:
 */
class HomeBean : BaseResponse() {

    @SerializedName(value = "list")
    val broadcasterList: MutableList<BroadcasterBean>? = null

    class BroadcasterBean {

        var uid: Int? = null
        val face: String? = null
        val nickname: String? = null
        val city: String? = null
        val age: String? = null
        val distance: String? = null
        val photo_total = -1
        val certify = -1 //大于0即为已认证

        val pay_photo: Int? = null
        val online: String? = null
        val isCollect = false
        val vip: Int? = null //大于0的即为VIP
        val sex: Int = 2//默认为女性


    }

}