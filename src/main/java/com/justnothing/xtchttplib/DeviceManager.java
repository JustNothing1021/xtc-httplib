package com.justnothing.xtchttplib;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceManager {
    private Map<String, DeviceInfo> devices;
    private String currentDeviceId;
    private String storagePath;

    public DeviceManager() {
        this.devices = new HashMap<>();
        this.storagePath = DataPathManager.getDevicesJsonPath().toString();
        DataPathManager.ensureDataDirExists();
    }

    public DeviceManager(String storagePath) {
        this.devices = new HashMap<>();
        this.storagePath = storagePath;
    }

    public void addDevice(DeviceInfo device) {
        devices.put(device.getBindNumber(), device);
        saveDevices();
    }

    public void addDevice(String bindNumber, ContextManager context) {
        DeviceInfo device = new DeviceInfo(bindNumber, context);
        addDevice(device);
    }

    public void switchDevice(String bindNumber) {
        if (!devices.containsKey(bindNumber)) {
            throw new IllegalArgumentException("设备不存在: " + bindNumber);
        }
        currentDeviceId = bindNumber;
        saveDevices();
    }

    public DeviceInfo getCurrentDevice() {
        if (currentDeviceId == null || !devices.containsKey(currentDeviceId)) {
            return null;
        }
        return devices.get(currentDeviceId);
    }

    public void removeDevice(String bindNumber) {
        devices.remove(bindNumber);
        if (bindNumber.equals(currentDeviceId)) {
            currentDeviceId = null;
        }
        saveDevices();
    }

    public List<DeviceInfo> listDevices() {
        return new ArrayList<>(devices.values());
    }

    public DeviceInfo getDevice(String bindNumber) {
        return devices.get(bindNumber);
    }

    public boolean hasDevice(String bindNumber) {
        return devices.containsKey(bindNumber);
    }

    public void updateDevice(String bindNumber, ContextManager context) {
        if (!devices.containsKey(bindNumber)) {
            throw new IllegalArgumentException("设备不存在: " + bindNumber);
        }
        DeviceInfo device = devices.get(bindNumber);
        device.setContext(context);
        device.setLastUpdated(new java.util.Date());
        saveDevices();
    }

    public void renameDevice(String bindNumber, String newName) {
        if (!devices.containsKey(bindNumber)) {
            throw new IllegalArgumentException("设备不存在: " + bindNumber);
        }
        DeviceInfo device = devices.get(bindNumber);
        device.setDeviceName(newName);
        saveDevices();
    }

    public void saveDevices() {
        try {
            JSONObject json = new JSONObject();
            JSONArray devicesArray = new JSONArray();
            
            for (DeviceInfo device : devices.values()) {
                devicesArray.put(device.toJson());
            }
            
            json.put("devices", devicesArray);
            json.put("currentDevice", currentDeviceId != null ? currentDeviceId : "");
            
            Files.write(Paths.get(storagePath), json.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("保存设备列表失败: " + e.getMessage());
        }
    }

    public void loadDevices() {
        try {
            File file = new File(storagePath);
            if (!file.exists()) {
                return;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(storagePath)));
            JSONObject json = new JSONObject(content);
            
            JSONArray devicesArray = json.getJSONArray("devices");
            for (int i = 0; i < devicesArray.length(); i++) {
                DeviceInfo device = DeviceInfo.fromJson(devicesArray.getJSONObject(i));
                devices.put(device.getBindNumber(), device);
            }
            
            String currentDevice = json.optString("currentDevice", "");
            if (!currentDevice.isEmpty() && devices.containsKey(currentDevice)) {
                currentDeviceId = currentDevice;
            }
        } catch (Exception e) {
            System.err.println("加载设备列表失败: " + e.getMessage());
        }
    }

    public String getCurrentDeviceId() {
        return currentDeviceId;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getDeviceCount() {
        return devices.size();
    }
}
