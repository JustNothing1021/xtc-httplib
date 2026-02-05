package com.justnothing.xtchttplib;

import org.json.JSONObject;

public class PackageInfo {
    private String packageName;
    private Integer versionCode;
    private String versionName;
    private String packageDisplayName;

    public PackageInfo() {
    }

    public PackageInfo(String packageName, Integer versionCode, String versionName, String packageDisplayName) {
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.packageDisplayName = packageDisplayName;
    }

    public static PackageInfo fromJson(JSONObject json) {
        PackageInfo info = new PackageInfo();
        info.setPackageName(json.optString("packageName", ""));
        info.setVersionCode(json.optInt("versionCode", 0));
        info.setVersionName(json.optString("versionName", ""));
        info.setPackageDisplayName(json.optString("packageDisplayName", ""));
        return info;
    }

    @Override
    public String toString() {
        if (packageDisplayName != null && !packageDisplayName.isEmpty()) {
            return packageDisplayName + " (" + packageName + ")";
        }
        return packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPackageDisplayName() {
        return packageDisplayName;
    }

    public void setPackageDisplayName(String packageDisplayName) {
        this.packageDisplayName = packageDisplayName;
    }
}
