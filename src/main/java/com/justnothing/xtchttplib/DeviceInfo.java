package com.justnothing.xtchttplib;

import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeviceInfo {
    private String bindNumber;
    private String deviceName;
    private ContextManager context;
    private Date lastUpdated;
    private String adbDeviceId;

    public DeviceInfo() {
    }

    public DeviceInfo(String bindNumber, ContextManager context) {
        this.bindNumber = bindNumber;
        this.context = context;
        this.lastUpdated = new Date();
        this.deviceName = context.getWatchModel();
    }

    public String getBindNumber() {
        return bindNumber;
    }

    public void setBindNumber(String bindNumber) {
        this.bindNumber = bindNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public ContextManager getContext() {
        return context;
    }

    public void setContext(ContextManager context) {
        this.context = context;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getAdbDeviceId() {
        return adbDeviceId;
    }

    public void setAdbDeviceId(String adbDeviceId) {
        this.adbDeviceId = adbDeviceId;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bindNumber", bindNumber);
        json.put("deviceName", deviceName);
        json.put("context", new JSONObject(context.toJson()));
        json.put("lastUpdated", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(lastUpdated));
        if (adbDeviceId != null) {
            json.put("adbDeviceId", adbDeviceId);
        }
        return json;
    }

    public static DeviceInfo fromJson(JSONObject json) throws Exception {
        DeviceInfo device = new DeviceInfo();
        device.setBindNumber(json.getString("bindNumber"));
        device.setDeviceName(json.getString("deviceName"));
        device.setContext(ContextManager.fromJson(json.getJSONObject("context").toString()));
        
        String lastUpdatedStr = json.getString("lastUpdated");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        device.setLastUpdated(sdf.parse(lastUpdatedStr));
        
        if (json.has("adbDeviceId")) {
            device.setAdbDeviceId(json.getString("adbDeviceId"));
        }
        
        return device;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (最后更新: %s)", 
            bindNumber, 
            deviceName, 
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(lastUpdated));
    }
}
