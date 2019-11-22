package com.yang.network;

import com.yang.network.Interceptor.HttpCommonInterceptor;
import com.yang.network.cookie.CookieJarImpl;
import com.yang.network.cookie.MemoryCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * created by ccl on 2019/4/16
 **/
public class OkHttpClientHelper {

    private OkHttpClient okHttpClient;
    private MemoryCookieStore mStore = new MemoryCookieStore();

    private static class OkHttpClientHelperHolder {
        private static OkHttpClientHelper instance = new OkHttpClientHelper();
    }

    public static OkHttpClientHelper getInstance() {
        return OkHttpClientHelperHolder.instance;
    }

    /**
     * 设置带缓存的OkHttpClient
     */
    public OkHttpClient okHttpsCacheClient() {
        if (okHttpClient == null) {
            okHttpClient = okHttpsBuilder().build();
        }
        return okHttpClient;
    }

    private OkHttpClient.Builder okHttpsBuilder() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.cookieJar(new CookieJarImpl(mStore));
        okHttpClientBuilder.connectTimeout(BaseConfig.OKHTTP_CONNECTTIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(BaseConfig.OKHTTP_READTIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(BaseConfig.OKHTTP_READTIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(new HttpCommonInterceptor());
        return okHttpClientBuilder;
    }

    public MemoryCookieStore getMemoryCookieStore() {
        return mStore;
    }
}
