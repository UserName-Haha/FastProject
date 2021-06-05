package com.haha.fastproject.base.global.config;

/**
 * Created by goldze on 2018/6/21 0021.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {

    private static final String BaseInit = "com.haha.fastproject.base.base.BaseModuleInit";
    //主业务模块
    private static final String MainInit = "com.haha.fastproject.main.MainModuleInit";

    //三方登录模块
    private static final String PlatformLoginInit = "com.haha.fastproject.platform_login.PlatformLoginModuleInit";

    //推送模块
    private static final String PushModuleInit = "com.haha.fastproject.push.PushModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, PlatformLoginInit, PushModuleInit};
}