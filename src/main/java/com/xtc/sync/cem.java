package com.xtc.sync;

import android.content.Context;
import android.text.TextUtils;
import com.xtc.httplib.bean.AppInfo;
import com.xtc.httplib.bean.DeviceInfo;
import com.xtc.httplib.bean.WatchInfo;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: HttpClient.java */
/* loaded from: classes5.dex */
public abstract class cem {

    /* renamed from: a, reason: collision with root package name */
    private static final String f22893a = ceo.a("HttpClient");

    /* renamed from: a, reason: collision with other field name */
    private long f5234a;

    /* renamed from: a, reason: collision with other field name */
    protected Context f5235a;

    /* renamed from: a, reason: collision with other field name */
    private AppInfo f5236a;

    /* renamed from: a, reason: collision with other field name */
    private DeviceInfo f5237a;

    /* renamed from: b, reason: collision with root package name */
    private String f22894b;

    /* renamed from: a */
    public abstract int mo3069a();

    public <T> T a(String str, Class<T> cls) {
        return null;
    }

    public Call a(Request request) {
        return null;
    }

    public void a(Map<String, String> map) {
    }

    public <T> T b(String str, Class<T> cls) {
        return null;
    }

    /* renamed from: b */
    public abstract String mo3073b();

    public <T> T c(String str, Class<T> cls) {
        return null;
    }

    /* renamed from: c */
    public abstract String mo3075c();

    public <T> T d(String str, Class<T> cls) {
        return null;
    }

    public cem(Context context) {
        // this.f5235a = context.getApplicationContext();
        this.f5237a = new WatchInfo(context);
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m3017a() {
        if (TextUtils.isEmpty(this.f22894b)) {
            this.f22894b = cen.m3018a(this.f5235a);
        }
        return this.f22894b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public cem a(String str) {
        this.f22894b = str;
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m3014a() {
        return this.f5234a;
    }

    public cem a(long j) {
        this.f5234a = j;
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public DeviceInfo m3016a() {
        return this.f5237a;
    }

    public cem a(DeviceInfo deviceInfo) {
        this.f5237a = deviceInfo;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized cem a(AppInfo appInfo) {
        this.f5236a = appInfo;
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized AppInfo m3015a() {
        if (this.f5236a == null) {
            this.f5236a = cen.a(this.f5235a).m3020a();
        }
        return this.f5236a;
    }
}