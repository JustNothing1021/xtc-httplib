package com.xtc.sync;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExtensionRegistryFactory.java */
/* loaded from: classes4.dex */
public final class alg {

    /* renamed from: a, reason: collision with root package name */
    static final Class<?> f20671a = m1419a();

    /* renamed from: a, reason: collision with other field name */
    static final String f2780a = "com.google.protobuf.ExtensionRegistry";

    alg() {
    }

    /* renamed from: a, reason: collision with other method in class */
    static Class<?> m1419a() {
        try {
            return Class.forName(f2780a);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static alh a() {
        alh a2 = a("newInstance");
        return a2 != null ? a2 : new alh();
    }

    public static alh b() {
        alh a2 = a("getEmptyRegistry");
        return a2 != null ? a2 : alh.f20672a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(alh alhVar) {
        Class<?> cls = f20671a;
        return cls != null && cls.isAssignableFrom(alhVar.getClass());
    }

    private static final alh a(String str) {
        Class<?> cls = f20671a;
        if (cls == null) {
            return null;
        }
        try {
            return (alh) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}