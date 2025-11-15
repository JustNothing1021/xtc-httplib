package com.xtc.httplib.bean;

import android.text.TextUtils;

import com.xtc.sync.cfr;
import com.xtc.sync.dkw;
import com.xtc.utils.encode.JSONUtil;
import com.xtc.utils.security.XtcSecurity;
import java.util.Map;

/* loaded from: classes2.dex */
public class AppInfo {
    private static final String COLON = ":";
    private static final int ENCRYPT_LEN = 3;
    private static final String ENCRYPT_OPEN = "1";
    private static final boolean LOG_KEY = false;
    private static final String TAG = "AppInfo";
    private volatile String aesKey;
    private String encSwitch;
    private volatile String encryptEebbkKey;
    private String grey;
    private String httpHeadParam;
    private volatile int httpTokenState;
    private volatile String keyId;
    private String rsaPublicKey;
    private volatile String selfRsaPublicKey;
    private String version;

    public String getGrey() {
        return this.grey;
    }

    public void setGrey(String str) {
        this.grey = str;
    }

    private String getRsaPublicKey() {
        return this.rsaPublicKey;
    }

    public void setRsaPublicKey(String str) {
        this.rsaPublicKey = str;
    }

    public String getEncSwitch() {
        return this.encSwitch;
    }

    public void setEncSwitch(String str) {
        this.encSwitch = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    private boolean isEncrypt() {
        if (TextUtils.isEmpty(this.encSwitch)) {
            return true;
        }
        return "1".equals(this.encSwitch);
    }

    public int getRsaEncryptType() {
        if (!isEncrypt()) {
            return 0;
        }
        if (TextUtils.isEmpty(this.aesKey) || TextUtils.isEmpty(this.encryptEebbkKey) || TextUtils.isEmpty(this.keyId)) {
            return (TextUtils.isEmpty(this.selfRsaPublicKey) || TextUtils.isEmpty(this.keyId)) ? 1 : 2;
        }
        return 3;
    }

    public void setSelfRsaPublicKeyAndId(String str) {
        if (TextUtils.isEmpty(str)) {
            dkw.b(TAG, "setSelfRsaPublicKeyAndId: selfRsaPublicKeyAndId is empty");
            return;
        }
        String b2 = XtcSecurity.b(str);
        if (TextUtils.isEmpty(b2) || !b2.contains(":")) {
            dkw.b(TAG, "setSelfRsaPublicKeyAndId: secretKeyDecrypt error");
            // cer.m3029a().a(cer.f5251a, 3);
            return;
        }
        String[] split = b2.split(":");
        if (split.length < 2) {
            dkw.b(TAG, "setSelfRsaPublicKeyAndId: decryptKeyAndId len error");
            return;
        }
        this.keyId = split[0];
        this.selfRsaPublicKey = split[1];
        dkw.b(TAG, "setSelfRsaPublicKeyAndId: 秘钥解析正常 ");
    }

    public String getKeyId() {
        return this.keyId;
    }

    public String getHttpHeadParam() {
        return this.httpHeadParam;
    }

    public void setHttpHeadParam(String str) {
        this.httpHeadParam = str;
    }

    public String getPublicKey(int i) {
        return i == 2 ? this.selfRsaPublicKey : this.rsaPublicKey;
    }

    public Map<String, String> getHttpHeadParamMap() {
        if (!TextUtils.isEmpty(this.httpHeadParam)) {
            try {
                return (Map) JSONUtil.fromJSON(this.httpHeadParam, Map.class);
            } catch (Exception e) {
                dkw.d(TAG, "getHttpHeadParamMap", e);
            }
        }
        return null;
    }

    public String getEncryptEebbkKey() {
        if (TextUtils.isEmpty(this.encryptEebbkKey) || TextUtils.isEmpty(this.aesKey)) {
            return null;
        }
        return this.encryptEebbkKey;
    }

    public void setEncryptEebbkKey(String str) {
        if (TextUtils.isEmpty(str)) {
            dkw.b(TAG, "setEncryptEebbkKey: encryptEebbkKey is empty");
            return;
        }
        String b2 = XtcSecurity.b(str);
        if (TextUtils.isEmpty(b2) || !b2.contains(":")) {
            dkw.b(TAG, "setEncryptEebbkKey: Decrypt error");
            // ceu.a(azk.a(), 3);
            return;
        }
        String[] split = b2.split(":", 3);
        if (split.length < 3 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1]) || TextUtils.isEmpty(split[2])) {
            dkw.e(TAG, "setEncryptEebbkKey 解析异常");
            // ceu.a(azk.a(), 3);
        } else {
            dkw.b(TAG, "setEncryptEebbkKey: 解析正常 ");
            this.keyId = split[0];
            this.aesKey = split[1];
            this.encryptEebbkKey = split[2];
        }
    }

    public int getHttpTokenState() {
        return this.httpTokenState;
    }

    public void setHttpTokenState(int i) {
        // boolean z = !isHttpTokenValid();
        this.httpTokenState = i;
        // if (z && isHttpTokenValid()) {
        //     cex.a().m3044a();
        // }
    }

    public boolean isHttpTokenValid() {
        return this.httpTokenState == 0 || this.httpTokenState == 1;
    }

    public String getAesKey() {
        if (TextUtils.isEmpty(this.encryptEebbkKey) || TextUtils.isEmpty(this.aesKey)) {
            return cfr.f22951a;
        }
        return this.aesKey;
    }
}