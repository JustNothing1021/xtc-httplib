package com.xtc.sync;

import android.content.Context;
// import android.os.SystemClock;
import android.text.TextUtils;

import com.justnothing.xtchttplib.ContextManager;
import com.xtc.httplib.bean.AppInfo;
import com.xtc.httplib.bean.EncryptData;
import com.xtc.httplib.constant.HttpRequestEvent;
import java.io.IOException;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: HttpRequestInterceptor.java */
/* loaded from: classes5.dex */
public class cfv extends cfr {

    /* renamed from: b, reason: collision with root package name */
    private static final String f22962b = ceo.a("HttpRequestInterceptor");
    private static final String c = "application";
    private static final String d = "json";

    /* renamed from: a, reason: collision with root package name */
    private cem f22963a;

    public ContextManager contextManager;
    public elw watchModelUtil;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cfv(Context context, cem cemVar, ContextManager contextManager) {
        // super(context);
        this.contextManager = contextManager;
        this.f22963a = cemVar;
        this.watchModelUtil = new elw(contextManager);
    }

    @Override // com.xtc.sync.cfr, okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return a(chain, a(chain), System.currentTimeMillis());
    }

    private Response a(Interceptor.Chain chain, Request.Builder builder, long j) throws IOException {
        builder.url(a(builder.build().url().toString(), false));
        HttpRequestEvent a2 = cet.a().a(chain.request().header(cfb.f));
        if (a2 != null) {
            a2.setInterceptorReqTime(String.valueOf(System.currentTimeMillis() - j));
        }
        return chain.proceed(builder.build());
    }

    private Request.Builder a(Interceptor.Chain chain) {
        Request request = chain.request();
        String httpUrl = request.url().toString();
        String method = request.method();
        String a2 = cfu.a(request.body());
        EncryptData a3 = cfu.a(this.f22963a, request);
        String eebbkKey = a3.getEebbkKey();
        int rsaEncryptType = a3.getRsaEncryptType();
        String aesKey = a3.getAesKey();
        boolean a4 = rsaEncryptType == 0 ? false : a(request);
        dkw.c(f22962b, "encryptRequest url:" + httpUrl + ", method:" + method + ", isEncrypt:" + a4);
        String a5 = cfu.a(this.f22963a);
        String a6 = (TextUtils.isEmpty(eebbkKey) || !a4) ? a5 : cfu.a(a5, aesKey);
        String a7 = cfu.a(aesKey, httpUrl, a5, a2);
        cev.a().a(a7, aesKey);
        Request.Builder newBuilder = request.newBuilder();
        a(request, newBuilder, a7, a6, eebbkKey, a4, rsaEncryptType);
        dkw.a(f22962b, "encryptRequest header = \n" + a(newBuilder.build().headers()));
        a(request, newBuilder, a2, eebbkKey, a4, aesKey);
        return newBuilder;
    }

    private String a(Headers headers) {
        if (headers == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            if (!cel.a.f22882b.equals(headers.name(i)) && !"Content-Type".equals(headers.name(i)) && !cel.a.n.equals(headers.name(i))) {
                sb.append(headers.name(i));
                sb.append(": ");
                sb.append(headers.value(i));
                sb.append(dtr.f25185b);
            }
        }
        return sb.toString();
    }

    private boolean a(Request request) {
        RequestBody body = request.body();
        if (body == null) {
            return true;
        }
        MediaType contentType = body.contentType();
        if (contentType == null) {
            try {
                if (body.contentLength() <= 0) {
                    return true;
                }
            } catch (IOException e) {
                dkw.e(f22962b, "isEncrypt: get contentLength error: ", e);
            }
        }
        return contentType != null && c.equals(contentType.type()) && d.equals(contentType.subtype());
    }

    private void a(Request request, Request.Builder builder, String str, String str2, boolean z, String str3) {
        RequestBody body = request.body();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && z) {
            String a2 = cfu.a(str, str2, str3);
            if (!TextUtils.isEmpty(a2)) {
                body = RequestBody.create(body.contentType(), a2);
            }
        }
        builder.method(request.method(), body);
    }

    private void a(Request request, Request.Builder builder, String str, String str2, String str3, boolean z, int i) {
        try {
            if (watchModelUtil == null) {
                dkw.e(f22962b, "watchModelUtil is null in cfv.a()");
                new Exception("watchModelUtil is null").printStackTrace();
                watchModelUtil = new elw(contextManager);
            }
            if (contextManager == null) {
                dkw.e(f22962b, "contextManager is null in cfv.a()");
                new Exception("contextManager is null").printStackTrace();
            }
            a(request, builder, "Content-Type", cel.a.f);
            a(request, builder, "model", watchModelUtil.a());
            a(request, builder, cel.a.n, String.valueOf(102));
            a(request, builder, cel.a.q, String.valueOf(this.f22963a.mo3069a()));
            a(request, builder, "packageName", this.f22963a.mo3073b());
            builder.header(cel.a.g, str);
            builder.header(cel.a.f22882b, str2);
            String m2760a = contextManager != null ? contextManager.getDataCenterCode() : null;
            if (!TextUtils.isEmpty(m2760a)) {
                builder.header(cel.a.t, m2760a);
            }
            AppInfo m3015a = this.f22963a.m3015a();
            if (m3015a != null) {
                String version = m3015a.getVersion();
                if (!TextUtils.isEmpty(version)) {
                    builder.header(cel.a.c, version);
                }
                String grey = m3015a.getGrey();
                if (!TextUtils.isEmpty(grey)) {
                    builder.header(cel.a.l, grey);
                }
                if (i == 3 || i == 2) {
                    builder.header(cel.a.i, m3015a.getKeyId());
                }
                Map<String, String> httpHeadParamMap = m3015a.getHttpHeadParamMap();
                if (httpHeadParamMap != null) {
                    for (Map.Entry<String, String> entry : httpHeadParamMap.entrySet()) {
                        String key = entry.getKey();
                        if (!TextUtils.isEmpty(key)) {
                            builder.header(key, entry.getValue());
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(str3) && z) {
                builder.addHeader(cel.a.h, str3);
                builder.addHeader(cel.a.s, cel.a.s);
                builder.addHeader("Content-Encoding", "gzip");
            }
            if (!TextUtils.isEmpty(cen.a(contextManager).m3022a())) {
                builder.addHeader("Accept-Language", cen.a(contextManager).m3022a());
            }
            if (TextUtils.isEmpty(cen.a(contextManager).b())) {
                return;
            }
            builder.addHeader(cel.a.p, cen.a(contextManager).b());
        } catch (Exception e) {
            dkw.b(f22962b, e);
        }
    }

    private void a(Request request, Request.Builder builder, String str, String str2) {
        if (request.headers().get(str) == null) {
            builder.header(str, str2);
        }
    }
}