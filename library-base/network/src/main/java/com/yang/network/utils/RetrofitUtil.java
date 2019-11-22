package com.yang.network.utils;

import com.yang.network.OkHttpClientHelper;
import com.yang.network.model.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yang on 2018/10/31.
 */
public class RetrofitUtil<T> {

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public RetrofitUtil() {
        okHttpClient = OkHttpClientHelper.getInstance().okHttpsCacheClient();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MetaDataUtil.getBaseUrl())
                .build();
    }

    public  Retrofit getRetrofit(){
        return retrofit;
    }

    public void toSubscribe(Observable o, DisposableObserver<BaseResponse<T>> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public static RetrofitUtil getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final RetrofitUtil instance = new RetrofitUtil();
    }

}
