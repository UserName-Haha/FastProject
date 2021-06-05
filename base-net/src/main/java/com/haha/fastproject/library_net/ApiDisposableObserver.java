package com.haha.fastproject.library_net;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haha.fastproject.base.contract.BaseResponse;
import com.haha.fastproject.base.global.AccountManger;
import com.haha.fastproject.base.router.RouterActivityPath;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.AppManger;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ResUtils;
import me.goldze.mvvmhabit.utils.Utils;
import me.goldze.mvvmhabit.utils.toast.ToastUtils;

/**
 * Created by zhe.chen on 2017/5/10.
 */
public abstract class ApiDisposableObserver<T extends BaseResponse> implements Observer<T> {

    public abstract void onResult(T t);

    @Override
    public void onSubscribe(@NotNull Disposable d) {
        //if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(Utils.getContext())) {
            ToastUtils.show(ResUtils.getString(R.string.res_net_unavailable));
            KLog.d("无网络，读取缓存数据");
            onComplete();
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponseThrowable) {
            ResponseThrowable rError = (ResponseThrowable) e;
            ToastUtils.show(rError.message);
            return;
        }
        //其他全部甩锅网络异常
        ToastUtils.show(R.string.res_net_net_error);
    }

    @Override
    public void onNext(@NotNull T t) {
        switch (t.getStat()) {
            case CodeRule.CODE_200:
                //请求成功, 正确的操作方式
                onResult(t);
                break;
            case CodeRule.LoginExpired:
                //无效的Token，提示跳入登录页
                ToastUtils.show(ResUtils.getString(R.string.res_net_login_expired));
                AccountManger.clearAccountInfo();
                //跳入登录界面
                ARouter.getInstance()
                        .build(RouterActivityPath.Main.PAGER_LOGIN)
                        .navigation(null, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                try {
                                    Class<?> aClass = Class.forName("com.haha.fastproject.main.login.LoginActivity");
                                    //关闭所有页面，除了LoginActivity
                                    AppManger.getManger().killAll(aClass);
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            default:
                ToastUtils.show("错误码:" + t.getStat());
                break;
        }
    }

    public static final class CodeRule {
        //请求成功
        static final int CODE_200 = 200;
        //Cookie过期
        static final int LoginExpired = 500;
    }
}