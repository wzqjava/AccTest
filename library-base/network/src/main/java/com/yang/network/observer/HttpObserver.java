package com.yang.network.observer;

import androidx.lifecycle.MutableLiveData;
import com.yang.network.model.BaseResponse;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by yang on 2018/10/31.
 */
public class HttpObserver<T> extends DisposableObserver<BaseResponse<T>> {
    private static final int RESPONSE_OK = 200;
    public static final int RESPONSE_LOG_RETRY = 401;
    public static final int RESPONSE_LOG_OUT1 = 2010;
    private MutableLiveData<BaseResponse<T>> mMutableLiveData;

    public MutableLiveData<BaseResponse<T>> get() {
        return mMutableLiveData;
    }

    public HttpObserver() {
        mMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void onNext(BaseResponse<T> t) {
        mMutableLiveData.setValue(t);
        if (t == null) {
            return;
        }
        int code = t.getCode();
        switch (code) {
            case 0:
            case RESPONSE_OK:
                break;
            case RESPONSE_LOG_RETRY:
                break;
            case RESPONSE_LOG_OUT1:
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("错误");
        mMutableLiveData.setValue(baseResponse);
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

}
