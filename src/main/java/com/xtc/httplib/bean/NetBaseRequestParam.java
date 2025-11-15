package com.xtc.httplib.bean;

/* loaded from: classes2.dex */
public class NetBaseRequestParam {
    private String accountId;
    private String appId;
    private String deviceId;
    private String imFlag;
    private String mac;
    private String machineId;
    private String program;
    private Long registId;
    private String requestId;
    private String timestamp;
    private String token;

    /* loaded from: classes2.dex */
    public interface AppId {
        public static final String WATCH = "2";
    }

    /* loaded from: classes2.dex */
    public interface ImFlag {
        public static final String No = "";
        public static final String Yes = "1";
    }

    /* loaded from: classes2.dex */
    public interface Program {
        public static final String Watch = "watch";
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getMachineId() {
        return this.machineId;
    }

    public void setMachineId(String str) {
        this.machineId = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String str) {
        this.accountId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String str) {
        this.program = str;
    }

    public String getImFlag() {
        return this.imFlag;
    }

    public void setImFlag(String str) {
        this.imFlag = str;
    }

    public Long getRegistId() {
        return this.registId;
    }

    public void setRegistId(Long l) {
        this.registId = l;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String toString() {
        return "NetBaseRequestParam{appId='" + this.appId + "', token='" + this.token + "', timestamp='" + this.timestamp + "', machineId='" + this.machineId + "', mac='" + this.mac + "', accountId='" + this.accountId + "', deviceId='" + this.deviceId + "', program='" + this.program + "', imFlag='" + this.imFlag + "', registId=" + this.registId + ", requestId=" + this.requestId + '}';
    }
}