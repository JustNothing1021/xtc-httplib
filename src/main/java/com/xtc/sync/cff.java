package com.xtc.sync;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* compiled from: XtcGsonConverterFactory.java */
/* loaded from: classes5.dex */
public final class cff extends Converter.Factory {

    /* renamed from: a, reason: collision with root package name */
    private final Gson f22936a;

    public static cff a() {
        return a(new Gson());
    }

    public static cff a(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new cff(gson);
    }

    private cff(Gson gson) {
        this.f22936a = gson;
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new cfh(this.f22936a, this.f22936a.getAdapter(TypeToken.get(type)));
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new cfg(this.f22936a, this.f22936a.getAdapter(TypeToken.get(type)));
    }
}