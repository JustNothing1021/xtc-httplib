package com.xtc.httplib.bean;

import com.xtc.sync.cfr;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class EncryptData {
    private String aesKey;
    private String eebbkKey;
    private int rsaEncryptType;

    public EncryptData(String str, String str2, int i) {
        this.eebbkKey = str;
        this.aesKey = str2;
        this.rsaEncryptType = i;
    }

    public String getEebbkKey() {
        return this.eebbkKey;
    }

    public void setEebbkKey(String str) {
        this.eebbkKey = str;
    }

    public String getAesKey() {
        return TextUtils.isEmpty(this.aesKey) ? cfr.f22951a : this.aesKey;
    }

    public void setAesKey(String str) {
        this.aesKey = str;
    }

    public int getRsaEncryptType() {
        return this.rsaEncryptType;
    }

    public void setRsaEncryptType(int i) {
        this.rsaEncryptType = i;
    }
}