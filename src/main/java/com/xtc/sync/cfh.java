package com.xtc.sync;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.xtc.httplib.bean.NetBaseResult;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* compiled from: XtcGsonResponseBodyConverter.java */
/* loaded from: classes5.dex */
final class cfh<T> implements Converter<ResponseBody, T> {

    /* renamed from: a, reason: collision with root package name */
    private final Gson f22938a;

    /* renamed from: a, reason: collision with other field name */
    private final TypeAdapter<T> f5306a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cfh(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f22938a = gson;
        this.f5306a = typeAdapter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // retrofit2.Converter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public T convert(ResponseBody responseBody) throws IOException {
        JsonReader newJsonReader = this.f22938a.newJsonReader(responseBody.charStream());
        try {
            T read2 = this.f5306a.read(newJsonReader);
            if (newJsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            if (read2 instanceof NetBaseResult) {
                cer.m3029a().a(((NetBaseResult) read2).getCode(), 1);
            }
            return read2;
        } finally {
            responseBody.close();
        }
    }
}