package com.xtc.sync;

import java.util.UUID;

/* compiled from: UUIDUtil.java */
/* loaded from: classes2.dex */
public class ekp {
    public static String a() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}