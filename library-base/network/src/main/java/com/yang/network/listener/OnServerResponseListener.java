package com.yang.network.listener;


import com.yang.network.model.BaseResponse;

/**
 * Created by yang on 2019/10/24.
 */
public interface OnServerResponseListener<T> {
    /**
     * 服务器成功返回
     * @param what
     * @param isQualifed 返回的数据是否合格
     * @param t
     */
    void success(int what, boolean isQualifed, BaseResponse<T> t);

    /**
     * 返回错误
     *
     * @param what
     * @param throwable 错误类型有可能是移动端也有可能是后端
     */
    void error(int what, Throwable throwable);

    /**
     * 重连
     */
    void reTry(int what);
}
