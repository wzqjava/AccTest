package com.yang.demo.http;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import com.yang.demo.model.LoginInfo;
import com.yang.network.model.BaseResponse;
import com.yang.network.model.BaseVmRequst;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by yang on 2019/10/24.
 */
public class LoginRequest extends BaseVmRequst {
    private LoginRequestService requestService;

    public LoginRequest() {
        super();
        requestService = retrofit.create(LoginRequestService.class);
    }

    public MutableLiveData<BaseResponse<LoginInfo>> postLogin() {
        String json = "{\"loginName\":\"ui\",\"deviceNumber\":\"R23A0304035376938562779202:00:00:00:00:00\",\"appVersion\":\"Test20190411\",\"password\":\"E10ADC3949BA59ABBE56E057F20F883E\",\"deviceName\":\"alps/full_joyasz8735b_3tb_n/joyasz8735b_3tb_n:7.0/NRD90M/1529631635:user/dev-keys\",\"isForce\":\"1\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Observable<BaseResponse<LoginInfo>> observable = requestService.postLogin(requestBody);
        MutableLiveData<BaseResponse<LoginInfo>> mutableLiveData = doRequest(observable);
        return mutableLiveData;
    }

    public static LoginRequest getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static LoginRequest INSTANCE = new LoginRequest();
    }
}
