package com.xtc.sync;

// import android.content.ComponentCallbacks;
import android.content.Context;
// import android.content.Intent;
// import android.content.IntentFilter;
// import android.content.res.Configuration;
// import android.net.Uri;
// import android.os.SystemClock;
import android.text.TextUtils;

import com.justnothing.xtchttplib.ContextManager;
import com.xtc.httplib.bean.AppInfo;
import com.xtc.im.transpond.TranspondAdapter;
import com.xtc.sync.cel;
import com.xtc.system.account.bean.AppInfoBase;
import com.xtc.system.account.bean.HttpConfig;
// import com.xtc.system.account.utils.AbsAsyncBroadcastReceiver;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: HttpManager.java */
/* loaded from: classes5.dex */
public class cen {

    /* renamed from: a, reason: collision with root package name */
    private static final long f22895a = 5000;

    /* renamed from: a, reason: collision with other field name */
    private static volatile cen f5238a = null;
    private static final String c = ceo.a("HttpManager");
    private static final String d = "content://com.xtc.provider/BaseDataProvider/watchId/1";

    /* renamed from: a, reason: collision with other field name */
    protected Context f5239a;

    /* renamed from: a, reason: collision with other field name */
    private cem f5240a;

    /* renamed from: a, reason: collision with other field name */
    private a f5241a;

    /* renamed from: a, reason: collision with other field name */
    private cga f5242a;

    /* renamed from: a, reason: collision with other field name */
    private cgb f5243a;

    /* renamed from: a, reason: collision with other field name */
    public String f5244a;

    /* renamed from: b, reason: collision with root package name */
    private long f22896b;

    /* renamed from: b, reason: collision with other field name */
    public String f5245b;

    public static cen a(Context context) {
        if (f5238a == null) {
            synchronized (cen.class) {
                if (f5238a == null) {
                    f5238a = new cen(context);
                }
            }
        }
        return f5238a;
    }

    private cen(Context context) {
        // this.f5239a = context.getApplicationContext();
        d();
        this.f5241a = new a();
        this.f5241a.a(this.f5239a);
        cer.m3029a().a(context);
        // ces.m3032a().a(context);
        // cfo.a().a(context);
    }

    private void d() {
        m3019a(this.f5239a);
        this.f5240a = new cft(this.f5239a);
        // this.f5239a.registerComponentCallbacks(new ComponentCallbacks() { // from class: com.xtc.i3launcher.cen.1
        //     @Override // android.content.ComponentCallbacks
        //     public void onLowMemory() {
        //     }

        //     @Override // android.content.ComponentCallbacks
        //     public void onConfigurationChanged(Configuration configuration) {
        //         cen.this.c("onConfigurationChanged");
        //     }
        // });
    }

    public void setOnGetAppInfoListener(cga cgaVar) {
        this.f5242a = cgaVar;
    }

