package com.xtc.sync;

// import android.content.ContentProviderClient;
// import android.content.ContentValues;
import android.content.Context;
// import android.net.Uri;
// import android.os.Build;
// import android.os.RemoteException;
import com.google.gson.Gson;
import com.justnothing.xtchttplib.ContextManager;
import com.xtc.httplib.constant.HttpRequestEvent;
// import com.xtc.ultraframework.app.apm.APMDatabaseHelper;
// import com.xtc.web.client.manager.BaseInfoManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Request;

/* compiled from: BigdataClientManager.java */
/* loaded from: classes5.dex */
public class cet {
    public static final String e = "com.xtc.http.bigdata.provider.HttpBigdataProvider";

    /* renamed from: a, reason: collision with other field name */
    private Boolean f5266a;

    /* renamed from: a, reason: collision with other field name */
    public String f5267a;

    /* renamed from: a, reason: collision with other field name */
    private ConcurrentHashMap<String, HttpRequestEvent> f5268a;

    /* renamed from: b, reason: collision with other field name */
    private Boolean f5269b;

    /* renamed from: b, reason: collision with other field name */
    public String f5270b;

    /* renamed from: c, reason: collision with other field name */
    public String f5271c;
    public String d;
    private final String f;

    /* renamed from: a, reason: collision with other field name */
    public static final SimpleDateFormat f5264a = new SimpleDateFormat("yyyyMMddHHmmss");

    /* renamed from: b, reason: collision with other field name */
    public static final SimpleDateFormat f5265b = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat c = new SimpleDateFormat("HH");

    /* renamed from: a, reason: collision with other field name */
    public static final Gson f5263a = new Gson();

    /* renamed from: a, reason: collision with root package name */
    private static Context f22904a = null;

    /* renamed from: a, reason: collision with other field name */
    // public static final Uri f5262a = Uri.parse("content://com.xtc.http.bigdata.provider.HttpBigdataProvider/query");

    /* renamed from: b, reason: collision with root package name */
    // public static final Uri f22905b = Uri.parse("content://com.xtc.http.bigdata.provider.HttpBigdataProvider/insert");

    public static cet a() {
        return a.f22908a;
    }

    public HttpRequestEvent a(String str) {
        return this.f5268a.get(str);
    }

