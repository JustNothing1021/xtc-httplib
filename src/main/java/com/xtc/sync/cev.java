package com.xtc.sync;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: AesKeyCache.java */
/* loaded from: classes5.dex */
public class cev {

    /* renamed from: a, reason: collision with root package name */
    private static final int f22912a = 50;

    /* renamed from: a, reason: collision with other field name */
    private LinkedHashMap<String, a> f5279a = new LinkedHashMap<>();

    /* renamed from: a, reason: collision with other field name */
    private static final String f5278a = ceo.a("AesKeyCache");

    /* renamed from: a, reason: collision with other field name */
    private static final long f5276a = TimeUnit.MINUTES.toMillis(1);

    /* renamed from: a, reason: collision with other field name */
    private static volatile cev f5277a = null;

    public static cev a() {
        if (f5277a == null) {
            synchronized (cev.class) {
                if (f5277a == null) {
                    f5277a = new cev();
                }
            }
        }
        return f5277a;
    }

    private cev() {
    }

    public synchronized void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            dkw.b(f5278a, "cache: sign is empty");
        } else {
            if (this.f5279a.containsKey(str)) {
                dkw.b(f5278a, "cache: sign already cache");
                return;
            }
            m3037a();
            this.f5279a.put(str, new a(System.currentTimeMillis(), str2));
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m3037a() {
        if (this.f5279a.size() < 50) {
            return;
        }
        Iterator<Map.Entry<String, a>> it = this.f5279a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, a> next = it.next();
            if (next == null) {
                it.remove();
            } else {
                a value = next.getValue();
                if (value == null) {
                    it.remove();
                } else if (System.currentTimeMillis() - value.a() < f5276a) {
                    return;
                } else {
                    it.remove();
                }
            }
        }
    }

    public synchronized String a(String str) {
        String r0 = "";
        if (TextUtils.isEmpty(str)) {
            return cfr.f22951a;
        }
        if (this.f5279a.containsKey(str)) {
            a aVar = this.f5279a.get(str);
            r0 = aVar != null ? aVar.m3038a() : null;
            this.f5279a.remove(str);
        } else {
            dkw.b(f5278a, "getKey not found : sign = " + str);
        }
        if (TextUtils.isEmpty(r0)) {
            r0 = cfr.f22951a;
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AesKeyCache.java */
    /* loaded from: classes5.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private long f22913a;

        /* renamed from: a, reason: collision with other field name */
        private String f5280a;

        public a(long j, String str) {
            this.f22913a = j;
            this.f5280a = str;
        }

        public long a() {
            return this.f22913a;
        }

        public void a(long j) {
            this.f22913a = j;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m3038a() {
            return this.f5280a;
        }

        public void a(String str) {
            this.f5280a = str;
        }

        public String toString() {
            return "CacheData{cacheTime=" + this.f22913a + ", aesKey='" + this.f5280a + "'}";
        }
    }
}