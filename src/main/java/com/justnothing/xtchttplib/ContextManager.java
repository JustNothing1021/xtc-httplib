package com.justnothing.xtchttplib;


public class ContextManager {
    private String grey; 
    private Integer ts;
    private String ae;
    private String rsaPublicKey;
    private String selfRsaPublicKey;
    private String httpHeadParam;
    private String encSwitch;
    private String watchId; // return context.getContentResolver().getType(Uri.parse(d));
    private String macAddr; // 形如 02:00:00:00:00:00
    private String bindNumber; // ro.boot.bindnumber
    private Integer packageVersionCode;
    private String packageVersionName;
    private String packageName;
    private Integer androidSdk;
    private String innerModel = "IB"; // ro.product.innermodel
    private String serverInner; // persist.sys.serverinner
    private String locale; // ro.product.locale
    private String region; // ro.product.locale.region
    private String language; // Locale.getDefault().getLanguage()
    private String innerModelEx; // ro.product.innermodel.ex
    private String watchModel = "Z3"; // ro.product.model
    private String watchPriModel; // ro.product.pri.model
    private String buildType = "user"; // ro.build.type
    private String hardware = "qcom"; // ro.hardware
    private String caremeOsVersion; // ro.product.careme.version
    private String showModel; // ro.product.showmodel
    private String timeZone = "GMT+08:00"; // TODO: 原逻辑为elt.a();
    private String dataCenterCode = "CN-BJ"; // TODO: 原逻辑为byk.a(this.f5323a).m2760a();
    private String chipId; // ro.boot.xtc.chipid
    private String buildRelease; // ro.build.version.release
    private String softVersion; // ro.product.current.softversion

    private static ContextManager instance;

    public static ContextManager getInstance() {
        return instance;
    }
    public static void setInstance(ContextManager instance) {
        ContextManager.instance = instance;
    }
    public String getEncSwitch() {
        return encSwitch;
    }
    public void setEncSwitch(String encSwitch) {
        this.encSwitch = encSwitch;
    }

    public String getGrey() {
        return grey;
    }
    public void setGrey(String grey) {
        this.grey = grey;
    }
    public String getHttpHeadParam() {
        return httpHeadParam;
    }
    public void setHttpHeadParam(String httpHeadParam) {
        this.httpHeadParam = httpHeadParam;
    }


    public String getRsaPublicKey() {
        return rsaPublicKey;
    }
    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }
    public void setSelfRsaPublicKey(String selfRsaPublicKey) {
        this.selfRsaPublicKey = selfRsaPublicKey;
    }
    public String getSelfRsaPublicKey() {
        return selfRsaPublicKey;
    }

    public String getWatchId() {
        return watchId;
    }
    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }
    public String getMacAddr() {
        return macAddr;
    }
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }
    public String getBindNumber() {
        return bindNumber;
    }
    public void setBindNumber(String bindNumber) {
        this.bindNumber = bindNumber;
    }
    public String getAe() {
        return ae;
    }
    public void setAe(String ae) {
        this.ae = ae;
    }
    public Integer getTs() {
        return ts;
    }
    public void setTs(Integer ts) {
        this.ts = ts;
    }
    public String getPackageName() {
        return packageName;
    }
    public Integer getPackageVersionCode() {
        return packageVersionCode;
    }
    public String getPackageVersionName() {
        return packageVersionName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public void setPackageVersionCode(Integer packageVersionCode) {
        this.packageVersionCode = packageVersionCode;
    }
    public void setPackageVersionName(String packageVersionName) {
        this.packageVersionName = packageVersionName;
    }
    public Integer getAndroidSdk() {
        return androidSdk;
    }
    public void setAndroidSdk(Integer androidSdk) {
        this.androidSdk = androidSdk;
    }
    public void setInnerModel(String innerModel) {
        this.innerModel = innerModel;
    }
    public String getInnerModel() {
        return innerModel;
    }
    public String getBuildType() {
        return buildType;
    }
    public String getCaremeOsVersion() {
        return caremeOsVersion;
    }
    public String getHardware() {
        return hardware;
    }
    public String getInnerModelEx() {
        return innerModelEx;
    }
    public String getLanguage() {
        return language;
    }
    public String getLocale() {
        return locale;
    }
    public String getRegion() {
        return region;
    }
    public String getServerInner() {
        return serverInner;
    }
    public String getWatchModel() {
        return watchModel;
    }
    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }
    public void setCaremeOsVersion(String caremeOsVersion) {
        this.caremeOsVersion = caremeOsVersion;
    }
    public void setHardware(String hardware) {
        this.hardware = hardware;
    }
    public void setInnerModelEx(String innerModelEx) {
        this.innerModelEx = innerModelEx;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setServerInner(String serverInner) {
        this.serverInner = serverInner;
    }
    public void setWatchModel(String watchModel) {
        this.watchModel = watchModel;
    }
    public String getWatchPriModel() {
        return watchPriModel;
    }
    public void setWatchPriModel(String watchPriModel) {
        this.watchPriModel = watchPriModel;
    }
    public String getShowModel() {
        return showModel;
    }
    public void setShowModel(String showModel) {
        this.showModel = showModel;
    }
    public String getTimeZone() {
        return timeZone;
    }
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public String getDataCenterCode() {
        return dataCenterCode;
    }
    public void setDataCenterCode(String dataCenterCode) {
        this.dataCenterCode = dataCenterCode;
    }
    public void setBuildRelease(String buildRelease) {
        this.buildRelease = buildRelease;
    }
    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }
    public String getBuildRelease() {
        return buildRelease;
    }
    public String getChipId() {
        return chipId;
    }
    public String getSoftVersion() {
        return softVersion;
    }
}
