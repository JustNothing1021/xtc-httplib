package com.xtc.httplib.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class NetPushError {
    private Integer code;
    private List error;
    private String identify;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    public List getError() {
        return this.error;
    }

    public void setError(List list) {
        this.error = list;
    }
}