package com.haha.fastproject.platform_login

import android.app.Activity
import android.content.Intent
import com.haha.fastproject.base.contract.AccountBean
import com.haha.fastproject.base.contract.LoginBean

/**
 * @author zhe.chen
 * @date 4/27/21 11:47
 * Des:
 */
object PlatformLogin {

    private var createLoginImpl: ILogin? = null

    fun doLogin(activity: Activity, loginType: LoginType, loginCallback: LoginCallback) {
        createLoginImpl = LoginFactory.createLoginImpl(activity, loginType,loginCallback)
        createLoginImpl?.action()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        createLoginImpl?.onActivityResult(requestCode, resultCode, data)
    }

    interface LoginCallback {

        fun onLoginSuccess(accountBean: AccountBean, loginType: LoginType)

        fun onLoginFail(msg: String, loginType: LoginType)
    }


}

