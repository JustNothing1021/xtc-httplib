package com.xtc.sync;

// import android.app.AlarmManager;
// import android.app.PendingIntent;
// import android.content.BroadcastReceiver;
import android.content.Context;
// import android.content.Intent;
// import android.content.IntentFilter;
// import android.os.Build;
// import android.os.RemoteException;
// import android.os.SystemClock;
// import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.justnothing.xtchttplib.ContextManager;
import com.xtc.httplib.bean.EncryptData;
import com.xtc.httplib.bean.NetBaseResult;
import com.xtc.httplib.constant.HttpRequestEvent;
import com.xtc.im.transpond.ITranspondCallback;
import com.xtc.utils.encode.JSONUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/* compiled from: PreprocessorInterceptor.java */
@Deprecated
/* loaded from: classes5.dex */
public class cgc extends cfr {

    /* renamed from: a, reason: collision with other field name */
    // private AlarmManager f5368a;

    /* renamed from: a, reason: collision with other field name */
    private a f5369a;

    /* renamed from: a, reason: collision with other field name */
    private CopyOnWriteArrayList<b> f5370a;

    /* renamed from: a, reason: collision with other field name */
    private AtomicInteger f5371a;

    /* renamed from: a, reason: collision with other field name */
    private AtomicLong f5372a;

    /* renamed from: b, reason: collision with root package name */
    private static final String f22980b = ceo.a("PreprocessorInterceptor");

    /* renamed from: a, reason: collision with other field name */
    public static boolean f5366a = false;

    /* renamed from: a, reason: collision with root package name */
    public static final List<String> f22979a = List.of("okii");

    /* renamed from: b, reason: collision with other field name */
    public static final List<String> f5367b = new ArrayList();

    public ContextManager contextManager;
    public elw watchModelUtil;

    private Response a(Interceptor.Chain chain) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public cgc(Context context, ContextManager contextManager) {
        this.contextManager = contextManager;
        this.f5372a = new AtomicLong(0L);
        this.f5371a = new AtomicInteger(0);
        this.f5370a = new CopyOnWriteArrayList<>();
        this.f5369a = null;
        // this.f5368a = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    /* renamed from: a, reason: collision with other method in class */
    private synchronized void m3080a() {
        if (this.f5369a != null) {
            return;
        }
        this.f5369a = new a();
        this.f5369a.a(this.f5323a);
    }

    @Override // com.xtc.sync.cfr, okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        long elapsedRealtime = System.currentTimeMillis();
        m3080a();
        Request request = chain.request();
        // Response a2 = a(chain);
        // if (a2 != null) {
        //     a(request, elapsedRealtime);
        //     return a2;
        // }
        // Response b2 = b(chain);
        // if (b2 != null) {
        //     a(request, elapsedRealtime);
        //     return b2;
        // }
        // a(request, elapsedRealtime);
        // RequestBody body = request.body();
        // Buffer buffer = new Buffer();
        // if (body != null) {
        //     body.writeTo(buffer);
        // }
        // String mo9347a = buffer.readString(Charset.forName("UTF-8"));
        // EncryptData a3 = cfu.a(cen.a(this.f5323a).m3021a(), request);
        // String eebbkKey = a3.getEebbkKey();
        // int rsaEncryptType = a3.getRsaEncryptType();
        // String aesKey = a3.getAesKey();
        // boolean z = rsaEncryptType != 0;
        // String a4 = cfu.a(mo9347a, eebbkKey, z, aesKey);
        // String str = request.url().toString();
        // byte[] bytes = a4 == null ? null : a4.getBytes();
        // Map<String, String> a5 = cfu.a(this.f5323a, request, str, mo9347a.getBytes(), eebbkKey, z, rsaEncryptType, aesKey);
        // Headers a6 = request.headers();
        // for (Map.Entry<String, String> entry : a5.entrySet()) {
        //     a6 = a6.newBuilder().add(entry.getKey(), entry.getValue()).build();
        // }
        // Request.Builder newBuilder = request.newBuilder()
        //                         .headers(a6)
        //                         .method(request.method(), RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bytes));
        // request = newBuilder.build();
        a(request, elapsedRealtime);
        return chain.proceed(request);
    }

