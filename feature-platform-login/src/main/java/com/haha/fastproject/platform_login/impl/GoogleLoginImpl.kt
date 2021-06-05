package com.haha.fastproject.platform_login.impl

import android.app.Activity
import android.content.Intent
import com.haha.fastproject.base.contract.AccountBean
import com.haha.fastproject.base.global.constant.Constant
import com.haha.fastproject.platform_login.ILogin
import com.haha.fastproject.platform_login.LoginType
import com.haha.fastproject.platform_login.PlatformLogin
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import me.goldze.mvvmhabit.utils.KLog
import java.lang.ref.WeakReference


/**
 * @author zhe.chen
 * @date 4/27/21 15:16
 * Des:Google登录实现
 */
class GoogleLoginImpl(private val activity: WeakReference<Activity>, val loginCallback: PlatformLogin.LoginCallback) : ILogin {

    private val CODE_SIGN_IN = 1001

    override fun action() {
        activity.get()?.apply {
            val mGoogleSignInClient =
                GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(Constant.RequestIdToken)
                    .requestEmail()
                    .requestId()
                    .requestProfile()
                    .build())
            mGoogleSignInClient.signOut()
            startActivityForResult(mGoogleSignInClient.getSignInIntent(), CODE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CODE_SIGN_IN -> {
                handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data))
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val info = """
            获取登录信息成功：
            ID：${account!!.id}
            photoUrl：${account.photoUrl}
            displayName：${account.displayName}
            familyName：${account.familyName}
            givenName：${account.givenName}
            idToken:${account.idToken}
            """.trimIndent()
            KLog.e(info)

            account.apply {
                val accountBean = AccountBean()
//                accountBean.face = photoUrl.toString()
//                accountBean.nickname = account.displayName
                accountBean.custom_access_token = account.idToken
                accountBean.custom_open_id = account.id
                loginCallback.onLoginSuccess(accountBean, LoginType.Google)
            }
        } catch (e: ApiException) {
            KLog.e("获取登录信息失败：" + e.message)
            loginCallback.onLoginFail(e.message ?: "", LoginType.Google)
        }
    }

}