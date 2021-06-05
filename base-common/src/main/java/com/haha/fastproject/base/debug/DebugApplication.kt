package com.haha.fastproject.base.debug

import com.haha.fastproject.base.global.config.ModuleLifecycleConfig
import me.goldze.mvvmhabit.base.BaseApplication

/**
 * @author zhe.chen
 * @date 4/21/21 11:22
 * Des:
 */
class DebugApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this)
    }
}