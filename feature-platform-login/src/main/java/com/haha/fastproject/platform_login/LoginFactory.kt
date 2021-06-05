package com.haha.fastproject.platform_login

import android.app.Activity
import com.haha.fastproject.platform_login.impl.FacebookLoginImpl
import com.haha.fastproject.platform_login.impl.GoogleLoginImpl
import com.haha.fastproject.platform_login.impl.PhoneLoginImpl
import java.lang.ref.WeakReference

/**
 * @author zhe.chen
 * @date 4/27/21 15:21
 * Des:
 */
object LoginFactory {

    fun createLoginImpl(activity: Activity, loginType: LoginType, loginCallback: PlatformLogin.LoginCallback): ILogin =
        when (loginType) {
            LoginType.Google -> {
                GoogleLoginImpl(WeakReference(activity), loginCallback)
            }
            LoginType.FaceBook -> {
                FacebookLoginImpl(WeakReference(activity), loginCallback)
            }
            LoginType.PHONE -> {
                PhoneLoginImpl(WeakReference(activity))
            }
        }
}