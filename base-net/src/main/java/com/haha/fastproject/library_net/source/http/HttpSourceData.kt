package com.haha.fastproject.library_net.source.http

import com.haha.fastproject.base.contract.AppLanuchBean
import com.haha.fastproject.base.contract.GiftBean
import com.haha.fastproject.base.contract.HomeBean
import com.haha.fastproject.base.contract.LoginBean
import io.reactivex.Observable

/**
 * @author zhe.chen
 * @date 4/8/21 16:33
 * Des:
 */
interface HttpSourceData {

    fun getLaunchData(): Observable<AppLanuchBean>

    fun getGiftData(): Observable<GiftBean>

    fun login(params: Map<String, String>): Observable<LoginBean>

    fun getHotBroadcaster(params: HashMap<String, String>): Observable<HomeBean>

    fun getNewBroadcaster(params: HashMap<String, String>): Observable<HomeBean>

}