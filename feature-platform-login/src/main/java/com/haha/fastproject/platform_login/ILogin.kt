package com.haha.fastproject.platform_login

import android.content.Intent

/**
 * @author zhe.chen
 * @date 4/27/21 15:12
 * Des:
 */
interface ILogin {

    fun action()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

}

enum class LoginType constructor(name: String) {

    Google("google"), FaceBook("facebook"), PHONE("phone")
}