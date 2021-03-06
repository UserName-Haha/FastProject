package com.haha.fastproject.library_net

import com.haha.fastproject.library_net.source.http.HttpSourceData
import com.haha.fastproject.library_net.source.local.LocalSouceData
import me.goldze.mvvmhabit.base.BaseModel


/**
 * @author zhe.chen
 * @date 4/8/21 16:32
 * Des:MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
class AppRepository private constructor(
    private val httpSourceData: HttpSourceData,
    private val localSourceData: LocalSouceData
) :
    BaseModel(), HttpSourceData, LocalSouceData {

    companion object {
        private var INSTANCE: AppRepository? = null

        fun getInstance(
            httpSourceData: HttpSourceData,
            localSourceData: LocalSouceData
        ): AppRepository {
            if (INSTANCE == null) {
                synchronized(AppRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AppRepository(httpSourceData, localSourceData)
                    }
                }
            }
            return INSTANCE!!
        }
    }

}