package com.haha.fastproject.base.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.haha.fastproject.library_res.R
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.RequestCallback
import me.goldze.mvvmhabit.utils.ResUtils

/**
 * @author zhe.chen
 * @date 4/27/21 10:16
 * Des:
 */
object PermissionExtension {

    @JvmStatic
    fun FragmentActivity.simpleRequestPermissions(array: Array<String>, callback: RequestCallback) {
        PermissionX.init(this)
            .permissions(*array)
            .onExplainRequestReason { scope, deniedList ->
                val message = "${ResUtils.getString(R.string.app_name)}需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "确定", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白")
            }
            .request(callback)
    }

    @JvmStatic
    fun Fragment.simpleRequestPermissions(array: Array<String>, callback: RequestCallback) {
        PermissionX.init(this)
            .permissions(*array)
            .onExplainRequestReason { scope, deniedList ->
                val message = "${ResUtils.getString(R.string.app_name)}需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "确定", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白")
            }
            .request(callback)
    }

}