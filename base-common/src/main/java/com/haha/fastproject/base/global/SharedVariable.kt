package com.haha.fastproject.base.global

import com.haha.fastproject.base.contract.AppLanuchBean
import com.haha.fastproject.base.contract.LoginBean

/**
 * @author zhe.chen
 * @date 2021/5/18 16:04
 * Des:内存中全局共享的变量可以存放于此
 */
object SharedVariable {

    /**
     * 当前的更新信息
     */
    var mCurrentUpdateInfo: AppLanuchBean.UpDataAppBean? = null

    /**
     * 当前的Socket连接地址
     */
    var mSocketPath: String? = null

    /**
     * 首次登录用户,登录成功后返回的用户信息暂存。等待用户信息填写完成后使用
     */
    var mRegisterUserInfo: LoginBean? = null


}