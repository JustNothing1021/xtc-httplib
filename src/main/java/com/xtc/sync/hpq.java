package com.xtc.sync;

import java.util.Arrays;
import kotlin.Metadata;

/* compiled from: Segment.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0000J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokio/Segment;", "", "()V", "data", "", "pos", "", "limit", "shared", "", "owner", "([BIIZZ)V", "next", "prev", "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class hpq {

    /* renamed from: a, reason: collision with root package name */
    public static final a f28679a = new a(null);
    public static final int c = 8192;
    public static final int d = 1024;

    /* renamed from: a, reason: collision with other field name */
    public int f12033a;

    /* renamed from: a, reason: collision with other field name */
    public hpq f12034a;

    /* renamed from: a, reason: collision with other field name */
    public boolean f12035a;

    /* renamed from: a, reason: collision with other field name */
    public final byte[] f12036a;

    /* renamed from: b, reason: collision with root package name */
    public int f28680b;

    /* renamed from: b, reason: collision with other field name */
    public hpq f12037b;

    /* renamed from: b, reason: collision with other field name */
    public boolean f12038b;

    public hpq() {
        this.f12036a = new byte[8192];
        this.f12038b = true;
        this.f12035a = false;
    }

    public hpq(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        fpf.f(bArr, "data");
        this.f12036a = bArr;
        this.f12033a = i;
        this.f28680b = i2;
        this.f12035a = z;
        this.f12038b = z2;
    }

    public final hpq a() {
        this.f12035a = true;
        return new hpq(this.f12036a, this.f12033a, this.f28680b, true, false);
    }

    public final hpq b() {
        byte[] bArr = this.f12036a;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        fpf.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new hpq(copyOf, this.f12033a, this.f28680b, false, true);
    }

    public final hpq c() {
        hpq hpqVar = this.f12034a;
        if (hpqVar == this) {
            hpqVar = null;
        }
        hpq hpqVar2 = this.f12037b;
        if (hpqVar2 == null) {
            fpf.a();
        }
        hpqVar2.f12034a = this.f12034a;
        hpq hpqVar3 = this.f12034a;
        if (hpqVar3 == null) {
            fpf.a();
        }
        hpqVar3.f12037b = this.f12037b;
        hpq hpqVar4 = (hpq) null;
        this.f12034a = hpqVar4;
        this.f12037b = hpqVar4;
        return hpqVar;
    }

    public final hpq a(hpq hpqVar) {
        fpf.f(hpqVar, "segment");
        hpqVar.f12037b = this;
        hpqVar.f12034a = this.f12034a;
        hpq hpqVar2 = this.f12034a;
        if (hpqVar2 == null) {
            fpf.a();
        }
        hpqVar2.f12037b = hpqVar;
        this.f12034a = hpqVar;
        return hpqVar;
    }

    public final hpq a(int i) {
        hpq hpqVar;
        if (!(i > 0 && i <= this.f28680b - this.f12033a)) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (i >= 1024) {
            hpqVar = a();
        } else {
            hpq a2 = hpr.a();
            hos.a(this.f12036a, this.f12033a, a2.f12036a, 0, i);
            hpqVar = a2;
        }
        hpqVar.f28680b = hpqVar.f12033a + i;
        this.f12033a += i;
        hpq hpqVar2 = this.f12037b;
        if (hpqVar2 == null) {
            fpf.a();
        }
        hpqVar2.a(hpqVar);
        return hpqVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public final void m9402a() {
        int i = 0;
        if (!(this.f12037b != this)) {
            throw new IllegalStateException("cannot compact".toString());
        }
        hpq hpqVar = this.f12037b;
        if (hpqVar == null) {
            fpf.a();
        }
        if (hpqVar.f12038b) {
            int i2 = this.f28680b - this.f12033a;
            hpq hpqVar2 = this.f12037b;
            if (hpqVar2 == null) {
                fpf.a();
            }
            int i3 = 8192 - hpqVar2.f28680b;
            hpq hpqVar3 = this.f12037b;
            if (hpqVar3 == null) {
                fpf.a();
            }
            if (!hpqVar3.f12035a) {
                hpq hpqVar4 = this.f12037b;
                if (hpqVar4 == null) {
                    fpf.a();
                }
                i = hpqVar4.f12033a;
            }
            if (i2 > i3 + i) {
                return;
            }
            hpq hpqVar5 = this.f12037b;
            if (hpqVar5 == null) {
                fpf.a();
            }
            a(hpqVar5, i2);
            c();
            hpr.a(this);
        }
    }

    public final void a(hpq hpqVar, int i) {
        fpf.f(hpqVar, "sink");
        if (!hpqVar.f12038b) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i2 = hpqVar.f28680b;
        if (i2 + i > 8192) {
            if (hpqVar.f12035a) {
                throw new IllegalArgumentException();
            }
            int i3 = hpqVar.f12033a;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = hpqVar.f12036a;
            hos.a(bArr, i3, bArr, 0, i2 - i3);
            hpqVar.f28680b -= hpqVar.f12033a;
            hpqVar.f12033a = 0;
        }
        hos.a(this.f12036a, this.f12033a, hpqVar.f12036a, hpqVar.f28680b, i);
        hpqVar.f28680b += i;
        this.f12033a += i;
    }

    /* compiled from: Segment.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokio/Segment$Companion;", "", "()V", "SHARE_MINIMUM", "", "SIZE", "jvm"}, k = 1, mv = {1, 1, 11})
    /* loaded from: classes2.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(fou fouVar) {
            this();
        }
    }
}