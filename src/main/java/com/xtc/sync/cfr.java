package com.xtc.sync;

import java.io.IOException;

import android.content.Context;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: BaseInterceptor.java */
/* loaded from: classes5.dex */
public abstract class cfr implements Interceptor {

    /* renamed from: a, reason: collision with other field name */
    protected Context f5323a;


    /* renamed from: b, reason: collision with root package name */
    private static final String f22952b = ceo.a("BaseInterceptor");

    /* renamed from: a, reason: collision with root package name */
    public static final String f22951a = ekp.a().substring(0, 16);

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return null;
    }

    public cfr() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(String str, boolean z) {
        if (z) {
            return str;
        }
        if (str.contains("?")) {
            return str + "&uuid=" + ekp.a();
        }
        return str + "?uuid=" + ekp.a();
    }

    protected String a(String str) {
        int indexOf = str.indexOf("?uuid=");
        if (indexOf == -1) {
            indexOf = str.indexOf("&uuid=");
        }
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        dkw.c(f22952b, "http request sub url:" + str);
        return str;
    }
}