    private static void a(Request request, long j) {
        HttpRequestEvent a2 = cet.a().a(request.header(cfb.f));
        if (a2 != null) {
            a2.setInterceptorPreprocessorTime(String.valueOf(System.currentTimeMillis() - j));
        }
    }

    private Response b(Interceptor.Chain chain) throws IOException {
        long elapsedRealtime = System.currentTimeMillis();
        // if (elapsedRealtime - this.f5372a.get() < 600000) {
        //     dkw.d(f22980b, "createFromTranspond not in limit time !");
        //     return null;
        // }
        Request request = chain.request();
        String httpUrl = request.url().toString();
        if (!m3081a(httpUrl)) {
            return null;
        }
        this.f5372a.set(elapsedRealtime);
        b bVar = new b();
        this.f5370a.add(bVar);
        bVar.f22983a = a();
        Map<String, String> a2 = a(request, httpUrl, bVar);
        // PendingIntent a3 = a(this.f5323a, bVar);
        // a(21000L, a3);
        try {
            bVar.a(20000L);
        } catch (InterruptedException e) {
            dkw.b(f22980b, e);
        }
        // a(a3);
        return a(request, httpUrl, a2, bVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m3081a(String str) {
        boolean z;
        if (f5366a) {
            Iterator<String> it = f5367b.iterator();
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    dkw.c(f22980b, "NEED_TRANSPOND no need Transpond,allTranspond = " + f5366a);
                    return false;
                }
            }
        } else {
            Iterator<String> it2 = f22979a.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                if (str.contains(it2.next())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return false;
            }
            dkw.c(f22980b, "NEED_TRANSPOND need Transpond,allTranspond = " + f5366a);
        }
        return true;
    }

    private Map<String, String> a(Request request, String str, ITranspondCallback iTranspondCallback) throws IOException {
        int a2 = a2(request.method());
        RequestBody body = request.body();
        // how howVar = new how();
        Buffer buffer = new Buffer();
        if (body != null) {
            body.writeTo(buffer);
        }
        String mo9347a = buffer.readString(Charset.forName("UTF-8"));
        if (TextUtils.isEmpty(mo9347a)) {
            dkw.d(f22980b, "requestByTranspond is empty");
        }
        EncryptData a3 = cfu.a(cen.a(contextManager).m3021a(), request);
        String eebbkKey = a3.getEebbkKey();
        int rsaEncryptType = a3.getRsaEncryptType();
        String aesKey = a3.getAesKey();
        boolean z = rsaEncryptType != 0;
        String a4 = cfu.a(mo9347a, eebbkKey, z, aesKey, watchModelUtil);
        byte[] bytes = a4 == null ? null : a4.getBytes();
        Map<String, String> a5 = cfu.a(this.f5323a, request, str, mo9347a.getBytes(), eebbkKey, z, rsaEncryptType, aesKey, watchModelUtil, contextManager);
        dkw.c(f22980b, "requestByTranspond url = " + str + ", method = " + request.method() + ", requestBody = " + mo9347a);
        dgg.a(str, a2, JSONUtil.toJSON(a5).getBytes(), bytes, iTranspondCallback);
        return a5;
    }

