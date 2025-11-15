package com.xtc.utils.encode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.xtc.sync.dkw;

import android.text.TextUtils;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JSONUtil {
    private static String TAG = JSONUtil.class.getName();
    private static Gson gson = getGsonBuilder().create();

    public static GsonBuilder getGsonBuilder() {
        GsonBuilder disableHtmlEscaping = new GsonBuilder().disableHtmlEscaping();
        disableHtmlEscaping.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { // from class: com.xtc.utils.encode.JSONUtil.1
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        disableHtmlEscaping.registerTypeAdapter(Date.class, new JsonSerializer<Date>() { // from class: com.xtc.utils.encode.JSONUtil.2
            @Override // com.google.gson.JsonSerializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(Long.valueOf(date.getTime()));
            }
        });
        disableHtmlEscaping.registerTypeAdapter(Boolean.TYPE, new JsonDeserializer<Boolean>() { // from class: com.xtc.utils.encode.JSONUtil.3
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                try {
                    return Boolean.valueOf(jsonElement.getAsInt() != 0);
                } catch (NumberFormatException unused) {
                    return Boolean.valueOf(jsonElement.getAsBoolean());
                }
            }
        });
        disableHtmlEscaping.registerTypeAdapter(Boolean.class, new JsonDeserializer<Boolean>() { // from class: com.xtc.utils.encode.JSONUtil.4
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                try {
                    return Boolean.valueOf(jsonElement.getAsInt() != 0);
                } catch (NumberFormatException unused) {
                    return Boolean.valueOf(jsonElement.getAsBoolean());
                }
            }
        });
        disableHtmlEscaping.registerTypeAdapter(byte[].class, new JsonSerializer<byte[]>() { // from class: com.xtc.utils.encode.JSONUtil.5
            @Override // com.google.gson.JsonSerializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public JsonElement serialize(byte[] bArr, Type type, JsonSerializationContext jsonSerializationContext) {
                // TODO: 这里的原逻辑是Base64.encodeToString(bArr, 2)
                return new JsonPrimitive(Base64.getEncoder().encodeToString(bArr));
            }
        });
        disableHtmlEscaping.registerTypeAdapter(byte[].class, new JsonDeserializer<byte[]>() { // from class: com.xtc.utils.encode.JSONUtil.6
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public byte[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                try {
                    // TODO: 原逻辑为decode(jsonElement.getAsString(), 2)
                    return Base64.getDecoder().decode(jsonElement.getAsString());
                } catch (IllegalStateException unused) {
                    JsonArray asJsonArray = jsonElement.getAsJsonArray();
                    byte[] bArr = new byte[asJsonArray.size()];
                    for (int i = 0; i < asJsonArray.size(); i++) {
                        bArr[i] = asJsonArray.get(i).getAsByte();
                    }
                    return bArr;
                }
            }
        });
        disableHtmlEscaping.registerTypeAdapter(Integer.TYPE, new JsonDeserializer<Integer>() { // from class: com.xtc.utils.encode.JSONUtil.7
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return Integer.valueOf(jsonElement.getAsInt());
            }
        });
        try {
            Object newInstance = Class.forName("kotlinx.gson.KotlinJsonTypeAdapterFactory").newInstance();
            if (newInstance instanceof TypeAdapterFactory) {
                disableHtmlEscaping.registerTypeAdapterFactory((TypeAdapterFactory) newInstance);
            }
        } catch (Exception unused) {
        }
        return disableHtmlEscaping;
    }

    public static String toJSON(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return gson.toJson(obj);
        } catch (Exception unused) {
            dkw.b(TAG, "toJSON failed with obj=" + obj);
            return null;
        }
    }

    public static <T> T fromJSON(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (T) gson.fromJson(str, (Class) cls);
        } catch (Exception unused) {
            dkw.b(TAG, "fromJSON failed with jsonStr=" + str + ", classType=" + cls);
            return null;
        }
    }

    public static <T> T toCollection(String str, Class<?> cls, Class<?>... clsArr) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (T) gson.fromJson(str, TypeToken.getParameterized(cls, clsArr).getType());
        } catch (Exception unused) {
            dkw.b(TAG, "toCollection failed with jsonStr=" + str + ", collectionClass=" + cls);
            return null;
        }
    }

    public static Object get(String str, String str2) {
        try {
            return new JSONObject(str).get(str2);
        } catch (JSONException e) {
            dkw.b(TAG, e.getMessage());
            return null;
        }
    }
}
