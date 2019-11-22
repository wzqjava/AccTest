package com.yang.demo.http;

import com.yang.demo.model.LoginInfo;
import com.yang.network.model.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yang on 2019/10/24.
 */
public interface LoginRequestService {

    @POST("login")
    Observable<BaseResponse<LoginInfo>> postLogin(@Body RequestBody requestBody);
}
