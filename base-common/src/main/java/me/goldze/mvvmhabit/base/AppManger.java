package me.goldze.mvvmhabit.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * Create By zhe.chen on 2019/4/29.
 */
public class AppManger {

    private final String TAG = this.getClass().getSimpleName();
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";
    private List<AppStatusListener> mAppStatusListenerList = new ArrayList<>();

    /**
     * 所有存活的Activity集合
     */
    private List<Activity> mActivityList;

    /**
     * 当前正在显示的activity
     */
    private Activity mCurrentActivity;
    private static AppManger mAppManger;
    private Application mApplication;

    private AppManger() {
    }

    public static AppManger getManger() {
        if (mAppManger == null) {
            synchronized (AppManger.class) {
                if (mAppManger == null) {
                    mAppManger = new AppManger();
                }
            }
        }
        return mAppManger;
    }

    public AppManger init(Application application) {
        this.mApplication = application;
        return mAppManger;
    }

    /**
     * 注册App前后台监听
     *
     * @param appStatusListener
     */
    public void registerAppStatusListener(AppStatusListener appStatusListener) {
        mAppStatusListenerList.add(appStatusListener);
    }

    /**
     * 移除App状态监听
     *
     * @param appStatusListener
     */
    public void unRegisterAppStatusListener(AppStatusListener appStatusListener) {
        if (appStatusListener == null) return;
        mAppStatusListenerList.remove(appStatusListener);
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    /**
     * 让栈顶的Activity打开指定Activity
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (getTopActivity() == null) {
            KLog.i("mCurrentActivity is Null");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getTopActivity().startActivity(intent);
    }

    public void startActivity(Class activityClass) {
        startActivity(new Intent(mApplication, activityClass));
    }

    public List<AppStatusListener> getAppStatusListenerList() {
        return mAppStatusListenerList;
    }

    /**
     * 获取前台正在显示的Activity
     * 如当前没有Activity在前台显示,则可能返回Null！
     * 此方法适用于需要在Activity显示时执行某些操作时调用
     *
     * @return
     */
    @Nullable
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 获取最近启动的Activity,此Activity不一定在前台可见
     * 不会出现为Null情况
     *
     * @return
     */
    public Activity getTopActivity() {
        if (mActivityList == null) {
            KLog.i(TAG, "mActivityList == null at getTopActivity");
            return null;
        }
        return mActivityList.size() > 0 ? mActivityList.get(mActivityList.size() - 1) : null;
    }

    /**
     * 获取所有未销毁的Activity集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new ArrayList<>();
        }
        return mActivityList;
    }

    /**
     * 添加Activity到集合中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        synchronized (AppManger.class) {
            List<Activity> activityList = getActivityList();
            if (!activityList.contains(activity)) {
                activityList.add(activity);
            }
        }
    }

    /**
     * 删除集合红指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (AppManger.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 关闭指定Activity Class的实例
     *
     * @param activityClas
     */
    public void killActivity(Class<?> activityClas) {
        if (mActivityList == null) {
            KLog.i(TAG, "mActivity == null at killActivity");
            return;
        }
        synchronized (AppManger.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (next.getClass().equals(activityClas)) {
                    iterator.remove();
                    next.finish();
                }
            }
        }
    }

    /**
     * 执行Activity是否存在
     *
     * @param activity
     * @return
     */
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            KLog.i(TAG, "mActivity == null at activityInstanceIsLive");
            return false;
        }
        return mActivityList.contains(activity);
    }

    /**
     * 指定的Activity Class是否存在，同一个Class可能有多个实例
     *
     * @return
     */
    public boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityList == null) {
            KLog.i(TAG, "mActivity == null at activityClassIsLive");
            return false;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取指定的Activity实例
     *
     * @param activityClass
     * @return
     */
    public Activity findActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            KLog.i(TAG, "mActivity == null at findActivity");
            return null;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 杀死所有
     */
    public void killAll() {
        synchronized (AppManger.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 杀死所有Activity 排除指定Activity名称的实例
     *
     * @param excludeActivityName
     */
    public void killAll(String... excludeActivityName) {
        List<String> excludeList = Arrays.asList(excludeActivityName);
        synchronized (AppManger.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (excludeList.contains(next.getClass().getSimpleName())) {
                    continue;
                }
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 关闭所有Activity 排除指定Activity
     *
     * @param excludeActivityClasses
     */
    public void killAll(Class<?>... excludeActivityClasses) {
        List<Class<?>> excludeList = Arrays.asList(excludeActivityClasses);
        synchronized (AppManger.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (excludeList.contains(next)) {
                    continue;
                }
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 退出App(部分机型无效)
     */
    public void exitApp() {
        try {
            killAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface AppStatusListener {
        void onRunInBackground(Activity activity);

        void onRunInForeground(Activity activity);
    }
}