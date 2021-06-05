package com.haha.fastproject

import com.haha.fastproject.base.global.config.ModuleLifecycleConfig
import me.goldze.mvvmhabit.base.BaseApplication


/**
 * @author zhe.chen
 * @date 4/21/21 14:40
 * Des:
 */
class AppApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this)
    }
}