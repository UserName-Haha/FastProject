package com.haha.fastproject.library_net.mock.providers

import me.goldze.mvvmhabit.base.BaseApplication
import me.goldze.mvvmhabit.utils.KLog
import java.io.IOException
import java.io.InputStream


/**
 * @author zhe.chen
 * @date 2021/5/17 14:43
 * Des:从Assets文件夹读取模拟的json数据
 */
class AssetsInputStreamProvider : InputStreamProvider {

    override fun provide(path: String): InputStream? {
        try {
            KLog.w("Mock路径--->" + path)
            return BaseApplication.getInstance().getAssets().open(path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}