package com.xtc.httplib.bean;


import android.content.Context;
import android.text.TextUtils;

import com.justnothing.xtchttplib.ContextManager;
import com.xtc.sync.ceo;
import com.xtc.sync.dkw;
import com.xtc.sync.elr;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public class WatchInfo implements DeviceInfo {
    private static final String TAG = ceo.a("WatchInfo");
    public static final String UNKOWN = "unkown";
    private String androidVersion;
    private String bindNumber;
    private String chipId;
    private Context context;
    private String watchVersion;

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getMacAddr() {
        // return "unkown";
        return ContextManager.getInstance().getMacAddr();
    }

    public WatchInfo(Context context) {
        // this.context = context.getApplicationContext();
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getBindNumber() {
        if (!TextUtils.isEmpty(this.bindNumber) && !"unkown".equals(this.bindNumber)) {
            return this.bindNumber;
        }
        // this.bindNumber = getSystemProperty(elr.aP);
        // if (TextUtils.isEmpty(this.bindNumber)) {
        //     this.bindNumber = "unkown";
        // }
        this.bindNumber = ContextManager.getInstance().getBindNumber();
        return this.bindNumber;
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getChipId() {
        if (!TextUtils.isEmpty(this.chipId)) {
            return this.chipId;
        }
        // this.chipId = getSystemProperty("ro.boot.xtc.chipid");
        this.chipId = ContextManager.getInstance().getChipId();
        if (TextUtils.isEmpty(this.chipId)) {
            this.chipId = "unkown";
        }
        return this.chipId;
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getAndroidVersion() {
        if (!TextUtils.isEmpty(this.androidVersion)) {
            return this.androidVersion;
        }
        // this.androidVersion = getSystemProperty("ro.build.version.release");
        this.androidVersion = ContextManager.getInstance().getBuildRelease();
        if (TextUtils.isEmpty(this.androidVersion)) {
            this.androidVersion = "unkown";
        }
        return this.androidVersion;
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getWatchVersion() {
        if (!TextUtils.isEmpty(this.watchVersion)) {
            return this.watchVersion;
        }
        // this.watchVersion = getSystemProperty(elr.aE);
        this.watchVersion = ContextManager.getInstance().getSoftVersion();
        if (TextUtils.isEmpty(this.watchVersion)) {
            this.watchVersion = "unkown";
        }
        return this.watchVersion;
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getSystemProperty(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (ClassNotFoundException e) {
            dkw.b(TAG, e);
            return null;
        } catch (IllegalAccessException e2) {
            dkw.b(TAG, e2);
            return null;
        } catch (NoSuchMethodException e3) {
            dkw.b(TAG, e3);
            return null;
        } catch (InvocationTargetException e4) {
            dkw.b(TAG, e4);
            return null;
        }
    }

    @Override // com.xtc.httplib.bean.DeviceInfo
    public String getBuildType() {
        // return getSystemProperty(elr.aN);
        return ContextManager.getInstance().getBuildType();
    }
}
