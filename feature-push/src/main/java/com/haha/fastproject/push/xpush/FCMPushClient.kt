package com.haha.fastproject.push.xpush

import android.content.Context
import com.xuexiang.xpush.core.IPushClient
import com.xuexiang.xpush.util.PushUtils

/**
 * @author zhe.chen
 * @date 2021/5/10 09:46
 * Des:
 */
class FCMPushClient : IPushClient {

    companion object {
        val MIPUSH_PLATFORM_NAME = "FCMPush"
        val MIPUSH_PLATFORM_CODE = 1001
    }

    override fun init(context: Context?) {
    }

    override fun register() {

    }

    override fun unRegister() {
    }

    override fun bindAlias(alias: String?) {
    }

    override fun unBindAlias(alias: String?) {
    }

    override fun getAlias() {
    }

    override fun addTags(vararg tag: String?) {
    }

    override fun deleteTags(vararg tag: String?) {
    }

    override fun getTags() {
    }

    override fun getPushToken(): String = PushUtils.getPushToken(MIPUSH_PLATFORM_NAME)

    override fun getPlatformCode(): Int = MIPUSH_PLATFORM_CODE

    override fun getPlatformName(): String = MIPUSH_PLATFORM_NAME
}