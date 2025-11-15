package com.xtc.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/* compiled from: Log.java */
/* loaded from: classes.dex */
public class dku {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
    
    private static Logger f24775a = getLogger(dku.class);

    public static void a(Logger iLogger) {
        f24775a = iLogger;
    }

    public static Logger a() {
        return f24775a;
    }

    public static void a(String str, String str2) {
        f24775a.debug("[{}]{}", str, str2);
    }

    public static void b(String str, String str2) {
        f24775a.debug("[{}]{}", str, str2);
    }

    public static void c(String str, String str2) {
        f24775a.info("[{}]{}", str, str2);
    }

    public static void d(String str, String str2) {
        f24775a.warn("[{}]{}", str, str2);
    }

    public static void e(String str, String str2) {
        f24775a.error("[{}]{}", str, str2);
    }

    public static void f(String str, String str2) {
        f24775a.error("[{}]{}", str, str2);
    }

    public static void a(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Verbose, str, str2, th);
        f24775a.trace("[{}]{}", str, str2, th);
    }

    public static void b(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Debug, str, str2, th);
        f24775a.debug("[{}]{}", str, str2, th);
    }

    public static void c(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Info, str, str2, th);
        f24775a.info("[{}]{}", str, str2, th);
    }

    public static void d(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Warning, str, str2, th);
        f24775a.warn("[{}]{}", str, str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Error, str, str2, th);
        f24775a.error("[{}]{}", str, str2, th);
    }

    public static void f(String str, String str2, Throwable th) {
        // f24775a.log(ILogger.Level.Assert, str, str2, th);
        f24775a.error("[{}]{}", str, str2, th);
    }

    public static void a(String str, Throwable th) {
        // f24775a.log(ILogger.Level.Warning, str, "", th);
        f24775a.warn(str, th);
    }

    public static void b(String str, Throwable th) {
        // f24775a.log(ILogger.Level.Assert, str, "", th);
        f24775a.error(str, th);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m4066a() {
        // f24775a.flush();
        
    }

    public static void b() {
        // f24775a.close();
    }
}