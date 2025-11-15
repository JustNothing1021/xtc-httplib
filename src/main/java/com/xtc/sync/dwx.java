package com.xtc.sync;

import android.content.Context;
import android.text.TextUtils;
import com.xtc.system.account.bean.HttpConfig;
import com.xtc.utils.security.XtcSecurity;

/* compiled from: AppInfoImpl.java */
/* loaded from: classes2.dex */
public class dwx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile dwx f25368a = null;

    /* renamed from: a, reason: collision with other field name */
    private static final String f8032a = "AppInfoImpl";

    /* renamed from: b, reason: collision with root package name */
    private static final String f25369b = ":";

    /* renamed from: a, reason: collision with other field name */
    // private long f8033a;

    /* renamed from: a, reason: collision with other field name */
    private a f8034a;

    /* renamed from: a, reason: collision with other field name */
    private HttpConfig f8035a;
    private String c;
    private String d;
    private String e;

    /* compiled from: AppInfoImpl.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(boolean z);
    }

    public dwx(Context context, a aVar) {
        this.f8034a = aVar;
        b(context);
        m4419a(context);
    }

    public static dwx a(Context context) {
        if (f25368a == null) {
            synchronized (dwx.class) {
                if (f25368a == null) {
                    f25368a = new dwx(context, null);
                }
            }
        }
        return f25368a;
    }

    private void a() {
        // this.f8033a = dxc.a();
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m4419a(Context context) {
        this.e = new dxe(context).e();
        if ("unkown".equals(this.e)) {
            this.e = null;
            return;
        }
        this.e = "W_" + this.e;
    }

    private void b(Context context) {
        this.f8035a = dxa.a(context);
        a aVar = this.f8034a;
        if (aVar != null) {
            aVar.a(this.f8035a != null);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m4422a() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getGrey();
        }
        return null;
    }

    public String b() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getRsaPublicKey();
        }
        return null;
    }

    public String c() {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        String b2 = XtcSecurity.b(e);
        if (TextUtils.isEmpty(b2) || !b2.contains(":")) {
            dkw.b(f8032a, "setSelfRsaPublicKeyAndId: secretKeyDecrypt error");
            return null;
        }
        String[] split = b2.split(":");
        if (split.length < 2) {
            return null;
        }
        return split[1];
    }

    public String d() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getEncSwitch();
        }
        return null;
    }

    public String e() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getSelfRsaPublicKey();
        }
        return null;
    }

    public String f() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getHttpHeadParam();
        }
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public int m4420a() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getTs();
        }
        return 0;
    }

    public String g() {
        HttpConfig httpConfig = this.f8035a;
        if (httpConfig != null) {
            return httpConfig.getAe();
        }
        return null;
    }

    public String h() {
        return this.e;
    }

    /* renamed from: a, reason: collision with other method in class */
    // public Long m4421a() {
    //     if (this.f8033a == 0) {
    //         a();
    //     }
    //     return Long.valueOf(this.f8033a);
    // }
}