package com.haha.fastproject.base.global.config

import android.net.Uri
import java.util.*

/**
 * @author zhe.chen
 * @date 4/25/21 15:18
 * Des:应用内Scheme匹配相关
 */
object AppLinkConfig {
    private data class AppLinkBean(var scheme: String, var host: String)

    private val Links =
        arrayListOf(AppLinkBean("http", "m.aliyun.com"),
            AppLinkBean("https", "m.aliyun.com"),
            AppLinkBean("auchat", "m.aliyun.com"),
            AppLinkBean("https", "web.aliyun.com"),
            AppLinkBean("http", "web.aliyun.com")

        )

    fun Uri.matchLink(): Boolean {
        val mScheme = this.scheme
        val mHost = this.host
        if (mScheme.isNullOrBlank() || mHost.isNullOrBlank()) return false
        for (link in Links) {
            if (link.scheme.toLowerCase(Locale.CHINESE)
                    .equals(mScheme) && link.host.toLowerCase(Locale.CHINESE).equals(mHost)
            ) {
                return true
            }
        }
        return false
    }
}