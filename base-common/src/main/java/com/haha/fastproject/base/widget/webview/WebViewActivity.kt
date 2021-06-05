package com.haha.fastproject.base.widget.webview

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.haha.fastproject.base.R
import com.haha.fastproject.base.global.config.AppLinkConfig.matchLink
import com.haha.fastproject.base.router.RouterActivityPath
import com.just.agentweb.*
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * @author zhe.chen
 * @date 4/23/21 09:50
 * Des:
 */
@Route(path = RouterActivityPath.Base.PAGE_WEBVIEW)
class WebViewActivity : AppCompatActivity() {

    companion object {

        val KEY_URL = "KEY_URL"

        fun toWebPage(url: String) {
            ARouter.getInstance()
                .build(RouterActivityPath.Base.PAGE_WEBVIEW)
                .withString(KEY_URL, url)
                .navigation()
        }
    }

    private var mAgentWeb: AgentWeb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val url = intent.getStringExtra(KEY_URL)
        if (url.isNullOrBlank()) return
        AgentWebConfig.DEBUG = true
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(webViewContentLayout, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    //此处是自己手动匹配是否包含我们自定义的Scheme的
                    //按道理只需要在清单文件设置 intent-filter 系统即可自动配置
                    //但是我们使用过的AgentWeb AgentWeb内部对shouldOverrideUrlLoading进行了重写，
                    //所以走不到系统匹配那一层。故需要我们自己来手动匹配
                    if (request.url.matchLink()) {
                        ARouter.getInstance().build(request.url).navigation()
                        return true
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }
            })
            .setSecurityType(AgentWeb.SecurityType.DEFAULT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
            .interceptUnkownUrl()
            .createAgentWeb()
            .ready()
            .get()
            .apply {
                addUA()
                urlLoader.loadUrl(url)
                var mIndicatorController: IndicatorController? = null
                if (!TextUtils.isEmpty(url) && indicatorController.also {
                        mIndicatorController = it
                    } != null && mIndicatorController!!.offerIndicator() != null) {
                    indicatorController.offerIndicator().show()
                }
            }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mAgentWeb != null && mAgentWeb!!.handleKeyEvent(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb?.webLifeCycle?.onDestroy()
    }
}