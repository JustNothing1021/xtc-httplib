package com.xtc.sync;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtil.java */
/* loaded from: classes2.dex */
public class ekg {

    /* renamed from: a, reason: collision with root package name */
    private static final String f26044a = "AES";

    public static String a(String str, String str2) {
        try {
            return ekh.a(m4856b(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr, String str) {
        try {
            return ekh.a(m4855a(bArr, str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2) {
        try {
            return new String(b(ekh.a(str), str2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static byte[] m4854a(String str, String str2) {
        try {
            return b(ekh.a(str), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    private static byte[] m4856b(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), f26044a);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] bytes = str.getBytes("utf-8");
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(bytes);
    }

    /* renamed from: a, reason: collision with other method in class */
    private static byte[] m4855a(byte[] bArr, String str) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), f26044a);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(bArr);
    }

    private static byte[] b(byte[] bArr, String str) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), f26044a);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr);
    }
}