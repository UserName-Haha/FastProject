package com.haha.fastproject.library_net.source.http

import com.haha.fastproject.base.contract.AppLanuchBean
import com.haha.fastproject.base.contract.LoginBean
import com.haha.fastproject.base.utils.AppUtils
import com.haha.fastproject.base.utils.DeviceUtils
import com.haha.fastproject.base.utils.MacUtil
import com.haha.fastproject.base.utils.NetworkUtils
import com.haha.fastproject.library_net.HttpTransformer
import com.haha.fastproject.library_net.source.http.service.ApiService
import com.github.gzuliyujiang.oaid.DeviceID
import io.reactivex.Observable
import me.goldze.mvvmhabit.base.BaseApplication
import me.goldze.mvvmhabit.utils.RxUtils


/**
 * @author zhe.chen
 * @date 4/8/21 16:35
 * Des:
 */
class HttpSourceDataImpl private constructor(val apiService: ApiService) : HttpSourceData {

    companion object {

        private var INSTANCE: HttpSourceDataImpl? = null

        fun getInstance(apiService: ApiService): HttpSourceDataImpl {
            if (INSTANCE == null) {
                synchronized(HttpSourceDataImpl::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = HttpSourceDataImpl(apiService)
                    }
                }
            }
            return INSTANCE!!
        }
    }

}