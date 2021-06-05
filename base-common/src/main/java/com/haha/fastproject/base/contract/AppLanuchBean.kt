package com.haha.fastproject.base.contract

import com.google.gson.annotations.SerializedName

/**
 * @author zhe.chen
 * @date 2021/5/14 17:40
 * Des:
 */
class AppLanuchBean : BaseResponse() {

    /**
     * 更新信息
     */
    @SerializedName(value = "update_app")
    val updateInfo: UpDataAppBean? = null

    /**
     * 当前最新礼物版本号
     */
    @SerializedName(value = "gift_version")
    val giftVersion: Int? = null

    /**
     * 当前账户信息
     */
    @SerializedName(value = "userinfo")
    val accountBean: AccountBean? = null

    /**
     * webSocket地址
     */
    @SerializedName(value = "wss")
    val wss: String? = null


    class UpDataAppBean {
        val url: String? = null

        val type: Int? = null

        @SerializedName(value = "title")
        val updateTitle: String? = null

        @SerializedName(value = "memo")
        val updateContent: List<String>? = null

        val versionCode: Int? = null

        fun mustUpdate() = type != null && type == 1

    }

}