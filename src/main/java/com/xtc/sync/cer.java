package com.xtc.sync;

import android.content.Context;
// import android.content.Intent;
// import com.xtc.utils.storage.SharedManager;
import java.util.concurrent.TimeUnit;

/* compiled from: HttpAuthManager.java */
/* loaded from: classes5.dex */
public class cer {

    /* renamed from: a, reason: collision with other field name */
    public static final String f5251a = "000016";

    /* renamed from: b, reason: collision with root package name */
    public static final String f22901b = "com.xtc.watch.http.AUTH_ERROR";
    private static final String d = "AuthErrorNotifyTime";

    /* renamed from: a, reason: collision with other field name */
    private Context f5253a;

    /* renamed from: a, reason: collision with other field name */
    // private SharedManager f5254a;
    private static final String c = ceo.a("HttpAuthManager");

    /* renamed from: a, reason: collision with root package name */
    private static final long f22900a = TimeUnit.SECONDS.toMillis(30);

    /* renamed from: a, reason: collision with other field name */
    private static volatile cer f5250a = null;

    /* renamed from: b, reason: collision with other field name */
    private long f5255b = 0;

    /* renamed from: a, reason: collision with other field name */
    private int f5252a = 0;

    /* renamed from: a, reason: collision with other method in class */
    public static cer m3029a() {
        if (f5250a == null) {
            synchronized (cer.class) {
                if (f5250a == null) {
                    f5250a = new cer();
                }
            }
        }
        return f5250a;
    }

    public void a(String str, int i) {
        // if (Objects.equals(ces.f5257a, str)) {
            // ces.m3032a().a(true);
            // return;
        // }
        // if (Objects.equals(f5251a, str)) {
        //     if (this.f5255b == 0) {
        //         this.f5255b = a();
        //     }
        //     long currentTimeMillis = System.currentTimeMillis();
        //     if (currentTimeMillis < this.f5255b) {
        //         this.f5255b = 0L;
        //     }
        //     long pow = ((long) Math.pow(2.0d, this.f5252a)) * f22900a;
        //     if (currentTimeMillis - this.f5255b < pow) {
        //         dkw.b(c, "checkAuth is in limit: lastAuthErrorNotifyTime = [" + this.f5255b + "]， limitInterval = [" + pow + "]");
        //         return;
        //     }
        //     this.f5252a++;
        //     this.f5255b = currentTimeMillis;
        //     a(currentTimeMillis);
        //     m3030a();
        //     ceu.a(this.f5253a, i);
        // }
    }

    // private long a() {
    //     Context context = this.f5253a;
    //     if (context == null) {
    //         return 0L;
    //     }
    //     if (this.f5254a == null) {
    //         this.f5254a = SharedManager.getInstance(context);
    //     }
    //     return this.f5254a.getLong(d, 0L);
    // }

    // private void a(long j) {
    //     Context context = this.f5253a;
    //     if (context == null) {
    //         return;
    //     }
    //     if (this.f5254a == null) {
    //         this.f5254a = SharedManager.getInstance(context);
    //     }
    //     this.f5254a.saveLong(d, j);
    // }

    /* renamed from: a, reason: collision with other method in class */
    private void m3030a() {
        // if (this.f5253a == null) {
        //     return;
        // }
        // dkw.b(c, "sendAuthErrorBroadcast");
        // Intent intent = new Intent(f22901b);
        // intent.setPackage("com.xtc.i3launcher");
        // this.f5253a.sendBroadcast(intent);
    }

    /* renamed from: a, reason: collision with other method in class */
    public Context m3031a() {
        return this.f5253a;
    }

    public void a(Context context) {
        this.f5253a = context;
    }
}