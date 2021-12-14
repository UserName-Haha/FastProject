package com.haha.fastproject.base.base;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.gzuliyujiang.oaid.DeviceIdentifier;
import com.haha.fastproject.base.global.config.AppConfig;
import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.base.utils.PermissionExtension;
import com.haha.fastproject.base.utils.doraemonKit.CheckGooglePlayServiceKit;
import com.haha.fastproject.base.utils.doraemonKit.HttpDataMockKit;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.didichuxing.doraemonkit.kit.AbstractKit;
import com.qixi.zidan.v2.utils.doraemonKit.EnvSwitchKit;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.toast.ToastUtils;

/**
 * Created by goldze on 2018/6/21 0021.
 * 基础库自身初始化操作
 */

public class BaseModuleInit implements IModuleInit {

    @Override
    public boolean onInitAhead(Application application) {
        //开启打印日志
        KLog.init(AppConfig.LOG_ENABLE);
        KLog.w("基础层初始化 -- onInitAhead");
        //初始化阿里路由框架
        if (AppConfig.LOG_ENABLE) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.w("基础层初始化 -- onInitLow");
        registerGlobalMustPermissionsRequest(application);
        ToastUtils.init(application);
        //设备唯一标识工具库注册
        DeviceIdentifier.register(application);
        //初始化DoraemonKit工具
        ArrayList<AbstractKit> abstractKitSparseArray = new ArrayList();
        abstractKitSparseArray.add(new EnvSwitchKit());
        abstractKitSparseArray.add(new HttpDataMockKit());
        abstractKitSparseArray.add(new CheckGooglePlayServiceKit());
        DoraemonKit.install(application, abstractKitSparseArray, Constant.DoraemonKitProductId);
        return false;
    }

    /**
     * 监控全局的必须权限状态，如未授予及时请求
     *
     * @param application
     */
    private void registerGlobalMustPermissionsRequest(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                if (!(activity instanceof BaseActivity)) return;
                if (activity.getClass().getSimpleName().equals("SplashActivity")) return;
                PermissionExtension.simpleRequestPermissions(((BaseActivity) activity), getMustPermissionList(), (allGranted, grantedList, deniedList) -> {
                });
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    private String[] MUST_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] MUST_STORAGE_READPHONESTATE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};


    /**
     * Android10以上获取IMEI会抛出 SecurityException 异常，故不申请
     *
     * @return
     */
    private String[] getMustPermissionList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return MUST_STORAGE;
        }
        return MUST_STORAGE_READPHONESTATE;
    }
}
