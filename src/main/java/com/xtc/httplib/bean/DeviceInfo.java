package com.xtc.httplib.bean;

/* loaded from: classes2.dex */
public interface DeviceInfo {
    public static final String BUILD_TYPE_DEBUG = "userdebug";
    public static final String BUILD_TYPE_USER = "user";

    String getAndroidVersion();

    String getBindNumber();

    String getBuildType();

    String getChipId();

    String getMacAddr();

    String getSystemProperty(String str);

    String getWatchVersion();
}