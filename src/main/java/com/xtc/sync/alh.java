package com.xtc.sync;

import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ExtensionRegistryLite.java */
/* loaded from: classes4.dex */
public class alh {

    /* renamed from: a, reason: collision with root package name */
    static final alh f20672a = new alh(true);

    /* renamed from: a, reason: collision with other field name */
    static final String f2781a = "com.google.protobuf.Extension";

    /* renamed from: a, reason: collision with other field name */
    private static volatile boolean f2782a = false;

    /* renamed from: b, reason: collision with root package name */
    private static volatile alh f20673b = null;

    /* renamed from: b, reason: collision with other field name */
    private static boolean f2783b = true;

    /* renamed from: a, reason: collision with other field name */
    // private final Map<b, GeneratedMessageLite.f<?, ?>> f2784a;

    // /* compiled from: ExtensionRegistryLite.java */
    // /* loaded from: classes4.dex */
    // static class a {

    //     /* renamed from: a, reason: collision with root package name */
    //     static final Class<?> f20674a = a();

    //     private a() {
    //     }

    //     static Class<?> a() {
    //         try {
    //             return Class.forName(alh.f2781a);
    //         } catch (ClassNotFoundException unused) {
    //             return null;
    //         }
    //     }
    // }

    // /* renamed from: a, reason: collision with other method in class */
    // public static boolean m1420a() {
    //     return f2782a;
    // }

    public static void a(boolean z) {
        f2782a = z;
    }

    // public static alh a() {
    //     return f2783b ? alg.a() : new alh();
    // }

    public static alh b() {
        alh alhVar = f20673b;
        if (alhVar == null) {
            synchronized (alh.class) {
                alhVar = f20673b;
                if (alhVar == null) {
                    alhVar = f2783b ? alg.b() : f20672a;
                    f20673b = alhVar;
                }
            }
        }
        return alhVar;
    }

    // public alh c() {
    //     return new alh(this);
    // }

    // public <ContainingType extends amm> GeneratedMessageLite.f<ContainingType, ?> a(ContainingType containingtype, int i) {
    //     return (GeneratedMessageLite.f) this.f2784a.get(new b(containingtype, i));
    // }

    // public final void a(GeneratedMessageLite.f<?, ?> fVar) {
    //     this.f2784a.put(new b(fVar.b(), fVar.a()), fVar);
    // }

    // public final void a(alf<?, ?> alfVar) {
    //     if (GeneratedMessageLite.f.class.isAssignableFrom(alfVar.getClass())) {
    //         a((GeneratedMessageLite.f<?, ?>) alfVar);
    //     }
    //     if (f2783b && alg.a(this)) {
    //         try {
    //             getClass().getMethod("add", a.f20674a).invoke(this, alfVar);
    //         } catch (Exception e) {
    //             throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", alfVar), e);
    //         }
    //     }
    // }

    // /* JADX INFO: Access modifiers changed from: package-private */
    public alh() {
        // this.f2784a = new HashMap();
    }

    // alh(alh alhVar) {
    //     if (alhVar == f20672a) {
    //         this.f2784a = Collections.emptyMap();
    //     } else {
    //         this.f2784a = Collections.unmodifiableMap(alhVar.f2784a);
    //     }
    // }

    alh(boolean z) {
        // this.f2784a = Collections.emptyMap();
    }

    // /* JADX INFO: Access modifiers changed from: package-private */
    // /* compiled from: ExtensionRegistryLite.java */
    // /* loaded from: classes4.dex */
    // public static final class b {

    //     /* renamed from: a, reason: collision with root package name */
    //     private final int f20675a;

    //     /* renamed from: a, reason: collision with other field name */
    //     private final Object f2785a;

    //     b(Object obj, int i) {
    //         this.f2785a = obj;
    //         this.f20675a = i;
    //     }

    //     public int hashCode() {
    //         return (System.identityHashCode(this.f2785a) * 65535) + this.f20675a;
    //     }

    //     public boolean equals(Object obj) {
    //         if (!(obj instanceof b)) {
    //             return false;
    //         }
    //         b bVar = (b) obj;
    //         return this.f2785a == bVar.f2785a && this.f20675a == bVar.f20675a;
    //     }
    // }
}