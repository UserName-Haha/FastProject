package com.haha.fastproject.library_net.cookie;

import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.library_net.RetrofitClient;
import com.haha.fastproject.library_net.cookie.store.CookieStore;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.utils.KLog;
import okhttp3.Cookie;

/**
 * @author zhe.chen
 * @date 2019-10-16 11:52
 * Des:
 */
public class CookieSyncUtil {

    private static String COOKIE_UMD = "umd=";
    private static String COOKIE_UID = "uid=";

    /**
     * 将本地的Cookie 同步到WebView
     */
    public static void syncCookieToWeb() {
        String cookie1 = CookieManager.getInstance().getCookie("sevenkm.cn");
        String webviewCookie = CookieManager.getInstance().getCookie(Constant.getBaseUrl());
        if (TextUtils.isEmpty(webviewCookie) || !webviewCookie.contains(COOKIE_UMD) || !webviewCookie.contains(COOKIE_UID)) {
            KLog.e("WebView中Cookie为空,需要同步 " + webviewCookie);
            //获取本地储存的Cookie
            CookieJarImpl cookieJar = (CookieJarImpl) RetrofitClient.okHttpClient.cookieJar();
            CookieStore cookieStore = cookieJar.getCookieStore();
            List<Cookie> allCookie = cookieStore.getAllCookie();

            CookieSyncManager.createInstance(BaseApplication.getInstance());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookie();
            StringBuffer sb = new StringBuffer();

            for (Cookie cookie : allCookie) {
                String cookieName = cookie.name();
                String cookieValue = cookie.value();
                if (!TextUtils.isEmpty(cookieName)
                        && !TextUtils.isEmpty(cookieValue)) {
                    sb.append(cookieName + "=");
                    sb.append(cookieValue + ";");
                }
            }
            String appHost = Constant.getBaseUrl();
            String[] cookie = sb.toString().split(";");
            for (int i = 0; i < cookie.length; i++) {
                cookieManager.setCookie(appHost, cookie[i]);// cookies是在HttpClient中获得的cookie
            }
            CookieSyncManager.getInstance().sync();
        }
    }

    public static void removeWebCookie() {
        CookieSyncManager.createInstance(BaseApplication.getInstance());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

}
