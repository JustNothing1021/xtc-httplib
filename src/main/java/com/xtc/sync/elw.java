package com.xtc.sync;

// import android.os.Build;
import android.text.TextUtils;

import com.justnothing.xtchttplib.ContextManager;
// import com.xtc.sync.cst;

/* compiled from: WatchModelUtil.java */
/* loaded from: classes.dex */
public class elw {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f26091a = null;

    /* renamed from: a, reason: collision with other field name */
    private final String f8966a = "WatchModelUtil";

    /* renamed from: b, reason: collision with root package name */
    private String f26092b = "";
    private String c = "";
    private String d = "";
    private String e;
    private String f;

    public ContextManager contextManager;
    
    public elw(ContextManager contextManager) {
        this.contextManager = contextManager;
    }

    public void a(String str) {
        // els.m4920a(elr.V, str);
        // TODO: SETPROP
    }

    public String a() {
        String h;
        if (!TextUtils.isEmpty(f)) {
            return f;
        }
        String j = j();
        if (j.equals("") || m4937k()) {
            h = h();
            if (h.equals("")) {
                h = b() + k();
            }
        } else {
            if (b().equals("I13") && j.equals("ID")) {
                return "IDI13";
            }
            h = b() + "-" + j + k();
        }
        f = h;
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        // return els.a(elr.aH, "IB");
        return contextManager.getInnerModel();
    }

    private String h() {
        // return els.a(elr.V, "");
        return contextManager.getServerInner();
    }

    private String i() {
        // return els.a(elr.aL, "");
        return contextManager.getLocale();
    }

    private String j() {
        // return els.a(elr.aK, "");
        return contextManager.getRegion();
    }

    public String c() {
        // return Locale.getDefault().getLanguage();
        return contextManager.getLanguage();
    }

    public String d() {
        if (m4938l()) {
            return l();
        }
        return m();
    }

    private String k() {
        // String a2 = els.a(elr.aJ, "");
        String a2 = contextManager.getInnerModelEx();
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        return "-" + a2;
    }

    private String l() {
        String b2 = b();
        String i = i();
        if (e(b2)) {
            if (i.equals(ema.a("zh", "CN"))) {
                return "CN";
            }
            String j = j();
            if (!j.equals("")) {
                return j;
            }
            String h = h();
            if (h.equals("")) {
                return "";
            }
            int length = h.length();
            return h.substring(length - 2, length);
        }
        if (i.equals("")) {
            return "";
        }
        int length2 = i.length();
        return i.substring(length2 - 2, length2);
    }

    private String m() {
        return j();
    }

    @Deprecated
    /* renamed from: a, reason: collision with other method in class */
    public boolean m4926a() {
        return b("TH");
    }

    @Deprecated
    /* renamed from: b, reason: collision with other method in class */
    public boolean m4928b() {
        return b("ID");
    }

    @Deprecated
    /* renamed from: c, reason: collision with other method in class */
    public boolean m4929c() {
        return (b("CN") || b("")) ? false : true;
    }

    @Deprecated
    /* renamed from: a, reason: collision with other method in class */
    public String e() {
        if (TextUtils.isEmpty(f26092b)) {
            // f26092b = els.a(elr.aF, elr.b.c.f26083a);
            f26092b = contextManager.getWatchModel();
        }
        return f26092b;
    }

    public String f() {
        if (TextUtils.isEmpty(c)) {
            // c = els.a(elr.aG, "");
            c = contextManager.getWatchPriModel();
        }
        if (TextUtils.isEmpty(c)) {
            if (m4927a("I12")) {
                c = n();
            } else if (m4927a("IB")) {
                c = o();
            } else {
                c = e();
            }
        }
        return c;
    }

    private String n() {
        return contextManager.getShowModel();
    }

    private String o() {
        return contextManager.getShowModel();
    }

    @Deprecated
    /* renamed from: d, reason: collision with other method in class */
    public boolean m4930d() {
        return m4927a("IB");
    }

