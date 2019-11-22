package com.yang.demo;

import android.app.Application;
import android.os.Handler;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by yang on 2019/11/20.
 */
public class App extends Application {
    public static Handler mHandler=new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         *facebook的Android调试工具Stetho的简单使用
         *https://github.com/facebook/stetho
         * https://blog.csdn.net/qq_32540053/article/details/71079315
         */
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

}
