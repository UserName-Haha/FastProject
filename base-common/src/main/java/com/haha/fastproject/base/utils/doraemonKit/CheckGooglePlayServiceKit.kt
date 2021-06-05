package com.haha.fastproject.base.utils.doraemonKit

import android.content.Context
import com.haha.fastproject.base.R
import com.haha.fastproject.base.global.config.AppConfig
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener
import me.goldze.mvvmhabit.utils.toast.ToastUtils

/**
 * @author zhe.chen
 * @date 2021/5/18 10:41
 * Des:
 */
class CheckGooglePlayServiceKit(override val icon: Int = R.drawable.ic_launcher, override val name: Int = R.string.kit_check_google_play_service_switch) : AbstractKit() {

    private val INDEX_OPEN = 0
    private val INDEX_CLOSE = 1

    private val mChooseList = arrayOfNulls<String>(2).apply {
        this[INDEX_OPEN] = "开启"
        this[INDEX_CLOSE] = "关闭"
    }

    override fun onAppInit(context: Context?) {

    }

    override fun onClick(context: Context?) {
        XPopup.Builder(context)
            .asCenterList("检查GooglePlayService", mChooseList, null, object : OnSelectListener {
                override fun onSelect(position: Int, text: String?) {
                    when (position) {
                        INDEX_OPEN -> {
                            AppConfig.CHECK_GOOGLE_SERVICE = true
                            ToastUtils.show("已开启GoogleService检查")
                        }
                        INDEX_CLOSE -> {
                            AppConfig.CHECK_GOOGLE_SERVICE = false
                            ToastUtils.show("已关闭GoogleService检查")
                        }
                    }
                }
            }).show()
    }


}