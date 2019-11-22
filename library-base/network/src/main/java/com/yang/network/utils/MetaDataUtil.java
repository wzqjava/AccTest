package com.yang.network.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yang on 2019/10/23.
 */
public class MetaDataUtil {
    private static final String TAG = MetaDataUtil.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication == null) {
            sApplication = getApplicationByReflect();
        }
        return sApplication;
    }

    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * 服务环境
     *
     * @return
     */
    public static int getEnvironment() {
        try {
            ApplicationInfo appInfo = getApp().getPackageManager().
                    getApplicationInfo(getApp().getPackageName(),
                            PackageManager.GET_META_DATA);
            int serviceEnvironment = appInfo.metaData.getInt("DADA_ENVIRONMENT");
            return serviceEnvironment;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 日志是否打印
     *
     * @return
     */
    public static boolean isLogEnable() {
        try {
            ApplicationInfo appInfo = getApp().getPackageManager().
                    getApplicationInfo(getApp().getPackageName(),
                            PackageManager.GET_META_DATA);
            int LOG_ENABLE = appInfo.metaData.getInt("LOG_ENABLE");
            return LOG_ENABLE == 1;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 渠道
     *
     * @return
     */
    public static String getChannel() {
        try {
            ApplicationInfo appInfo = getApp().getPackageManager().
                    getApplicationInfo(getApp().getPackageName(),
                            PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("CHANNALID");
            return channel;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取base url
     *
     * @return
     */
    public static String getBaseUrl() {
        String baseUrl = "http://ppe.tope365.com/common-interface/";
/*        int serviceEnvironment = getEnvironment();
        switch (serviceEnvironment) {
            //测试
            case 1:
                baseUrl = "http://10.0.3.200:8919";
                break;
            //线上
            case 2:
                baseUrl = "http://10.0.3.200:8919";
                break;
            default:
                break;
        }*/
        return baseUrl;
    }


}
