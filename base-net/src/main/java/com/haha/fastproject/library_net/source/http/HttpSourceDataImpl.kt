package com.haha.fastproject.library_net.source.http

import com.haha.fastproject.base.contract.AppLanuchBean
import com.haha.fastproject.base.contract.GiftBean
import com.haha.fastproject.base.contract.HomeBean
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

    override fun getLaunchData(): Observable<AppLanuchBean> {
        val params = HashMap<String, String>()
        params["os_name"] = AppUtils.getOS()
        params["os_version"] = DeviceUtils.getSystemVersion()
        params["carrier"] =
            NetworkUtils.getNetworkOperatorName(BaseApplication.getInstance()) //运营商名称
        params["udid"] = DeviceID.getClientIdMD5() //唯一标识
        params["app_version"] = AppUtils.getAppVersionCode().toString()
        params["bssid"] = MacUtil.getMac(BaseApplication.getInstance()) //mac地址
        return apiService.appLanuch(params)
            .compose(HttpTransformer.exceptionTransformer<AppLanuchBean, AppLanuchBean>())
            .compose(RxUtils.schedulersTransformer<AppLanuchBean, AppLanuchBean>())
    }

    override fun getGiftData(): Observable<GiftBean> {
        return apiService
            .giftData
            .compose(RxUtils.schedulersTransformer<GiftBean, GiftBean>())
    }

    override fun login(params: Map<String, String>): Observable<LoginBean> {
        return apiService.platformLogin(params)
            .compose(HttpTransformer.exceptionTransformer<LoginBean, LoginBean>())
            .compose(RxUtils.schedulersTransformer<LoginBean, LoginBean>())
    }

    override fun getHotBroadcaster(params: HashMap<String, String>): Observable<HomeBean> {
        return apiService.getHomeHotBroadcaster(params)
            .compose(HttpTransformer.exceptionTransformer<HomeBean, HomeBean>())
            .compose(RxUtils.schedulersTransformer<HomeBean, HomeBean>())
    }

    override fun getNewBroadcaster(params: HashMap<String, String>): Observable<HomeBean> {
        return apiService.getHomeNewBroadcaster(params)
            .compose(HttpTransformer.exceptionTransformer<HomeBean, HomeBean>())
            .compose(RxUtils.schedulersTransformer<HomeBean, HomeBean>())
    }


}