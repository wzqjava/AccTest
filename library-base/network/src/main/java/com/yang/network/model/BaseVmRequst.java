package com.yang.network.model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import com.yang.network.observer.HttpObserver;
import com.yang.network.utils.RetrofitUtil;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by yang on 2019/04/16.
 */
public class BaseVmRequst {

    public RetrofitUtil retrofitTool;
    public Retrofit retrofit;

    public BaseVmRequst() {
        this.retrofitTool = RetrofitUtil.getInstance();
        this.retrofit = retrofitTool.getRetrofit();
    }

    public MutableLiveData doRequest(Observable observable) {
        HttpObserver httpObserver = new HttpObserver();
        retrofitTool.toSubscribe(observable, httpObserver);
        MutableLiveData mutableLiveData = httpObserver.get();
        return mutableLiveData;
    }

}