    private Response a(Request request, String str, Map<String, String> map, b bVar) {
        byte[] bArr;
        NetBaseResult netBaseResult;
        if (bVar.f5375a || !bVar.f22984b || (bArr = bVar.f5376a) == null || bArr.length <= 0 || (netBaseResult = (NetBaseResult) JSONUtil.fromJSON(cfu.a(this.f5323a, map, new String(bArr), request, contextManager), NetBaseResult.class)) == null) {
            return null;
        }
        ResponseBody create = ResponseBody.create(MediaType.parse(cel.a.f), JSONUtil.toJSON(netBaseResult));
        dkw.c(f22980b, "createTranspondResponse url = " + str + ", method = " + request.method() + ", responseBody = " + create);
        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).message(str + " Http Response Successful").code(Integer.parseInt(cfb.g)).body(create).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
    }

    private int a2(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("get")) {
            return 1;
        }
        if (lowerCase.equals("post")) {
            return 2;
        }
        if (lowerCase.equals("delete")) {
            return 3;
        }
        if (lowerCase.equals("put")) {
            return 4;
        }
        dkw.e(f22980b, "unknown http method: " + lowerCase);
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PreprocessorInterceptor.java */
    /* loaded from: classes5.dex */
    public class b extends ITranspondCallback.a {

        /* renamed from: a, reason: collision with other field name */
        byte[] f5376a;

        /* renamed from: a, reason: collision with root package name */
        int f22983a = -1;

        /* renamed from: a, reason: collision with other field name */
        boolean f5375a = false;

        /* renamed from: b, reason: collision with root package name */
        boolean f22984b = false;

        b() {
        }

        public synchronized void a(long j) throws InterruptedException {
            // wait(j);
        }

        public synchronized void a() {
            this.f5375a = true;
            notify();
        }

        @Override // com.xtc.im.transpond.ITranspondCallback
        public synchronized void onSuccess(byte[] bArr, int i) /* throws RemoteException */ {
            if (bArr != null) {
                if (bArr.length > 0) {
                    this.f5376a = bArr;
                }
            }
            this.f22984b = true;
            notify();
        }

        @Override // com.xtc.im.transpond.ITranspondCallback
        public synchronized void onError(String str) {
            dkw.e(cgc.f22980b, "http transmit failed:" + str);
            this.f22984b = false;
            notify();
        }
    }

    // private void a(long j, PendingIntent pendingIntent) {
    //     long elapsedRealtime = System.currentTimeMillis() + j;
    //     if (Build.VERSION.SDK_INT > 19) {
    //         this.f5368a.setExact(2, elapsedRealtime, pendingIntent);
    //     } else {
    //         this.f5368a.set(2, elapsedRealtime, pendingIntent);
    //     }
    // }

    // private void a(PendingIntent pendingIntent) {
    //     this.f5368a.cancel(pendingIntent);
    // }

    // private PendingIntent a(Context context, b bVar) {
    //     Intent intent = new Intent();
    //     intent.setPackage(context.getPackageName());
    //     intent.setAction(a.f22981a);
    //     intent.putExtra(a.f22982b, bVar.f22983a);
    //     return PendingIntent.getBroadcast(context, bVar.f22983a, intent, dxi.e);
    // }

    private int a() {
        if (this.f5371a.get() > Integer.MAX_VALUE) {
            this.f5371a.set(1);
        }
        return this.f5371a.getAndIncrement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PreprocessorInterceptor.java */
    /* loaded from: classes5.dex */
    public class a /* extends BroadcastReceiver */ {

        /* renamed from: a, reason: collision with root package name */
        public static final String f22981a = "com.xtc.httplib.IMTranspond.TIME_OUT";

        /* renamed from: b, reason: collision with root package name */
        public static final String f22982b = "com.xtc.httplib.IMTranspond.REQUEST_CODE";

        a() {
        }

        public void a(Context context) {
            // IntentFilter intentFilter = new IntentFilter();
            // intentFilter.addAction(f22981a);
            // context.registerReceiver(this, intentFilter);
        }

        // @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Object intent) {
            // if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            //     dkw.e(cgc.f22980b, "intent is null");
            //     return;
            // }
            // if (f22981a.equals(intent.getAction())) {
            //     int intExtra = intent.getIntExtra(f22982b, -1);
            //     dkw.c(cgc.f22980b, "receive time out alarm requestCode = " + intExtra);
            //     if (intExtra != -1) {
            //         b a2 = cgc.this.a(intExtra);
            //         if (a2 == null) {
            //             dkw.d(cgc.f22980b, "receive time out alarm tag is null");
            //         } else {
            //             a2.a();
            //         }
            //     }
            // }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b a(int i) {
        Iterator<b> it = this.f5370a.iterator();
        while (it.hasNext()) {
            b next = it.next();
            if (next.f22983a == i) {
                return next;
            }
        }
        return null;
    }
}