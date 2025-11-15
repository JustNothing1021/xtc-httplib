package com.xtc.system.account.bean;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class HttpConfig {
    private String ae;
    private String encSwitch;
    private String grey;
    private String httpHeadParam;
    private String rsaPublicKey;
    private String selfRsaPublicKey;
    private int ts;

    public HttpConfig(String str, String str2, String str3, String str4, String str5, int i, String str6) {
        this.grey = str;
        this.encSwitch = str2;
        this.rsaPublicKey = str3;
        this.selfRsaPublicKey = str4;
        this.httpHeadParam = str5;
        this.ts = i;
        this.ae = str6;
    }

    public String getGrey() {
        return this.grey;
    }

    public void setGrey(String str) {
        this.grey = str;
    }

    public String getEncSwitch() {
        return this.encSwitch;
    }

    public void setEncSwitch(String str) {
        this.encSwitch = str;
    }

    public String getRsaPublicKey() {
        return this.rsaPublicKey;
    }

    public void setRsaPublicKey(String str) {
        this.rsaPublicKey = str;
    }

    public String getSelfRsaPublicKey() {
        return this.selfRsaPublicKey;
    }

    public void setSelfRsaPublicKey(String str) {
        this.selfRsaPublicKey = str;
    }

    public String getHttpHeadParam() {
        return this.httpHeadParam;
    }

    public void setHttpHeadParam(String str) {
        this.httpHeadParam = str;
    }

    public int getTs() {
        return this.ts;
    }

    public void setTs(int i) {
        this.ts = i;
    }

    public String getAe() {
        return this.ae;
    }

    public void setAe(String str) {
        this.ae = str;
    }

    public String toString() {
        return "HttpConfig{grey='" + this.grey + "', encSwitch='" + this.encSwitch + "', rsaPublicKey='" + this.rsaPublicKey + "', selfRsaPublicKey='" + this.selfRsaPublicKey + "', httpHeadParam='" + this.httpHeadParam + "', ts ='" + this.ts + "', ae null='" + TextUtils.isEmpty(this.ae) + "'}";
    }
}