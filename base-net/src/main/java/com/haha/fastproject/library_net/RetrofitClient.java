package com.haha.fastproject.library_net;

import android.content.Context;
import android.text.TextUtils;

import com.haha.fastproject.base.global.config.AppConfig;
import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.base.utils.AppUtils;
import com.haha.fastproject.library_net.Interceptor.BaseInterceptor;
import com.haha.fastproject.library_net.Interceptor.CacheInterceptor;
import com.haha.fastproject.library_net.Interceptor.DecryptInterceptor;
import com.haha.fastproject.library_net.Interceptor.OkHttpMockInterceptor;
import com.haha.fastproject.library_net.Interceptor.logging.Level;
import com.haha.fastproject.library_net.Interceptor.logging.LoggingInterceptor;
import com.haha.fastproject.library_net.cookie.CookieJarImpl;
import com.haha.fastproject.library_net.cookie.store.PersistentCookieStore;
import com.haha.fastproject.library_net.mock.providers.AssetsInputStreamProvider;
import com.github.gzuliyujiang.oaid.DeviceID;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.Utils;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by goldze on 2017/5/10.
 * RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {
    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;


    private Context mContext = Utils.getContext();

    public static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private Cache cache = null;
    private File httpCacheDirectory;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(Constant.getBaseUrl(), getHeads());

        private static HashMap<String, String> getHeads() {
            String TimeZoneID = TimeZone.getDefault().getID();
            HashMap<String, String> heads = new HashMap<>();
            heads.put("timezone", TimeZoneID);
            heads.put("PF", AppUtils.getOS());
            heads.put("UDID", DeviceID.getClientIdMD5());
            heads.put("APP_VERSION", AppUtils.getAppVersionCode() + "");
            heads.put("Bundle_ID", AppUtils.getAppPackageName());
            heads.put("Lang", Locale.getDefault().getLanguage());
            return heads;
        }
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        this(Constant.getBaseUrl(), null);
    }

    private RetrofitClient(String url, Map<String, String> headers) {
        if (TextUtils.isEmpty(url)) {
            url = Constant.BASEURL;
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "goldze_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            KLog.e("Could not create http cache", e);
        }
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
//                .cache(cache)
                //注意拦截器的顺序，如果数据加密了，需要把解密拦截器放到最后一个添加
                //因为放到最后一个后，响应数据会最先到最后一个拦截器进行解密处理，解密完后再交给上方的拦截器，这样可以保证日兰拦截器正确打印
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new LoggingInterceptor
                        .Builder()
                        //是否开启日志打印
                        .loggable(AppConfig.NET_LOG_ENABLE)
                        //打印的等级
                        .setLevel(Level.BASIC)
                        //打印类型
                        .log(Platform.INFO)
                        //Request的Tag
                        .request("Request")
                        //Response的Tag
                        .response("Response")
                        //添加打印头, 注意 key 和 value 都不能是中文
                        .addHeader("log-header", "I am the log request header.")
                        .build()
                )
                .addInterceptor(new CacheInterceptor(mContext))
                //网络数据Mock拦截器(通过AppConfig#HTTP_DATA_MOCK_ENABLE 属性控制是否启用)
                .addInterceptor(new OkHttpMockInterceptor(new AssetsInputStreamProvider(), 5))
                //解密拦截器需要放在最后一个
                .addInterceptor(new DecryptInterceptor())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return null;
    }
}