    public void setOnHttpSuccessMonitorListener(cgb cgbVar) {
        this.f5243a = cgbVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m3024a() {
        if (this.f5243a == null) {
            return;
        }
        long elapsedRealtime = System.currentTimeMillis();
        if (elapsedRealtime - this.f22896b < 5000) {
            return;
        }
        this.f22896b = elapsedRealtime;
        this.f5243a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a, reason: collision with other method in class */
    public AppInfo m3020a() {
        dwx a2 = dwx.a(this.f5239a);
        AppInfo appInfo = new AppInfo();
        appInfo.setEncSwitch(a2.d());
        appInfo.setGrey(a2.m4422a());
        appInfo.setRsaPublicKey(a2.b());
        appInfo.setVersion(a2.h());
        appInfo.setSelfRsaPublicKeyAndId(a2.e());
        appInfo.setHttpHeadParam(a2.f());
        appInfo.setEncryptEebbkKey(a2.g());
        appInfo.setHttpTokenState(a2.m4420a());
        return appInfo;
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m3025b() {
        this.f5241a.b(this.f5239a);
    }

    /* renamed from: a, reason: collision with other method in class */
    public cem m3021a() {
        return this.f5240a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Executor m3023a() {
        cem cemVar = this.f5240a;
        if (cemVar instanceof cft) {
            return ((cft) cemVar).m3071a();
        }
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m3019a(Context context) {
        try {
            dgg.a((TranspondAdapter) Class.forName("com.xtc.im.transpond.TranspondAdapterImpl").getConstructor(Context.class).newInstance(context));
            dkw.b(c, "register http bridge successful");
        } catch (Throwable unused) {
        }
    }

    public void a(boolean z, List<String> list) {
        dkw.c(c, "setAllTranspond allTranspond = " + z);
        cgc.f5366a = z;
        if (list == null || list.size() == 0) {
            return;
        }
        if (z) {
            cgc.f5367b.addAll(list);
        } else {
            cgc.f22979a.addAll(list);
        }
    }

    public void a(boolean z) {
        dkw.c(c, "setUseHttps use = " + z);
        // if (z) {
        //     cfj.f22939a = cel.e.f22888a;
        // } else {
        //     cfj.f22939a = cel.e.f22889b;
        // }
    }

    public void a(int i, int i2, int i3) {
        dkw.c(c, "setTimeOut connectTimeout = " + i + ",writeTimeout = " + i2 + ",readTimeout = " + i3);
        cft.f22955a = i;
        cft.f22956b = i2;
        cft.c = i3;
    }

    public void a(long j, List<String> list) {
        dkw.c(c, "setBootLimitTime limitTime = " + j);
        // cfz.f22975a = j;
        // if (list == null || list.size() == 0) {
        //     return;
        // }
        // cfz.f5355a.addAll(list);
    }

    /* renamed from: c, reason: collision with other method in class */
    public void m3026c() {
        // cfz.f5360c = true;
    }

    public void b(boolean z) {
        dkw.c(c, "setOpenMonitor openMonitor = " + z);
        // cfz.f5357a = z;
    }

    public void c(boolean z) {
        dkw.c(c, "setHaveConfirmPermission haveConfirmPermission = " + z);
        // cfz.f5359b = z;
    }

    public void a(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        dkw.c(c, "setNoNeedMonitor noNeedMonitor = " + list.size());
        // cfz.f5356a.addAll(list);
    }

    @Deprecated
    public void a(String str) {
        dkw.c(c, "setAcceptLanguage acceptLanguage = " + str);
        this.f5244a = str;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m3022a() {
        if (TextUtils.isEmpty(this.f5244a)) {
            c("init");
        }
        return this.f5244a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        dkw.c(c, "refreshLanguage reason = " + str);
        String a2 = ContextManager.getInstance().getLanguage();
        if (a2.startsWith("th")) {
            a2 = "th";
        }
        this.f5244a = a2;
    }

    @Deprecated
    public void b(String str) {
        dkw.c(c, "setWatchTimeZone watchTimeZone = " + str);
        this.f5245b = str;
    }

    public String b() {
        if (TextUtils.isEmpty(this.f5245b)) {
            this.f5245b = ContextManager.getInstance().getTimeZone();
            dkw.c(c, "getWatchTimeZone is empty, reset watchTimeZone = " + this.f5245b);
        }
        return this.f5245b;
    }

    /* compiled from: HttpManager.java */
    /* loaded from: classes5.dex */
    class a /* extends AbsAsyncBroadcastReceiver */ {
        private a() {
        }

        // @Override // com.xtc.system.account.utils.AbsAsyncBroadcastReceiver
        public void onReceiveAsync(Context context, /* Intent */ Object intent) {
            // String action = intent.getAction();
            // dkw.c("receive app info update receiver,action:" + action);
            // if (TextUtils.isEmpty(action)) {
            //     dkw.e("action is null");
            //     return;
            // }
            // if (action.equals(del.f6705a)) {
            //     String stringExtra = intent.getStringExtra(AppInfoBase.KEY_RSA);
            //     String stringExtra2 = intent.getStringExtra(AppInfoBase.KEY_ENC_SWITCH);
            //     String stringExtra3 = intent.getStringExtra(AppInfoBase.KEY_GREY);
            //     String stringExtra4 = intent.getStringExtra(AppInfoBase.KEY_SELF_RSA_KEY);
            //     String stringExtra5 = intent.getStringExtra(AppInfoBase.KEY_HTTP_HEAD);
            //     AppInfo m3015a = cen.this.f5240a.m3015a();
            //     if (m3015a == null) {
            //         m3015a = new AppInfo();
            //         cen.this.f5240a.a(m3015a);
            //     }
            //     if (!TextUtils.isEmpty(stringExtra)) {
            //         m3015a.setRsaPublicKey(stringExtra);
            //     }
            //     if (!TextUtils.isEmpty(stringExtra2)) {
            //         m3015a.setEncSwitch(stringExtra2);
            //     }
            //     if (!TextUtils.isEmpty(stringExtra3)) {
            //         m3015a.setGrey(stringExtra3);
            //     }
            //     if (!TextUtils.isEmpty(stringExtra4)) {
            //         a(m3015a);
            //     }
            //     if (TextUtils.isEmpty(stringExtra5)) {
            //         return;
            //     }
            //     m3015a.setHttpHeadParam(stringExtra5);
            //     return;
            // }
            // if (action.equals(bla.l)) {
            //     cen.this.f5240a.a(cen.m3018a(context));
            // }
        }

        private void a(AppInfo appInfo) {
            if (appInfo == null) {
                dkw.d(cen.c, "refreshSelfRsaPublicKeyAndId: appInfo is null");
                return;
            }
            HttpConfig a2 = dxa.a(cen.this.f5239a);
            if (a2 == null) {
                dkw.d(cen.c, "refreshSelfRsaPublicKeyAndId: httpConfig is null");
                return;
            }
            String selfRsaPublicKey = a2.getSelfRsaPublicKey();
            if (TextUtils.isEmpty(selfRsaPublicKey)) {
                appInfo.setEncryptEebbkKey(a2.getAe());
                appInfo.setHttpTokenState(a2.getTs());
            } else {
                appInfo.setSelfRsaPublicKeyAndId(selfRsaPublicKey);
            }
            dkw.b(cen.c, "refreshSelfRsaPublicKeyAndId");
        }

        public void a(Context context) {
            // Context applicationContext = context.getApplicationContext();
            // IntentFilter intentFilter = new IntentFilter();
            // intentFilter.addAction(del.f6705a);
            // intentFilter.addAction(bla.l);
            // applicationContext.registerReceiver(this, intentFilter);
        }

        public void b(Context context) {
            // context.getApplicationContext().unregisterReceiver(this);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m3018a(Context context) {
        // return context.getContentResolver().getType(Uri.parse(d));
        return ContextManager.getInstance().getWatchId();
    }

    public Context a() {
        return this.f5239a;
    }
}