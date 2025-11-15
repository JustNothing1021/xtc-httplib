package com.xtc.sync;

/* compiled from: LogMask.java */
/* loaded from: classes.dex */
public class dkv {
    public static String a(String str) {
        return m4067a(str) ? str : a(str, 4, Math.max(1, str.length() - 5));
    }

    public static String b(String str) {
        return m4067a(str) ? str : a(str, 4, Math.max(1, str.length() - 5));
    }

    public static String c(String str) {
        if (m4067a(str)) {
            return str;
        }
        if (str.contains(":")) {
            return a(str, 3, Math.max(1, str.length() - 4));
        }
        return a(str, 2, Math.max(1, str.length() - 3));
    }

    public static String d(String str) {
        return m4067a(str) ? str : a(str, 4, Math.max(1, str.length() - 5));
    }

    public static String e(String str) {
        return m4067a(str) ? str : a(str, 2, Math.max(1, str.length() - 3));
    }

    public static String f(String str) {
        return m4067a(str) ? str : a(str, 1, Math.max(1, str.length() - 2));
    }

    private static String a(String str, int i, int i2) {
        if (m4067a(str) || i >= str.length() || i > i2) {
            return str;
        }
        int max = Math.max(i, 0);
        int min = Math.min(i2, str.length() - 1);
        StringBuilder sb = new StringBuilder();
        if (max > 0) {
            sb.append((CharSequence) str, 0, max);
        }
        if (max <= min) {
            while (max <= min) {
                sb.append("*");
                max++;
            }
        }
        if (min < str.length() - 1) {
            sb.append((CharSequence) str, min + 1, str.length());
        }
        return sb.toString();
    }

    /* renamed from: a, reason: collision with other method in class */
    private static boolean m4067a(String str) {
        return str == null || str.length() == 0;
    }
}