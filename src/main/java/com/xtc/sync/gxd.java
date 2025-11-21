package com.xtc.sync;

import java.nio.charset.Charset;
import kotlin.Metadata;

/* compiled from: Charsets.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class gxd {

    /* renamed from: a, reason: collision with root package name */
    public static final gxd f28087a = new gxd();

    /* renamed from: a, reason: collision with other field name */
    public static final Charset f11382a;

    /* renamed from: b, reason: collision with root package name */
    public static final Charset f28088b;
    public static final Charset c;
    public static final Charset d;
    public static final Charset e;
    public static final Charset f;
    private static Charset g;
    private static Charset h;
    private static Charset i;

    private gxd() {
    }

    static {
        Charset forName = Charset.forName("UTF-8");
        fpf.c(forName, "forName(\"UTF-8\")");
        f11382a = forName;
        Charset forName2 = Charset.forName("UTF-16");
        fpf.c(forName2, "forName(\"UTF-16\")");
        f28088b = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        fpf.c(forName3, "forName(\"UTF-16BE\")");
        c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        fpf.c(forName4, "forName(\"UTF-16LE\")");
        d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        fpf.c(forName5, "forName(\"US-ASCII\")");
        e = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        fpf.c(forName6, "forName(\"ISO-8859-1\")");
        f = forName6;
    }

    public final Charset a() {
        Charset charset = g;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32");
        fpf.c(forName, "forName(\"UTF-32\")");
        g = forName;
        return forName;
    }

    public final Charset b() {
        Charset charset = h;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32LE");
        fpf.c(forName, "forName(\"UTF-32LE\")");
        h = forName;
        return forName;
    }

    public final Charset c() {
        Charset charset = i;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32BE");
        fpf.c(forName, "forName(\"UTF-32BE\")");
        i = forName;
        return forName;
    }
}