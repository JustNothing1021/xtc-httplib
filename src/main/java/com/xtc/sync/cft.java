package com.xtc.sync;

import android.content.Context;

import com.google.protobuf.ExtensionRegistryLite;
import com.justnothing.xtchttplib.ContextManager;
import com.xtc.httplib.constant.HttpConstants;
// import android.content.pm.PackageInfo;
// import android.os.Process;
// import android.os.SystemClock;
// import android.support.v4.os.EnvironmentCompat;
import com.xtc.httplib.constant.TcpTimeoutConfig;
// import com.xtc.httplib.okhttp.ImBridgeInterceptor;
import com.xtc.utils.encode.JSONUtil;
// import com.xtc.utils.storage.SharedManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;

/* compiled from: DefaultOkHttpClient.java */
/* loaded from: classes5.dex */
public class cft extends cem {

    /* renamed from: a, reason: collision with other field name */
    public static final String f5326a = "tcp_keep_alive_duration";

    /* renamed from: b, reason: collision with other field name */
    public static final String f5327b = "tcp_max_idle_connections";
    public static final int d = 14;
    public static final int e = 2;

    /* renamed from: a, reason: collision with other field name */
    private TcpTimeoutConfig f5329a;

    /* renamed from: a, reason: collision with other field name */
    // private bxi f5330a;

    /* renamed from: a, reason: collision with other field name */
    private final Map<String, Retrofit> f5331a;

    /* renamed from: a, reason: collision with other field name */
    private Executor f5332a;

    /* renamed from: a, reason: collision with other field name */
    private OkHttpClient f5333a;

    /* renamed from: b, reason: collision with other field name */
    private OkHttpClient f5334b;

    /* renamed from: c, reason: collision with other field name */
    String f5335c;

    /* renamed from: c, reason: collision with other field name */
    private OkHttpClient f5336c;

    /* renamed from: d, reason: collision with other field name */
    String f5337d;

    /* renamed from: d, reason: collision with other field name */
    private OkHttpClient f5338d;
    int f;

    /* renamed from: e, reason: collision with other field name */
    private static final String f5328e = ceo.a("DefaultOkHttpClient");

    /* renamed from: a, reason: collision with other field name */
    public static final cfy f5325a = new cfy();

    /* renamed from: a, reason: collision with root package name */
    public static int f22955a = bla.c;

    /* renamed from: b, reason: collision with root package name */
    public static int f22956b = bla.c;
    public static int c = bla.c;

    /* renamed from: a, reason: collision with other method in class */
    // public bxi m3070a() {
        // return this.f5330a;
    // }

    @Override // com.xtc.sync.cem
    /* renamed from: b, reason: collision with other method in class */
    public String mo3073b() {
        return this.f5335c;
    }

    @Override // com.xtc.sync.cem
    /* renamed from: a, reason: collision with other method in class */
    public int mo3069a() {
        return this.f;
    }

    @Override // com.xtc.sync.cem
    /* renamed from: c, reason: collision with other method in class */
    public String mo3075c() {
        return this.f5337d;
    }

    public cft(Context context) {
        super(context);
        this.f5335c = "unknown";
        this.f = -1;
        this.f5337d = "unknown";
        this.f5329a = null;
        this.f5331a = new HashMap();
        // m3067b();
        ConnectionPool a2 = a(context);
        a(a2);
        b(a2);
        e();
    }