    @Deprecated
    /* renamed from: e, reason: collision with other method in class */
    public boolean m4931e() {
        return m4927a("I12");
    }

    @Deprecated
    /* renamed from: f, reason: collision with other method in class */
    public boolean m4932f() {
        return m4927a("I13");
    }

    @Deprecated
    /* renamed from: g, reason: collision with other method in class */
    public boolean m4933g() {
        return m4927a(elr.b.a.o);
    }

    @Deprecated
    /* renamed from: h, reason: collision with other method in class */
    public boolean m4934h() {
        return m4927a(elr.b.a.p);
    }

    @Deprecated
    /* renamed from: i, reason: collision with other method in class */
    public boolean m4935i() {
        return m4927a(elr.b.a.q);
    }

    @Deprecated
    /* renamed from: j, reason: collision with other method in class */
    public boolean m4936j() {
        return m4927a(elr.b.a.t);
    }

    public boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            dkw.e(f8966a, "isModel error: innerModel == null || isModel == null");
            return false;
        }
        return str2.equals(str.split("-")[0]);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m4927a(String str) {
        if (str == null) {
            dkw.e(f8966a, "isModel error: isModel");
            return false;
        }
        return a(b(), str);
    }

    public boolean b(String str) {
        if (str == null) {
            dkw.e(f8966a, "isRegion error: isRegion");
            return false;
        }
        return d().equals(str);
    }

    public boolean c(String str) {
        if (str == null) {
            dkw.e(f8966a, "checkRegionOfInnerModel error: isRegion == null");
            return false;
        }
        return b(a(), str);
    }

    public boolean b(String str, String str2) {
        if (str == null || str2 == null) {
            dkw.e(f8966a, "checkRegionOfInnerModel error: innerModel == null || isRegion == null");
            return false;
        }
        if (str2.equals("")) {
            return !str.contains("-");
        }
        return str.endsWith(str2);
    }

    public boolean a(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            dkw.e(f8966a, "checkModelAndRegionOfInnerModel error: innerModel == null || isModel == null || isRegion == null");
            return false;
        }
        return str.equals(str2 + "-" + str3);
    }

    /* renamed from: k, reason: collision with other method in class */
    public boolean m4937k() {
        return !m4938l();
    }

    /* renamed from: l, reason: collision with other method in class */
    public boolean m4938l() {
        return emc.f26106b.equals(contextManager.getHardware());
    }

    public boolean d(String str) {
        if (str != null) {
            return str.equals(elr.b.a.d) || str.equals(elr.b.a.k) || str.equals(elr.b.a.w) || str.equals(elr.b.a.x) || str.equals(elr.b.a.y) || str.equals(elr.b.a.z) || str.equals(elr.b.a.B) || str.equals(elr.b.a.C) || str.equals(elr.b.a.D) || str.equals(elr.b.a.E);
        }
        dkw.e(f8966a, "isWatchModelY error: innerModel == null");
        return false;
    }

    /* renamed from: m, reason: collision with other method in class */
    boolean m4939m() {
        return e(b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e(String str) {
        if (str == null) {
            dkw.e(f8966a, "isModelRegionChangable error: model == null");
            return false;
        }
        String str2 = str.split("-")[0];
        return str2.equals("I13") || str2.equals(elr.b.a.t) || str2.equals(elr.b.a.M);
    }

    /* renamed from: n, reason: collision with other method in class */
    public boolean m4940n() {
        if (TextUtils.isEmpty(d)) {
            d = contextManager.getBuildType();
        }
        return "userdebug".equals(d);
    }

    public String g() {
        if (e == null) {
            // e = els.a(elr.aI, "");
            e = contextManager.getCaremeOsVersion();
        }
        return e;
    }

    /* renamed from: o, reason: collision with other method in class */
    public boolean m4941o() {
        if (f26091a == null) {
            f26091a = Boolean.valueOf(!TextUtils.isEmpty(g()));
        }
        return f26091a.booleanValue();
    }
}
