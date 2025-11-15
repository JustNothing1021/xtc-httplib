package com.xtc.sync;

// import com.xtc.clockwidget.compose.view.BackgroundPluginView;
import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

/* compiled from: Intrinsics.java */
/* loaded from: classes2.dex */
public class fpf {
    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private fpf() {
    }

    public static String a(String str, Object obj) {
        return str + obj;
    }

    public static void a(Object obj) {
        if (obj == null) {
            b();
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            b(str);
        }
    }

    public static void a() {
        throw ((KotlinNullPointerException) a(new KotlinNullPointerException()));
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m7311a(String str) {
        throw ((KotlinNullPointerException) a(new KotlinNullPointerException(str)));
    }

    public static void b() {
        throw ((NullPointerException) a(new NullPointerException()));
    }

    public static void b(String str) {
        throw ((NullPointerException) a(new NullPointerException(str)));
    }

    public static void c(String str) {
        throw ((UninitializedPropertyAccessException) a(new UninitializedPropertyAccessException(str)));
    }

    public static void d(String str) {
        c("lateinit property " + str + " has not been initialized");
    }

    public static void c() {
        throw ((AssertionError) a(new AssertionError()));
    }

    public static void e(String str) {
        throw ((AssertionError) a(new AssertionError(str)));
    }

    public static void d() {
        throw ((IllegalArgumentException) a(new IllegalArgumentException()));
    }

    public static void f(String str) {
        throw ((IllegalArgumentException) a(new IllegalArgumentException(str)));
    }

    public static void e() {
        throw ((IllegalStateException) a(new IllegalStateException()));
    }

    public static void g(String str) {
        throw ((IllegalStateException) a(new IllegalStateException(str)));
    }

    public static void b(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((IllegalStateException) a(new IllegalStateException(str + " must not be null")));
    }

    public static void c(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) a(new NullPointerException(str + " must not be null")));
    }

    public static void a(Object obj, String str, String str2) {
        if (obj != null) {
            return;
        }
        throw ((IllegalStateException) a(new IllegalStateException("Method specified as non-null returned null: " + str + "." + str2)));
    }

    public static void d(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str)));
        }
    }

    public static void b(Object obj, String str, String str2) {
        if (obj != null) {
            return;
        }
        throw ((IllegalStateException) a(new IllegalStateException("Field specified as non-null is null: " + str + "." + str2)));
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str)));
        }
    }

    public static void f(Object obj, String str) {
        if (obj == null) {
            k(str);
        }
    }

    public static void g(Object obj, String str) {
        if (obj == null) {
            l(str);
        }
    }

    private static void k(String str) {
        throw ((IllegalArgumentException) a(new IllegalArgumentException(a(str))));
    }

    private static void l(String str) {
        throw ((NullPointerException) a(new NullPointerException(a(str))));
    }

    private static String a(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean a(Double d, Double d2) {
        if (d == null) {
            if (d2 == null) {
                return true;
            }
        } else if (d2 != null && d.doubleValue() == d2.doubleValue()) {
            return true;
        }
        return false;
    }

    public static boolean a(Double d, double d2) {
        return d != null && d.doubleValue() == d2;
    }

    public static boolean a(double d, Double d2) {
        return d2 != null && d == d2.doubleValue();
    }

    public static boolean a(Float f, Float f2) {
        if (f == null) {
            if (f2 == null) {
                return true;
            }
        } else if (f2 != null && f.floatValue() == f2.floatValue()) {
            return true;
        }
        return false;
    }

    public static boolean a(Float f, float f2) {
        return f != null && f.floatValue() == f2;
    }

    public static boolean a(float f, Float f2) {
        return f2 != null && f == f2.floatValue();
    }

    public static void f() {
        h("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void h(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void a(int i, String str) {
        f();
    }

    public static void a(int i, String str, String str2) {
        h(str2);
    }

    public static void g() {
        f();
    }

    public static void i(String str) {
        h(str);
    }

    public static void j(String str) throws ClassNotFoundException {
        String replace = str.replace('/', '.');
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) a(new ClassNotFoundException("Class " + replace + " is not found. Please update the Kotlin runtime to the latest version", e)));
        }
    }

    public static void a(String str, String str2) throws ClassNotFoundException {
        String replace = str.replace('/', '.');
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) a(new ClassNotFoundException("Class " + replace + " is not found: this code requires the Kotlin runtime of version at least " + str2, e)));
        }
    }

    private static <T extends Throwable> T a(T t) {
        return (T) a((Throwable) t, fpf.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    /* compiled from: Intrinsics.java */
    /* loaded from: classes2.dex */
    public static class a {
        private a() {
        }
    }
}