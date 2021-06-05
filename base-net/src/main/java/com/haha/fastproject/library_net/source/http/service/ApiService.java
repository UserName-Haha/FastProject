package com.haha.fastproject.library_net.source.http.service;

import com.haha.fastproject.base.contract.AppLanuchBean;
import com.haha.fastproject.base.contract.GiftBean;
import com.haha.fastproject.base.contract.HomeBean;
import com.haha.fastproject.base.contract.LoginBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author zhe.chen
 * @date 4/20/21 11:12
 * Des:API接口
 */
public interface ApiService {


    /**
     * 进入App时调用此接口
     *
     * @return
     */
    @GET("index/sync")
    Observable<AppLanuchBean> appLanuch(@QueryMap HashMap<String, String> params);

    /**
     * 获取最新礼物数据
     */
    @GET("gift/mall")
    Observable<GiftBean> getGiftData();

    /**
     * 三方登录
     */
    @FormUrlEncoded
    @POST("index/oauth")
    Observable<LoginBean> platformLogin(@FieldMap Map<String, String> params);

    /**
     * 获取首页热门主播
     *
     * @param params
     * @return
     */
    @GET("nearby")
    Observable<HomeBean> getHomeHotBroadcaster(@QueryMap HashMap<String, String> params);

    /**
     * 获取首页最新主播
     *
     * @param params
     * @return
     */
    @GET("/nearby/reg")
    Observable<HomeBean> getHomeNewBroadcaster(@QueryMap HashMap<String, String> params);


}
