package com.haha.fastproject.base.contract

import com.google.gson.annotations.SerializedName

/**
 * @author zhe.chen
 * @date 2021/5/10 15:29
 * Des:
 */
class AccountBean {

    /**
     * 用户uid
     */
    var uid: String? = null

    /**
     * 账户
     */
    var account: String? = null

    /**
     * 性别
     */
    var sex: Int? = null

    /**
     * 昵称
     */
    var nickname: String? = null

    /**
     * 头像
     */
    var face: String? = null

    /**
     * 收到的钻石
     */
    @SerializedName(value = "recv_diamond")
    var recvDiamond: Int? = null

    /**
     * 送出去的钻石
     */
    @SerializedName(value = "send_diamond")
    var sendDiamond: Int? = null

    /**
     * 当前钻石数
     */
    @SerializedName(value = "diamond")
    var diamond: Int? = null

    /**
     * 粉丝数
     */
    @SerializedName(value = "fans_total")
    var fansTotal: Int? = null

    /**
     * 关注数
     */
    @SerializedName(value = "atten_total")
    var followTotal: Int? = null

    /**
     * 当前等级
     */
    @SerializedName(value = "grade")
    var grade: Int? = null

    /**
     * Vip级别
     */
    @SerializedName(value = "vip")
    var vip: Int? = null

    /**
     * 本地自定义字段
     */
    var custom_open_id:String?=null

    /**
     * 本地自定义字段（Google登录获取到的idToken）
     */
    var custom_access_token: String? = null

    /**
     * 是否是男性
     */
    fun isMale(): Boolean = sex != null && sex == 1

}