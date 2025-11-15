package com.xtc.sync;

import android.content.Context;
import android.text.TextUtils;
import com.xtc.httplib.bean.AppInfo;
import com.xtc.httplib.bean.DeviceInfo;
import com.xtc.httplib.bean.EncryptData;
import com.xtc.httplib.bean.NetBaseRequestParam;
import com.xtc.sync.cel;
import com.xtc.utils.encode.JSONUtil;
import com.xtc.utils.security.XtcSecurity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Invocation;

/* compiled from: HttpHelper.java */
/* loaded from: classes5.dex */
public class cfu {

    /* renamed from: a, reason: collision with root package name */
    private static final String f22961a = ceo.a("DefaultOkHttpClient");

    public static String a(String str, String str2, boolean z, String str3) {
        if (!a(str) && !a(str2) && z) {
            str = a(str, str2, str3);
        }
        dkw.c(f22961a, "encrypt body:" + str);
        return str;
    }

    public static Map<String, String> a(Context context, Request request, String str, byte[] bArr, String str2, boolean z, int i, String str3) {
        cem m3021a = cen.a(context).m3021a();
        HashMap hashMap = new HashMap();
        a(request, hashMap, "Content-Type", cel.a.f);
        String a2 = a(m3021a);
        String a3 = (a(str2) || !z) ? a2 : a(a2, str3);
        String a4 = a(str3, str, a2, bArr);
        hashMap.put(cel.a.g, a4);
        cev.a().a(a4, str3);
        hashMap.put(cel.a.f22882b, a3);
        a(request, hashMap, "model", elw.a());
        a(request, hashMap, cel.a.n, String.valueOf(102));
        a(request, hashMap, cel.a.q, String.valueOf(m3021a.mo3069a()));
        a(request, hashMap, "packageName", m3021a.mo3073b());
        AppInfo m3015a = m3021a.m3015a();
        if (m3015a != null) {
            String version = m3015a.getVersion();
            if (!a(version)) {
                hashMap.put(cel.a.c, version);
            }
            String grey = m3015a.getGrey();
            if (!a(grey)) {
                hashMap.put(cel.a.l, grey);
            }
            if (i == 3 || i == 2) {
                hashMap.put(cel.a.i, m3015a.getKeyId());
            }
            Map<String, String> httpHeadParamMap = m3015a.getHttpHeadParamMap();
            if (httpHeadParamMap != null) {
                hashMap.putAll(httpHeadParamMap);
            }
        }
        if (!a(str2) && z) {
            hashMap.put(cel.a.h, str2);
            hashMap.put(cel.a.s, cel.a.s);
            hashMap.put("Content-Encoding", "gzip");
        }
        if (!TextUtils.isEmpty(cen.a(context).m3022a())) {
            hashMap.put("Accept-Language", cen.a(context).m3022a());
        }
        if (!TextUtils.isEmpty(cen.a(context).b())) {
            hashMap.put(cel.a.p, cen.a(context).b());
        }
        dkw.c(f22961a, "generateHeaderMap:" + hashMap);
        return hashMap;
    }

    private static void a(Request request, Map<String, String> map, String str, String str2) {
        String str3 = request.headers().get(str);
        if (str3 == null) {
            map.put(str, str2);
            return;
        }
        dkw.c(f22961a, "addHeader tempValue:" + str3);
    }

