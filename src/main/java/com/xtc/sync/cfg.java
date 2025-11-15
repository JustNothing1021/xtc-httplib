package com.xtc.sync;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/* compiled from: XtcGsonRequestBodyConverter.java */
/* loaded from: classes5.dex */
final class cfg<T> implements Converter<T, RequestBody> {

    /* renamed from: a, reason: collision with other field name */
    private final Gson f5304a;

    /* renamed from: a, reason: collision with other field name */
    private final TypeAdapter<T> f5305a;

    /* renamed from: a, reason: collision with other field name */
    private static final MediaType f5303a = MediaType.get("application/json; charset=UTF-8");

    /* renamed from: a, reason: collision with root package name */
    private static final Charset f22937a = Charset.forName("UTF-8");

    /* JADX INFO: Access modifiers changed from: package-private */
    public cfg(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f5304a = gson;
        this.f5305a = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RequestBody convert(T t) throws IOException {
        okio.Buffer howVar = new okio.Buffer();
        JsonWriter newJsonWriter = this.f5304a.newJsonWriter(new OutputStreamWriter(howVar.outputStream(), f22937a));
        this.f5305a.write(newJsonWriter, t);
        newJsonWriter.close();
        return RequestBody.create(f5303a, howVar.readString(f22937a));
    }
}