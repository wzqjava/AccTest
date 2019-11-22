package com.yang.network.Interceptor;

import android.content.Context;
import com.yang.network.OkHttpClientHelper;
import com.yang.network.cookie.CookieUtils;
import com.yang.network.utils.MLog;
import com.yang.network.utils.MetaDataUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 网络公用拦截器
 * Created by yang on 2019/10/24.
 */
public class HttpCommonInterceptor implements Interceptor {
    private static final String TAG = HttpCommonInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        try {
            Context context = MetaDataUtil.getApp().getApplicationContext();
            Request request = chain.request();
            request = addHeader(request);
            addCookie(context, request);
            response = chain.proceed(request);
            if (true) {
                ResponseBody responseBody = response.body();
                MLog.e(TAG, "网络请求#" + response.request().method() + "#" + response.request().url());
                BufferedSource source = responseBody.source();
                Buffer buffer = source.buffer();
                MediaType contentType = responseBody.contentType();
                Charset charset = contentType != null ? contentType.charset(Charset.forName("UTF-8")) : Charset.forName("UTF-8");
                Charset newCharset = Util.bomAwareCharset(source, charset);
                String string = buffer.clone().readString(newCharset);
                MLog.e(TAG, "网络返回" + string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 添加cookie
     *
     * @param context
     * @param request
     */
    private void addCookie(Context context, Request request) {
        List<Cookie> list = OkHttpClientHelper.getInstance().getMemoryCookieStore().get(request.url());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Cookie cookie = (Cookie) it.next();
            if (cookie.name().equals(CookieUtils.KJsessionidCookie)) {
                CookieUtils.getInstance(context).saveCookie(cookie.value());
            }
        }
    }

    /**
     * 添加header
     *
     * @param request
     * @return
     */
    private Request addHeader(Request request) {
        String uuidStr = UUID.randomUUID().toString();
        String finaluuIDStr = uuidStr.replaceAll("-", "");
        Request.Builder mBuilder = request.newBuilder();
        mBuilder.addHeader("traceId", finaluuIDStr);
        mBuilder.addHeader("Authorization","Bearer cbe8323f-0b9b-4e80-90ab-c508ae2bc092");//添加token
        request = mBuilder.build();
        return request;
    }

}
