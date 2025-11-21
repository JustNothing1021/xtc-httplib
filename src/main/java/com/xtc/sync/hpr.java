package com.xtc.sync;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: SegmentPool.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0007J\b\u0010\u000b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokio/SegmentPool;", "", "()V", "MAX_SIZE", "", "byteCount", "next", "Lokio/Segment;", "recycle", "", "segment", "take", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class hpr {

    /* renamed from: a, reason: collision with root package name */
    public static final long f28681a = 65536;

    /* renamed from: a, reason: collision with other field name */
    public static hpq f12039a;

    /* renamed from: a, reason: collision with other field name */
    public static final hpr f12040a = new hpr();

    /* renamed from: b, reason: collision with root package name */
    public static long f28682b;

    private hpr() {
    }

    @JvmStatic
    public static final hpq a() {
        synchronized (f12040a) {
            hpq hpqVar = f12039a;
            if (hpqVar != null) {
                f12039a = hpqVar.f12034a;
                hpqVar.f12034a = (hpq) null;
                f28682b -= 8192;
                return hpqVar;
            }
            return new hpq();
        }
    }

    @JvmStatic
    public static final void a(hpq hpqVar) {
        fpf.f(hpqVar, "segment");
        if (!(hpqVar.f12034a == null && hpqVar.f12037b == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (hpqVar.f12035a) {
            return;
        }
        synchronized (f12040a) {
            long j = 8192;
            if (f28682b + j > f28681a) {
                return;
            }
            f28682b += j;
            hpqVar.f12034a = f12039a;
            hpqVar.f28680b = 0;
            hpqVar.f12033a = hpqVar.f28680b;
            f12039a = hpqVar;
            fhb fhbVar = fhb.f26875a;
        }
    }
}