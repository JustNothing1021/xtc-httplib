package com.xtc.sync;

import kotlin.Metadata;

/* compiled from: -Platform.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\f\u0010\t\u001a\u00020\u0003*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\u0003H\u0000*\n\u0010\f\"\u00020\r2\u00020\r*\n\u0010\u000e\"\u00020\u000f2\u00020\u000f*\n\u0010\u0010\"\u00020\u00112\u00020\u0011*\n\u0010\u0012\"\u00020\u00132\u00020\u0013*\n\u0010\u0014\"\u00020\u00152\u00020\u0015¨\u0006\u0016"}, d2 = {"arraycopy", "", "src", "", "srcPos", "", "dest", "destPos", "length", "asUtf8ToByteArray", "", "toUtf8String", "ArrayIndexOutOfBoundsException", "Ljava/lang/ArrayIndexOutOfBoundsException;", "JvmField", "Lkotlin/jvm/JvmField;", "JvmName", "Lkotlin/jvm/JvmName;", "JvmOverloads", "Lkotlin/jvm/JvmOverloads;", "JvmStatic", "Lkotlin/jvm/JvmStatic;", "jvm"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class hos {
    public static final void a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        fpf.f(bArr, "src");
        fpf.f(bArr2, "dest");
        System.arraycopy(bArr, i, bArr2, i2, i3);
    }

    public static final String a(byte[] bArr) {
        fpf.f(bArr, "$receiver");
        return new String(bArr, gxd.f11382a);
    }

    public static final byte[] a(String str) {
        fpf.f(str, "$receiver");
        byte[] bytes = str.getBytes(gxd.f11382a);
        fpf.b(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }
}