package com.justnothing.xtchttplib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ADBConnector {
    private String adbPath = "adb";
    private Logger logger = LoggerFactory.getLogger(ADBConnector.class);

    public ADBConnector() {
    }

    public ADBConnector(String adbPath) {
        this.adbPath = adbPath;
    }

    public boolean isADBAvailable() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{adbPath, "version"});
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> detectDevices() {
        List<String> devices = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(new String[] {adbPath, "devices"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9:.-]+)\\s+device$");
            
            while ((line = reader.readLine()) != null) {
                logger.debug(line);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    devices.add(matcher.group(1));
                }
            }
            process.waitFor();
        } catch (Exception e) {
            System.err.println("检测设备失败: " + e.getMessage());
        }
        return devices;
    }

    public String executeCommand(String deviceId, String command) {
        try {
            String[] cmd;
            if (deviceId != null && !deviceId.isEmpty()) {
                cmd = new String[] {adbPath, "-s", deviceId, "shell", command};
            } else {
                cmd = new String[] {adbPath, "shell", command};
            }
            
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            process.waitFor();
            return output.toString();
        } catch (Exception e) {
            System.err.println("执行ADB命令失败: " + e.getMessage());
            return null;
        }
    }

    public ContextManager exportContext(String deviceId) {
        try {
            String output = executeCommand(deviceId, "methods --export-context");
            if (output == null || output.trim().isEmpty()) {
                return null;
            }
            return ContextManager.fromJson(output.trim());
        } catch (Exception e) {
            System.err.println("导出设备上下文失败: " + e.getMessage());
            return null;
        }
    }

    public String getAdbPath() {
        return adbPath;
    }

    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
    }
}
