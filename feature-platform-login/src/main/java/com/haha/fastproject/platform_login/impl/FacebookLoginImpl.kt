package com.haha.fastproject.platform_login.impl

import android.app.Activity
import android.content.Intent
import com.haha.fastproject.platform_login.ILogin
import com.haha.fastproject.platform_login.LoginType
import com.haha.fastproject.platform_login.PlatformLogin
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.haha.fastproject.base.contract.AccountBean
import me.goldze.mvvmhabit.utils.KLog
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author zhe.chen
 * @date 4/27/21 15:16
 * Des:FaceBook登录实现
 */
class FacebookLoginImpl(private val weakReference: WeakReference<Activity>, val loginCallback: PlatformLogin.LoginCallback) : ILogin {

    private val TAG = "FacebookLoginImpl"
    private var callbackManager: CallbackManager? = null

    override fun action() {
        callbackManager = CallbackManager.Factory.create()
        weakReference.get()?.let {
            LoginManager.getInstance().logOut()
            LoginManager.getInstance().logInWithReadPermissions(it, Arrays.asList("public_profile"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        KLog.e("FaceBook登录获取到的用户token：${result.accessToken?.token}")
                        val accountBean = AccountBean()
                        accountBean.custom_access_token = result.accessToken?.token
                        accountBean.custom_open_id = result.accessToken?.userId
                        loginCallback.onLoginSuccess(accountBean, LoginType.FaceBook)
                    }

                    override fun onCancel() {
                        KLog.i(TAG, "FB登录取消")
                        loginCallback.onLoginFail("FB登录取消", LoginType.FaceBook)
                    }

                    override fun onError(error: FacebookException?) {
                        KLog.e(TAG, "FB登录出错：${error?.message}")
                        loginCallback.onLoginFail(error?.message ?: "", LoginType.FaceBook)
                    }
                })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data);
    }
}