package com.haha.fastproject.base.widget.webview

import android.util.Base64
import android.webkit.WebView
import com.haha.fastproject.base.utils.AppUtils
import com.baoyue.mugua.app.web.webview.WebViewUA
import com.github.gzuliyujiang.oaid.DeviceID
import com.google.gson.Gson
import com.just.agentweb.AgentWeb
import me.goldze.mvvmhabit.utils.KLog
import java.util.*

/**
 * @author zhe.chen
 * @date 4/12/21 10:13
 * Des:
 */

fun android.webkit.WebView.onResumeEvent() {
    loadUrl("javascript:document.dispatchEvent(new Event('viewAppear'))")
}

fun WebView.onPauseEvent() {
    loadUrl("javascript:document.dispatchEvent(new Event('viewDisappear'))")
}

fun WebView.onRunInForegroundEvent() {
    loadUrl("javascript:document.dispatchEvent(new Event('appForeground'))")
}

fun WebView.onRunInBackgroundEvent() {
    loadUrl("javascript:document.dispatchEvent(new Event('appBackground'))")
}

/**
 * 为WebView添加UA
 */
fun AgentWeb.addUA() {
    val ua = agentWebSettings.webSettings.userAgentString
    val language = Locale.getDefault().language
    val webViewUA = WebViewUA(
        AppUtils.getOS(),
        DeviceID.getClientIdMD5(),
        AppUtils.getAppVersionCode().toString(),
        AppUtils.getAppPackageName(),
        language
    )
    val json = Gson().toJson(webViewUA)
    val encodeToString = Base64.encodeToString(json.toByteArray(), Base64.NO_WRAP)
    agentWebSettings.webSettings.userAgentString = ua + " headers(${encodeToString})"
    val userAgentString = agentWebSettings.webSettings.userAgentString
    KLog.e("setting：${userAgentString}")
}