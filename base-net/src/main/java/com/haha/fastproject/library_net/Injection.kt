package com.haha.fastproject.library_net

import com.haha.fastproject.library_net.source.http.HttpSourceDataImpl
import com.haha.fastproject.library_net.source.http.service.ApiService
import com.haha.fastproject.library_net.source.local.LocalSouceDataImpl


/**
 * @author zhe.chen
 * @date 4/8/21 16:51
 * Des:
 */
object Injection {

    fun providerAppRepository(): AppRepository {
        //网络API服务
        val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
        //网络数据源
        val mHttpSourceDataImpl = HttpSourceDataImpl.getInstance(apiService)
        //本地数据源
        val mLocalSouceDataImpl = LocalSouceDataImpl.getInstance()
        //两条分支组成一个数据仓库
        return AppRepository.getInstance(mHttpSourceDataImpl, mLocalSouceDataImpl)
    }
}