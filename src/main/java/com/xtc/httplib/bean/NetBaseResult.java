package com.xtc.httplib.bean;

/* loaded from: classes2.dex */
public class NetBaseResult<T> {
    public static final String SUCCESS = "000001";
    private String code;
    private T data;
    private String desc;
    private NetPushError netPushError;
    private String serverGreyCode;
    private String watchTips;

    public NetPushError getNetPushError() {
        return this.netPushError;
    }

    public void setNetPushError(NetPushError netPushError) {
        this.netPushError = netPushError;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getServerGreyCode() {
        return this.serverGreyCode;
    }

    public void setServerGreyCode(String str) {
        this.serverGreyCode = str;
    }

    public String getWatchTips() {
        return this.watchTips;
    }

    public void setWatchTips(String str) {
        this.watchTips = str;
    }

    public String toString() {
        return "NetBaseResult{code='" + this.code + "', desc='" + this.desc + "', data=" + this.data + ", netPushError=" + this.netPushError + ", serverGreyCode='" + this.serverGreyCode + "', watchTips='" + this.watchTips + "'}";
    }
}