    public static String a(Context context, Map<String, String> map, String str, Request request) {
        String m3076a = m3076a(cen.a(context).m3021a(), request);
        if (a(str) || a(m3076a)) {
            dkw.e(f22961a, "decodeHttpResult:eebbkKey is null or http response body is nll.");
        } else if (map != null) {
            Headers of = Headers.of(map);
            String str2 = of.get(cel.a.s);
            String str3 = of.get(cel.a.g);
            if (str2 != null && str2.contains(cel.a.s)) {
                str = b(m3076a, str, cev.a().a(str3));
            }
        }
        dkw.c(f22961a, "decodeHttpResult:" + str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str, String str2, String str3) {
        if (!a(str2)) {
            return ekg.a(ekj.a(str, "utf-8"), str3);
        }
        dkw.d(f22961a, "eebbkKey is null,eebbkKey:" + str2);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(RequestBody requestBody) {
        return requestBody != null ? requestBody.toString() : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(cem cemVar) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        NetBaseRequestParam netBaseRequestParam = new NetBaseRequestParam();
        netBaseRequestParam.setImFlag("1");
        netBaseRequestParam.setAppId("2");
        netBaseRequestParam.setProgram("watch");
        DeviceInfo m3016a = cemVar.m3016a();
        netBaseRequestParam.setTimestamp(format);
        netBaseRequestParam.setDeviceId(m3016a.getBindNumber());
        netBaseRequestParam.setToken(m3016a.getChipId());
        netBaseRequestParam.setMac(m3016a.getMacAddr());
        netBaseRequestParam.setAccountId(cemVar.m3017a());
        netBaseRequestParam.setRegistId(Long.valueOf(cemVar.m3014a()));
        netBaseRequestParam.setRequestId(ekp.a());
        return JSONUtil.toJSON(netBaseRequestParam);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a, reason: collision with other method in class */
    public static String m3076a(cem cemVar, Request request) {
        return a(cemVar, request).getEebbkKey();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static EncryptData a(cem cemVar, Request request) {
        AppInfo m3015a = cemVar.m3015a();
        String str = null;
        if (m3015a == null) {
            dkw.d(f22961a, "getEebbkKey AppInfo is null");
            return new EncryptData(null, null, 0);
        }
        int rsaEncryptType = m3015a.getRsaEncryptType();
        if (rsaEncryptType > 1 && a(request)) {
            rsaEncryptType = 1;
        }
        String aesKey = m3015a.getAesKey();
        if (rsaEncryptType == 3) {
            str = m3015a.getEncryptEebbkKey();
        } else if (rsaEncryptType != 0) {
            String publicKey = m3015a.getPublicKey(rsaEncryptType);
            if (TextUtils.isEmpty(publicKey)) {
                publicKey = XtcSecurity.getDefaultRsaKey();
            }
            if (!a(publicKey) && (str = ekn.b(aesKey, publicKey)) == null) {
                dkw.e(f22961a, "pubEncrypt error: publicKey = [" + publicKey + "]");
                cer.m3029a().a(cer.f5251a, 2);
            }
        }
        return new EncryptData(str, aesKey, rsaEncryptType);
    }

    public static boolean a(Request request) {
        // Invocation invocation;
        // if (request == null || (invocation = (Invocation) request.tag(Invocation.class)) == null) {
        //     return false;
        // }
        // boolean z = invocation.method().getAnnotation(KeepHead.class) != null;
        // if (z) {
        //     dkw.b(f22961a, "NotUseSelfKey url = [" + request.url() + "]");
        // }
        // return z;
        return false;
    }

    private static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str, String str2) {
        return ekg.a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str, String str2, String str3, String str4) {
        return a(str, str2, str3, (str4 == null || "".equals(str4)) ? null : str4.getBytes(Charset.forName("utf-8")));
    }

    protected static String a(String str, String str2, String str3, byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Charset forName = Charset.forName("utf-8");
        byte[] bytes = str2.getBytes(forName);
        byteArrayOutputStream.write(bytes, 0, bytes.length);
        byte[] bytes2 = str3.getBytes(forName);
        byteArrayOutputStream.write(bytes2, 0, bytes2.length);
        if (bArr != null && bArr.length > 0) {
            byteArrayOutputStream.write(bArr, 0, bArr.length);
        }
        byte[] bytes3 = str.getBytes(forName);
        byteArrayOutputStream.write(bytes3, 0, bytes3.length);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            dkw.a(e);
        }
        return ekk.a(byteArrayOutputStream.toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String b(String str, String str2, String str3) {
        return a(str) ? str2 : b(str2, str3);
    }

    private static String b(String str, String str2) {
        if (!a(str)) {
            str = str.replace("\"", "");
        }
        byte[] bArr = null;
        try {
            bArr = ekg.m4854a(str, str2);
        } catch (Throwable th) {
            dkw.b(f22961a, "decryptAESToByte error: ", th);
        }
        if (!TextUtils.isEmpty(str) && bArr == null) {
            dkw.e(f22961a, "decryptAESToByte result is null");
        }
        byte[] a2 = ekj.a(bArr);
        return a2 != null ? new String(a2) : str;
    }
}