    private ConnectionPool a(Context context) {
        if (this.f5329a == null) {
            this.f5329a = new TcpTimeoutConfig(context);
        }
        int i = this.f5329a.getKeepAliveDuration();
        int i2 = this.f5329a.getMaxIdleConnections();
        if (this.f5329a == null) {
            m3065a();
        }
        return new ConnectionPool(i2, i, TimeUnit.SECONDS);
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m3065a() {
        try {
            long elapsedRealtime = System.currentTimeMillis();
            long elapsedRealtime2 = System.currentTimeMillis();
            dkw.b(cft.f5328e, "query connection pool params cost " + (elapsedRealtime2 - elapsedRealtime) + "ms");
            if (cft.this.f5329a != null) {
                if (cft.this.f5329a.getKeepAliveDuration() > 0) {
                    dkw.b(cft.f5328e, "keepAliveDuration == " + cft.this.f5329a.getKeepAliveDuration());
                }
                if (cft.this.f5329a.getMaxIdleConnections() >= 0) {
                    dkw.b(cft.f5328e, "maxIdleConnections == " + cft.this.f5329a.getMaxIdleConnections());
                }
            }
        } catch (Exception unused) {
            dkw.e(cft.f5328e, "query connection pool params failed. use default config");
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    // private void m3067b() {
    //     this.f5330a = new bxi(this.f5235a);
    // }

    private void a(ConnectionPool connectionPool) {
        OkHttpClient.Builder a2 = a();
        a2.connectionPool(connectionPool);
        this.f5333a = a2.build();
    }

    private void c() {
        this.f5336c = a().build();
    }

    private OkHttpClient.Builder a() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        m3066a(this.f5235a);
        // builder.dns(this.f5330a);
        // builder.eventListenerFactory(this.f5330a);
        builder.connectTimeout(f22955a, TimeUnit.MILLISECONDS);
        builder.writeTimeout(f22956b, TimeUnit.MILLISECONDS);
        builder.readTimeout(c, TimeUnit.MILLISECONDS);
        builder.addInterceptor(f5325a);
        f5325a.a(this);
        // builder.addInterceptor(new cge());
        // builder.addInterceptor(new cgf());
        // builder.addInterceptor(new cfz(this.f5235a));
        builder.addInterceptor(new cgc(this.f5235a));
        builder.addInterceptor(new cfv(this.f5235a, this));
        // builder.addInterceptor(new cfs(this.f5235a));
        // if ("com.xtc.i3launcher".equals(this.f5235a.getPackageName())) {
            // builder.addInterceptor(new cfx(this.f5235a));
        // }
        builder.addInterceptor(new cfw(this.f5235a, this));
        // builder.addInterceptor(new ImBridgeInterceptor());
        // builder.addInterceptor(new cgd(this.f5235a));
        return builder;
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m3066a(Context context) {
        try {
            // PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // this.f = packageInfo.versionCode;
            this.f = ContextManager.getInstance().getPackageVersionCode();
            // this.f5335c = packageInfo.packageName;
            this.f5335c = ContextManager.getInstance().getPackageName();
            // this.f5337d = packageInfo.versionName;
            this.f5337d = ContextManager.getInstance().getPackageVersionName();
        } catch (Exception e2) {
            dkw.e(f5328e, "get app version error = " + e2);
        }
    }

    private void b(ConnectionPool connectionPool) {
        OkHttpClient.Builder b2 = b();
        b2.connectionPool(connectionPool);
        this.f5334b = b2.build();
    }

    /* renamed from: d, reason: collision with other method in class */
    private void m3068d() {
        this.f5338d = b().build();
    }

    private OkHttpClient.Builder b() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // builder.dns(this.f5330a);
        // builder.eventListenerFactory(this.f5330a);
        builder.connectTimeout(f22955a, TimeUnit.MILLISECONDS);
        builder.writeTimeout(f22956b, TimeUnit.MILLISECONDS);
        builder.readTimeout(c, TimeUnit.MILLISECONDS);
        builder.addInterceptor(f5325a);
        // builder.addInterceptor(new cfz(this.f5235a));
        // builder.addInterceptor(new cgd(this.f5235a));
        return builder;
    }

    private void e() {
        this.f5332a = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.xtc.i3launcher.cft.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(final Runnable runnable) {
                return new Thread(new Runnable() { // from class: com.xtc.i3launcher.cft.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        // Process.setThreadPriority(10);
                        runnable.run();
                    }
                }, "http-thread");
            }
        });
    }

    @Override // com.xtc.sync.cem
    public Call a(Request request) {
        return this.f5333a.newCall(request);
    }

    private Retrofit a(String str, OkHttpClient okHttpClient, boolean z) {
        Retrofit retrofit;
        synchronized (this.f5331a) {
            retrofit = this.f5331a.get(str + z);
            if (retrofit == null) {
                retrofit = new Retrofit.Builder().addCallAdapterFactory(a(z)).addConverterFactory(
                        ProtoConverterFactory.createWithRegistry(ExtensionRegistryLite.getEmptyRegistry()))
                        .addConverterFactory(cff.a(JSONUtil.getGsonBuilder().create())).baseUrl(str)
                        .client(okHttpClient).build();
                this.f5331a.put(str + z, retrofit);
            }
        }
        return retrofit;
    }

    private Retrofit b(String str, OkHttpClient okHttpClient, boolean z) {
        return new Retrofit.Builder().addCallAdapterFactory(a(z)).addConverterFactory(GsonConverterFactory.create(JSONUtil.getGsonBuilder().create())).baseUrl(str).client(okHttpClient).build();
    }

    private CallAdapter.Factory a(boolean z) {
        // if (z) {
        //     return cgo.a();
        // }
        // return cgo.a(itd.a(this.f5332a));
        // return CallAdapter.Factory;
        return null;
    }

    @Override // com.xtc.sync.cem
    public <T> T a(String str, Class<T> cls) {
        dkw.c(f5328e, "request baseUrl = " + str);
        return (T) a(str, this.f5333a, false).create(cls);
    }

    @Override // com.xtc.sync.cem
    public <T> T b(String str, Class<T> cls) {
        dkw.c(f5328e, "requestSync baseUrl = " + str);
        return (T) a(str, this.f5333a, true).create(cls);
    }

    @Override // com.xtc.sync.cem
    public <T> T c(String str, Class<T> cls) {
        dkw.c(f5328e, "requestThird baseUrl = " + str);
        return (T) a(str, this.f5334b, false).create(cls);
    }

    @Override // com.xtc.sync.cem
    public <T> T d(String str, Class<T> cls) {
        dkw.c(f5328e, "requestThirdSync baseUrl = " + str);
        return (T) a(str, this.f5334b, true).create(cls);
    }

    @Override // com.xtc.sync.cem
    public void a(final Map<String, String> map) {
        OkHttpClient.Builder newBuilder = this.f5333a.newBuilder();
        newBuilder.addInterceptor(new Interceptor() { // from class: com.xtc.i3launcher.cft.3
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder newBuilder2 = chain.request().newBuilder();
                for (Map.Entry entry : map.entrySet()) {
                    newBuilder2 = newBuilder2.addHeader((String) entry.getKey(), (String) entry.getValue());
                }
                return chain.proceed(newBuilder2.build());
            }
        });
        this.f5333a = newBuilder.build();
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized OkHttpClient m3072a() {
        if (this.f5336c == null) {
            c();
        }
        return this.f5336c;
    }

    /* renamed from: b, reason: collision with other method in class */
    public synchronized OkHttpClient m3074b() {
        if (this.f5338d == null) {
            m3068d();
        }
        return this.f5338d;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Executor m3071a() {
        return this.f5332a;
    }
}