    public void a(String str, HttpRequestEvent httpRequestEvent) {
        if (m3036a() && a().b()) {
            this.f5268a.put(str, httpRequestEvent);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m3035a(String str) {
        if (str == null) {
            return "NOT_INIT";
        }
        dkw.b("BigdataClientManager", "before opt url: " + str);
        String str2 = this.f5270b;
        if (str2 != null && str.contains(str2)) {
            str = str.replace(this.f5270b, "bindnumber");
        }
        String str3 = this.f5267a;
        if (str3 != null && str.contains(str3)) {
            str = str.replace(this.f5267a, "watchId");
        }
        String str4 = this.f5271c;
        if (str4 != null && str.contains(str4)) {
            str = str.replace(this.f5271c, "model");
        }
        String str5 = this.d;
        if (str5 != null && str.contains(str5)) {
            str = str.replace(this.d, "openId");
        }
        String[] split = str.split("/");
        String str6 = str;
        for (int i = 0; i < split.length; i++) {
            if (i > 2) {
                String str7 = split[i];
                boolean z = true;
                if (str7.length() == 10 || str7.length() == 13) {
                    try {
                        Date date = new Date();
                        date.setTime(Long.parseLong(str7));
                        f5265b.format(date);
                    } catch (Exception unused) {
                        z = false;
                    }
                    if (z) {
                        str6 = str6.replace(str7, "timestamp");
                    }
                } else if (str7.length() == 14) {
                    try {
                        f5264a.parse(str7);
                    } catch (Exception unused2) {
                        z = false;
                    }
                    if (z) {
                        str6 = str6.replace(str7, "datetime");
                    }
                } else if (str7.length() > 25) {
                    str6 = str6.replace(str7, "long-str");
                }
            }
        }
        dkw.b("BigdataClientManager", "after opt url: " + str6);
        return str6;
    }

    public void a(Context context, String str, String str2, String str3, String str4) {
        dkw.b("BigdataClientManager", "init() called with: ctx = [" + context + "], watchId = [" + dkv.b(this.f5267a) + "], bindNumber = [" + dkv.a(this.f5270b) + "], watchInnerModel = [" + this.f5271c + "]");
        if (f22904a == null) {
            // f22904a = context.getApplicationContext();
        }
        a(str, str2, str3, str4);
    }

    public void a(String str, String str2, String str3, String str4) {
        dkw.b("BigdataClientManager", "updateAccount() called with: watchId = [" + dkv.b(str) + "], bindNumber = [" + dkv.a(str2) + "], watchInnerModel = [" + str3 + "]");
        if (str == null) {
            throw new RuntimeException("watchid is null");
        }
        this.f5267a = str;
        if (str2 == null) {
            throw new RuntimeException("bindNumber is null");
        }
        this.f5270b = str2;
        if (str3 == null) {
            throw new RuntimeException("watchInnerModel is null");
        }
        this.f5271c = str3;
        if (str4 == null) {
            throw new RuntimeException("openId is null");
        }
        this.d = str4;
    }

    public void a(final HttpRequestEvent httpRequestEvent, final Request request, final int i, final String str, final String str2, final String str3) {
        if (f22904a == null) {
            return;
        }
        httpRequestEvent.setOptUrl(cet.this.m3035a(request.url().toString()));
        httpRequestEvent.setCode(String.valueOf(i));
        httpRequestEvent.setReqContentLength(str);
        httpRequestEvent.setRespContentLength(str2);
        httpRequestEvent.setErrorReason(str3);
        long currentTimeMillis = System.currentTimeMillis();
        httpRequestEvent.setReqDate(cet.f5265b.format(Long.valueOf(currentTimeMillis)));
        httpRequestEvent.setReqHour(cet.c.format(Long.valueOf(currentTimeMillis)));
        cet.a().a(httpRequestEvent);
        cet.this.f5268a.remove(httpRequestEvent.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BigdataClientManager.java */
    /* loaded from: classes5.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final cet f22908a = new cet();

        private a() {
        }
    }

    private cet() {
        this.f = "BigdataClientManager";
        this.f5268a = new ConcurrentHashMap<>();
        this.f5266a = null;
        this.f5269b = null;
        this.f5267a = null;
        this.f5270b = null;
        this.f5271c = null;
        this.d = null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m3036a() {
        return ContextManager.getInstance().getAndroidSdk() >= 24;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d4, code lost:
    
        if (r3 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d6, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e7, code lost:
    
        if (r0 == null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e9, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ec, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e4, code lost:
    
        if (0 == 0) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b() {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xtc.sync.cet.b():boolean");
    }

    public void a(HttpRequestEvent httpRequestEvent) {
        // Context context = f22904a;
        // if (context == null) {
        //     return;
        // }
        // ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(f5262a);
        // try {
        //     try {
        //     } catch (RemoteException e2) {
        //         dkw.e("BigdataClientManager", e2.toString());
        //     }
        //     if (acquireUnstableContentProviderClient == null) {
        //         dkw.e("BigdataClientManager", "not found the bigdata server!!!");
        //         if (acquireUnstableContentProviderClient != null) {
        //             acquireUnstableContentProviderClient.close();
        //             return;
        //         }
        //         return;
        //     }
        //     ContentValues contentValues = new ContentValues();
        //     contentValues.put(cfb.c, f5263a.toJson(httpRequestEvent));
        //     acquireUnstableContentProviderClient.insert(f22905b, contentValues);
        //     if (acquireUnstableContentProviderClient != null) {
        //         acquireUnstableContentProviderClient.close();
        //     }
        // } catch (Throwable th) {
        //     try {
        //         throw th;
        //     } catch (Throwable th2) {
        //         if (acquireUnstableContentProviderClient != null) {
        //             try {
        //                 acquireUnstableContentProviderClient.close();
        //             } catch (Throwable th3) {
        //                 th.addSuppressed(th3);
        //             }
        //         }
        //         throw th2;
        //     }
        // }
    }
}