package com.xtc.sync;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/* compiled from: MD5Util.java */
/* loaded from: classes2.dex */
public class ekk {
    public static final String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i = 0;
            for (byte b2 : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String a(String str) {
        return a(str.getBytes(Charset.forName("utf-8")));
    }
}