package com.haha.fastproject.base.global.config;

import android.app.Application;
import android.util.Log;


import com.haha.fastproject.base.base.IModuleInit;

import io.reactivex.annotations.Nullable;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by goldze on 2018/6/21 0021.
 * 作为组件生命周期初始化的配置类，通过反射机制，动态调用每个组件初始化逻辑
 */

public class ModuleLifecycleConfig {

    private String TAG = "ModuleLifecycleConfig";

    //内部类，在装载该内部类时才会去创建单例对象
    private static class SingletonHolder {
        public static ModuleLifecycleConfig instance = new ModuleLifecycleConfig();
    }

    public static ModuleLifecycleConfig getInstance() {
        return SingletonHolder.instance;
    }

    private ModuleLifecycleConfig() {}

    //初始化组件-靠前
    public void initModuleAhead(@Nullable Application application) {
        Log.w(TAG,"开始靠前初始化");
        for (String moduleInitName : ModuleLifecycleReflexs.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IModuleInit init = (IModuleInit) clazz.newInstance();
                //调用初始化方法
                init.onInitAhead(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        KLog.w(TAG,"结束靠前初始化");
    }

    //初始化组件-靠后
    public void initModuleLow(@Nullable Application application) {
        KLog.w(TAG,"开始靠后初始化");
        for (String moduleInitName : ModuleLifecycleReflexs.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IModuleInit init = (IModuleInit) clazz.newInstance();
                //调用初始化方法
                init.onInitLow(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        KLog.w(TAG,"结束靠后初始化");

    }
}
