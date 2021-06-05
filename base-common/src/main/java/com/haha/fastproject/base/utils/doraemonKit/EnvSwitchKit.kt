package com.qixi.zidan.v2.utils.doraemonKit

import android.content.Context
import com.haha.fastproject.base.R
import com.haha.fastproject.base.global.constant.SPConstant
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener
import me.goldze.mvvmhabit.utils.SPUtils
import me.goldze.mvvmhabit.utils.toast.ToastUtils

/**
 * @author zhe.chen
 * @date 3/24/21 20:44
 * Des:
 */
class EnvSwitchKit(override val icon: Int = R.drawable.ic_launcher, override val name: Int = R.string.kit_env_switch) : AbstractKit() {

    private val INDEX_HOST_RELEASE = 0
    private val INDEX_HOST_TEST = 1

    private val mChooseList = arrayOfNulls<String>(2).apply {
        this[INDEX_HOST_RELEASE] = "正式线"
        this[INDEX_HOST_TEST] = "测试线"
    }

    override fun onAppInit(context: Context?) {
    }

    override fun onClick(context: Context?) {
        XPopup.Builder(context)
            .asCenterList("环境切换", mChooseList, null, object : OnSelectListener {
                override fun onSelect(position: Int, text: String?) {
                    when (position) {
                        INDEX_HOST_RELEASE -> {
                            SPUtils.getInstance().put(SPConstant.KEY_IS_RELEASE_ENV, true)
                        }
                        INDEX_HOST_TEST -> {
                            SPUtils.getInstance().put(SPConstant.KEY_IS_RELEASE_ENV, false)
                        }
                    }
                    ToastUtils.show("请重启App")
                }
            }).show()
    }
}