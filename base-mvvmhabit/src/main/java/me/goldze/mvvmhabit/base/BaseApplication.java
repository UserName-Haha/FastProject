package me.goldze.mvvmhabit.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.utils.Utils;

/**
 * Created by goldze on 2017/6/15.
 */

public class BaseApplication extends Application {

    private static Application sInstance;
    public static boolean isRunInBackground = false;
    private static int appCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(@NonNull Application application) {

        sInstance = application;
        //初始化工具类
        Utils.init(application);
        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManger.getManger().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
                if (isRunInBackground) {
                    //应用从后台回到前台
                    back2App(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //设置前台Activity
                AppManger.getManger().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
                if (appCount == 0) {
                    //应用进入后台 需要做的操作
                    leaveApp(activity);
                }

                if (AppManger.getManger().getCurrentActivity() == activity) {
                    //移除前台显示Activity
                    AppManger.getManger().setCurrentActivity(null);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManger.getManger().removeActivity(activity);
            }
        });
    }

    /**
     * 应用从后台回到前台
     *
     * @param activity
     */
    private static void back2App(Activity activity) {
        isRunInBackground = false;
        if (!AppManger.getManger().getAppStatusListenerList().isEmpty()) {
            for (AppManger.AppStatusListener appStatusListener : AppManger.getManger().getAppStatusListenerList()) {
                appStatusListener.onRunInForeground(activity);
            }
        }
    }

    /**
     * 离开或退出App
     *
     * @param activity
     */
    private static void leaveApp(Activity activity) {
        isRunInBackground = true;
        if (!AppManger.getManger().getAppStatusListenerList().isEmpty()) {
            for (AppManger.AppStatusListener appStatusListener : AppManger.getManger().getAppStatusListenerList()) {
                appStatusListener.onRunInBackground(activity);
            }
        }
    }

    /**
     * 获得当前app运行的Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }
}
