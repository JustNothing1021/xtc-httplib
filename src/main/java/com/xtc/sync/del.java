package com.xtc.sync;

import android.content.Context;
// import android.content.Intent;
// import android.content.IntentFilter;
import android.text.TextUtils;
// import com.xtc.bigdata.common.utils.AbsAsyncBroadcastReceiver;
// import com.xtc.im.core.common.LogTag;
import com.xtc.system.account.bean.HttpConfig;

/* compiled from: RsaPublicKeyManager.java */
/* loaded from: classes5.dex */
public class del {

    /* renamed from: a, reason: collision with root package name */
    private static volatile del f24353a = null;

    /* renamed from: a, reason: collision with other field name */
    public static final String f6705a = "com.xtc.initservice.action.NET_PARAM_UPDATE";
    private static final String c = ("RsaPublicKeyManager");

    /* renamed from: a, reason: collision with other field name */
    // private dek f6707a;

    /* renamed from: a, reason: collision with other field name */
    // private a f6708a;

    /* renamed from: b, reason: collision with root package name */
    private String f24354b = "UNKOWN";

    /* renamed from: a, reason: collision with other field name */
    private int f6706a = 2;

    /* compiled from: RsaPublicKeyManager.java */
    /* loaded from: classes5.dex */
    interface b {

        /* renamed from: a, reason: collision with root package name */
        public static final int f24356a = 0;

        /* renamed from: b, reason: collision with root package name */
        public static final int f24357b = 1;
        public static final int c = 2;
        public static final int d = 3;
    }

    public String a() {
        return this.f24354b;
    }

    private del(Context context) {
        b(context);
        // m3765a(context);
    }

    public static del a(Context context) {
        if (f24353a == null) {
            synchronized (del.class) {
                if (f24353a == null) {
                    f24353a = new del(context);
                }
            }
        }
        return f24353a;
    }

    /* renamed from: a, reason: collision with other method in class */
    // public void m3765a(Context context) {
    //     if (this.f6708a == null) {
    //         this.f6708a = new a();
    //     }
    //     try {
    //         this.f6708a.b(context);
    //     } catch (Exception unused) {
    //     }
    //     this.f6708a.a(context);
    // }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m3766a() {
        int i = this.f6706a;
        return i == 2 || i == 3;
    }

    // public void a(dek dekVar) {
    //     this.f6707a = dekVar;
    // }

    public void b(Context context) {
        dwx a2 = dwx.a(context);
        synchronized (this) {
            this.f24354b = a2.g();
            this.f6706a = a2.m4420a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RsaPublicKeyManager.java */
    /* loaded from: classes5.dex */
    // public class a extends AbsAsyncBroadcastReceiver {
    //     private a() {
    //     }

    //     @Override // com.xtc.bigdata.common.utils.AbsAsyncBroadcastReceiver
    //     public void a(Context context, Intent intent) {
    //         String action = intent.getAction();
    //         dkw.c(del.c, "receive app info update receiver,action:" + action);
    //         if (TextUtils.isEmpty(action)) {
    //             dkw.e(del.c, "action is null");
    //             return;
    //         }
    //         if (action.equals(del.f6705a)) {
    //             HttpConfig a2 = dxa.a(context);
    //             if (a2 == null) {
    //                 dkw.d(del.c, "refreshSelfRsaPublicKeyAndId: httpConfig is null");
    //                 return;
    //             }
    //             String ae = a2.getAe();
    //             if (TextUtils.isEmpty(ae)) {
    //                 dkw.d(del.c, "refreshSelfRsaPublicKeyAndId: newSelfRsaPublicKey is empty");
    //                 return;
    //             }
    //             dkw.b(del.c, "refreshSelfRsaPublicKeyAndId");
    //             del.this.f24354b = ae;
    //             del.this.f6706a = a2.getTs();
    //             if (del.this.f6706a != 1 || del.this.f6707a == null) {
    //                 return;
    //             }
    //             del.this.f6707a.a();
    //         }
    //     }

    //     public void a(Context context) {
    //         Context applicationContext = context.getApplicationContext();
    //         IntentFilter intentFilter = new IntentFilter();
    //         intentFilter.addAction(del.f6705a);
    //         applicationContext.registerReceiver(this, intentFilter);
    //     }

    //     public void b(Context context) {
    //         context.getApplicationContext().unregisterReceiver(this);
    //     }
    // }
}