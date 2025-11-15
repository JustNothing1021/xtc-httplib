package com.xtc.httplib.okhttp;

// import com.badlogic.gdx.Net;
import com.xtc.httplib.annotation.ImBridge;
import com.xtc.sync.cel;
import com.xtc.sync.cfb;
// import com.xtc.sync.dgg;
import com.xtc.sync.dkw;
// import com.xtc.sync.how;
import com.xtc.utils.encode.JSONUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Invocation;

/* loaded from: classes2.dex */
public class ImBridgeInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final long f20256a = 10;

    /* renamed from: a, reason: collision with other field name */
    private static final String f2352a = "ImBridgeInterceptor";

    /* renamed from: b, reason: collision with root package name */
    private static final long f20257b = 10240;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        if (!m1079a(chain)) {
            return chain.proceed(chain.request());
        }
        dkw.c(f2352a, "bridge http request: " + chain.request().url());
        return a(chain);
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m1079a(Interceptor.Chain chain) {
        Invocation invocation = (Invocation) chain.request().tag(Invocation.class);
        return (invocation == null || invocation.method().getAnnotation(ImBridge.class) == null) ? false : true;
    }

    private Response a(Interceptor.Chain chain) throws IOException {
        final Request request = chain.request();
        String httpUrl = request.url().toString();
        Integer code = MethodCode.getCode(request.method());
        if (code == null) {
            throw new IOException("Nonsupport bridge http method" + request.method());
        }
        byte[] a2 = a(request.headers());
        byte[] a3 = a(request.body());
        if (a3.length > 10240) {
            throw new IOException("body is larger than 10Kb");
        }
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        // if (!dgg.a(httpUrl, code.intValue(), a2, a3, new ITranspondCallback.a() { // from class: com.xtc.httplib.okhttp.ImBridgeInterceptor.1
        //     @Override // com.xtc.im.transpond.ITranspondCallback
        //     public void onSuccess(byte[] bArr, int i) {
        //         atomicReference.set(ImBridgeInterceptor.this.a(request, bArr));
        //         countDownLatch.countDown();
        //     }

        //     @Override // com.xtc.im.transpond.ITranspondCallback
        //     public void onError(String str) {
        //         atomicReference2.set(new IOException(str));
        //         countDownLatch.countDown();
        //     }
        // })) {
        //     throw new IOException("Bridge http failure");
        // }
        try {
            if (!countDownLatch.await(10L, TimeUnit.SECONDS)) {
                dkw.c(f2352a, "await timeout");
            }
        } catch (InterruptedException unused) {
        }
        if (atomicReference.get() != null) {
            return (Response) atomicReference.get();
        }
        if (atomicReference2.get() != null) {
            throw ((IOException) atomicReference2.get());
        }
        throw new IOException("timeout");
    }

    private byte[] a(Headers headers) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < headers.size(); i++) {
            hashMap.put(headers.name(i), headers.value(i));
        }
        String json = JSONUtil.toJSON(hashMap);
        return json == null ? new byte[0] : json.getBytes(StandardCharsets.US_ASCII);
    }

    private byte[] a(RequestBody requestBody) throws IOException {
        // if (requestBody == null) {
        //     return new byte[0];
        // }
        // how howVar = new how();
        // try {
        //     requestBody.writeTo(howVar);
        //     byte[] mo9359a = howVar.mo9359a();
        //     howVar.close();
        //     return mo9359a;
        // } catch (Throwable th) {
        //     try {
        //         throw th;
        //     } catch (Throwable th2) {
        //         try {
        //             howVar.close();
        //         } catch (Throwable th3) {
        //             th.addSuppressed(th3);
        //         }
        //         throw th2;
        //     }
        // }
        return requestBody.toString().getBytes(StandardCharsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response a(Request request, byte[] bArr) {
        Response.Builder receivedResponseAtMillis = new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(Integer.parseInt(cfb.g)).message("").body(ResponseBody.create(MediaType.get(cel.a.f), bArr)).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis());
        if (a(request)) {
            receivedResponseAtMillis.header(cel.a.s, cel.a.s);
        }
        return receivedResponseAtMillis.build();
    }

    private boolean a(Request request) {
        return cel.a.s.equals(request.header(cel.a.s));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum MethodCode {
        GET("GET", 1),
        POST("POST", 2),
        DELETE("DELETE", 3),
        PUT("PUT", 4);

        private final int code;
        private final String method;

        MethodCode(String str, int i) {
            this.method = str;
            this.code = i;
        }

        public static Integer getCode(String str) {
            for (MethodCode methodCode : values()) {
                if (methodCode.method.equals(str)) {
                    return Integer.valueOf(methodCode.code);
                }
            }
            return null;
        }
    }
}