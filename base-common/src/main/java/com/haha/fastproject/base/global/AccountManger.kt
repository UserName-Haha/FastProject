package com.haha.fastproject.base.global

import com.haha.fastproject.base.contract.AccountBean
import com.haha.fastproject.base.global.constant.SPConstant
import com.google.gson.Gson
import me.goldze.mvvmhabit.utils.SPUtils

/**
 * @author zhe.chen
 * @date 2021/5/10 15:30
 * Des:
 */
object AccountManger {

    /**
     * 是否初次进入App
     */
    fun isFirstEnter(): Boolean {
        return SPUtils.getInstance().getBoolean(SPConstant.KEY_FIRST_ENTER, true)
    }

    /**
     * 保存首次进入状态
     *
     * @param first
     */
    fun putFirstEnterState(first: Boolean) {
        SPUtils.getInstance().put(SPConstant.KEY_FIRST_ENTER, first)
    }

    /**
     * 是否登录
     */
    @JvmStatic
    fun isLogin(): Boolean =
        SPUtils.getInstance().getBoolean(SPConstant.KEY_LOGIN_STATE, false)

    /**
     * 保存Login状态
     */
    fun putLoginState(isLogin: Boolean) {
        SPUtils.getInstance().put(SPConstant.KEY_LOGIN_STATE, isLogin)
    }

    /**
     * 添加账户信息
     */
    @JvmStatic
    fun putAccountInfo(accountBean: AccountBean?) {
        accountBean?.apply {
            val toJson = Gson().toJson(this)
            if (!toJson.isNullOrBlank()) {
                SPUtils.getInstance().put(SPConstant.KEY_ACCOUNT, toJson)
            }
        }
    }

    /**
     * 更新账户信息（一般用户更新账户某个或者某几个字段）
     */
    @JvmStatic
    fun updateAccountInfo(newAccountBean: AccountBean?) {
        newAccountBean?.apply {
            val accountInfo = getAccountInfo()
            if (accountInfo != null) {
                if (account.isNullOrBlank()) {
                    accountInfo.account = account
                }
                if (!face.isNullOrBlank()) {
                    accountInfo.face = face
                }
                if (!nickname.isNullOrBlank()) {
                    accountInfo.nickname = nickname
                }
                if (sex != null) {
                    accountInfo.sex = sex
                }
                if (uid.isNullOrBlank()) {
                    accountInfo.uid = uid
                }
                if (recvDiamond != null) {
                    accountInfo.recvDiamond = recvDiamond
                }
                if (sendDiamond != null) {
                    accountInfo.sendDiamond = sendDiamond
                }
                if (diamond != null) {
                    accountInfo.diamond = diamond
                }
                if (fansTotal != null) {
                    accountInfo.fansTotal = fansTotal
                }
                if (followTotal != null) {
                    accountInfo.followTotal = followTotal
                }
                if (grade != null) {
                    accountInfo.grade = grade
                }
                if (vip != null) {
                    accountInfo.vip = vip
                }
            } else {
                putAccountInfo(this)
            }
        }
    }

    /**
     * 清空当前账户信息
     */
    @JvmStatic
    fun clearAccountInfo() {
        SPUtils.getInstance().remove(SPConstant.KEY_ACCOUNT)
    }

    /**
     * 获取当前账户信息
     */
    @JvmStatic
    fun getAccountInfo(): AccountBean? =
        SPUtils.getInstance().getString(SPConstant.KEY_ACCOUNT, "").run {
            if (this.isNullOrBlank()) {
                return null
            } else {
                try {
                    return Gson().fromJson(this, AccountBean::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return AccountBean()
                }
            }
        }

    @JvmStatic
    fun getUid(): String? {
        val accountInfo = getAccountInfo()
        if (accountInfo != null && !accountInfo.uid.isNullOrBlank()) {
            return accountInfo.uid
        }
        return null
    }


}