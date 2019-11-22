package com.yang.network.utils;

/**
 * Created by yang on 2019/10/25.
 */
public class Config {
    //渠道号
    public static String channel;
    //是否是测试环境
    public static boolean isTest;

    public static void init() {
        channel = MetaDataUtil.getChannel();
        int mEnvironment = MetaDataUtil.getEnvironment();
        isTest = mEnvironment == 1;
    }
}
