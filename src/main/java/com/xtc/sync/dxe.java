package com.xtc.sync;

import com.justnothing.xtchttplib.ContextManager;

import android.content.Context;
// import android.net.wifi.WifiInfo;
// import android.net.wifi.WifiManager;
import android.text.TextUtils;

/* compiled from: WatchDevice.java */
/* loaded from: classes2.dex */
public class dxe implements dwy {
    public static final String c = "unkown";
    private static final String d = "WatchDevice";

    /* renamed from: a, reason: collision with root package name */
    private Context f25390a;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    public ContextManager contextManager;

    public dxe(Context context, ContextManager contextManager) {
        this.contextManager = contextManager;
        // this.f25390a = context.getApplicationContext();
    }

    @Override // com.xtc.sync.dwy
    public String a() {
        // if (!TextUtils.isEmpty(this.e)) {
        //     return this.e;
        // }
        // this.e = "unkown";
        // WifiManager wifiManager = (WifiManager) this.f25390a.getSystemService("wifi");
        // if (wifiManager == null) {
        //     return this.e;
        // }
        // WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        // if (connectionInfo == null) {
        //     return this.e;
        // }
        // this.e = connectionInfo.getMacAddress();
        // if (TextUtils.isEmpty(this.e)) {
        //     this.e = "unkown";
        // }
        this.e = contextManager.getMacAddr();
        return this.e;
    }

    @Override // com.xtc.sync.dwy
    public String b() {
        if (contextManager == null) {
            dkw.e(d, "contextManager is null in dxe.b()");
            new Exception("contextManager is null").printStackTrace();
            return "unkown";
        }
        if (!TextUtils.isEmpty(this.f)) {
            return this.f;
        }
        // this.f = a(elr.aP);
        this.f = contextManager.getBindNumber();
        if (TextUtils.isEmpty(this.f)) {
            this.f = "unkown";
        }
        return this.f;
    }

    @Override // com.xtc.sync.dwy
    public String c() {
        if (!TextUtils.isEmpty(this.g)) {
            return this.g;
        }
        // this.g = a("ro.boot.xtc.chipid");
        this.g = contextManager.getChipId();
        if (TextUtils.isEmpty(this.g)) {
            this.g = "unkown";
        }
        return this.g;
    }

    @Override // com.xtc.sync.dwy
    public String d() {
        if (!TextUtils.isEmpty(this.h)) {
            return this.h;
        }
        // this.h = a("ro.build.version.release");
        this.h = contextManager.getBuildRelease();
        if (TextUtils.isEmpty(this.h)) {
            this.h = "unkown";
        }
        return this.h;
    }

    @Override // com.xtc.sync.dwy
    public String e() {
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        // this.i = a(elr.aE);
        this.i = contextManager.getSoftVersion();
        if (TextUtils.isEmpty(this.i)) {
            this.i = "unkown";
        }
        return this.i;
    }

    @Override // com.xtc.sync.dwy
    public String a(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (Exception e) {
            dkw.b(d, e);
            return null;
        }
    }

    @Override // com.xtc.sync.dwy
    public String f() {
        // return a(elr.aN);
        return contextManager.getBuildType();
    }
}