package com.xtc.httplib.constant;

import android.content.Context;

/* loaded from: classes2.dex */
public class TcpTimeoutConfig {
    private int keepAliveDuration = 14;
    private int maxIdleConnections = 2;

    public TcpTimeoutConfig() {
        this.keepAliveDuration = 14;
        this.maxIdleConnections = 2;
    }

    public TcpTimeoutConfig(Context context) {
        this.keepAliveDuration = 14;
        this.maxIdleConnections = 2;
    }

    public int getKeepAliveDuration() {
        return this.keepAliveDuration;
    }

    public void setKeepAliveDuration(int i) {
        this.keepAliveDuration = i;
    }

    public int getMaxIdleConnections() {
        return this.maxIdleConnections;
    }

    public void setMaxIdleConnections(int i) {
        this.maxIdleConnections = i;
    }
}