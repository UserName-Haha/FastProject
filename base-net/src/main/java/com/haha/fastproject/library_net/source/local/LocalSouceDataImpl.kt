package com.haha.fastproject.library_net.source.local

import com.haha.fastproject.base.global.constant.SPConstant
import me.goldze.mvvmhabit.utils.SPUtils

/**
 * @author zhe.chen
 * @date 4/8/21 17:09
 * Des:
 */
class LocalSouceDataImpl private constructor() : LocalSouceData {

    companion object {

        private var INSTANCE: LocalSouceDataImpl? = null

        fun getInstance(): LocalSouceDataImpl {
            if (INSTANCE == null) {
                synchronized(LocalSouceDataImpl::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = LocalSouceDataImpl()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun saveLoginPhoneNumber(phoneNumber: String) {
        SPUtils.getInstance().put(SPConstant.KEY_PHONE_LOGIN_NUMBER, phoneNumber)
    }


}