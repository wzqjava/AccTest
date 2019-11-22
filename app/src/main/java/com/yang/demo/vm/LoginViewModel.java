package com.yang.demo.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yang.demo.http.LoginRequest;
import com.yang.demo.model.LoginInfo;
import com.yang.network.model.BaseResponse;

/**
 * Created by yang on 2019/11/22.
 */
public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseResponse<LoginInfo>> postLogin() {
        return LoginRequest.getInstance().postLogin();
    }
}
