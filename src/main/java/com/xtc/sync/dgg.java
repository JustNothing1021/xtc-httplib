package com.xtc.sync;

import com.xtc.im.transpond.ITranspondCallback;
import com.xtc.im.transpond.TranspondAdapter;

/* compiled from: TranspondManager.java */
/* loaded from: classes5.dex */
public class dgg {

    /* renamed from: a, reason: collision with root package name */
    private static TranspondAdapter f24496a;

    public static void a(TranspondAdapter transpondAdapter) {
        f24496a = transpondAdapter;
    }

    public static boolean a(String str, int i, byte[] bArr, byte[] bArr2, ITranspondCallback iTranspondCallback) {
        TranspondAdapter transpondAdapter = f24496a;
        if (transpondAdapter != null) {
            return transpondAdapter.transpondHttp(str, i, bArr, bArr2, iTranspondCallback);
        }
        return false;
    }
}