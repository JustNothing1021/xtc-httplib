package com.xtc.sync;

import android.content.Context;
import android.text.TextUtils;
import com.xtc.httplib.constant.HttpRequestEvent;
import com.xtc.sync.cel;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: HttpResponseInterceptor.java */
/* loaded from: classes5.dex */
public class cfw extends cfr {

    /* renamed from: b, reason: collision with root package name */
    private static final String f22964b = ceo.a("HttpResponseInterceptor");

    /* renamed from: a, reason: collision with root package name */
    private final cem f22965a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cfw(Context context, cem cemVar) {
        this.f22965a = cemVar;
    }

    @Override // com.xtc.sync.cfr, okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long elapsedRealtime = System.currentTimeMillis();
        HttpRequestEvent a2 = cet.a().a(request.header(cfb.f));
        Response proceed = chain.proceed(request);
        if (a2 != null) {
            a2.setCallServerCostTime(String.valueOf(System.currentTimeMillis() - elapsedRealtime));
        }
        long elapsedRealtime2 = System.currentTimeMillis();
        Response a3 = a(proceed, cfu.m3076a(this.f22965a, request));
        if (a2 != null) {
            a2.setInterceptorRespTime(String.valueOf(System.currentTimeMillis() - elapsedRealtime2));
        }
        return a3;
    }

    private Response a(Response response, String str) throws IOException {
        if (response.body() == null) {
            return response;
        }
        String string = response.peekBody(1048576L).string();
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(str)) {
            String header = response.header(cel.a.s);
            String header2 = response.header(cel.a.g);
            if (header != null && header.contains(cel.a.s)) {
                string = cfu.b(str, string, cev.a().a(header2));
            }
        } else {
            dkw.d(f22964b, "result or eebbkKey is null");
        }
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body(ResponseBody.create(MediaType.parse(cel.a.f), string));
        return newBuilder.build();
    }
}