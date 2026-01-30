package com.justnothing.xtchttplib;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数管理器，负责参数的管理和模板替换
 * 支持所有格式化符号：%s, %d, %f, %x, %c等
 */
public class ParameterManager {
    private static final Pattern PERCENT_PATTERN = Pattern.compile("%([0-9]+\\$)?([a-zA-Z%])");
    private Map<String, Parameter> parameterMap;
    
    public ParameterManager() {
        initializeParameters();
    }
    
    /**
     * 初始化所有可用参数（支持信息栏所有内容）
     */
    private void initializeParameters() {
        parameterMap = new HashMap<>();
        
        addParameter(new Parameter("deviceId", "DeviceId", "设备信息", "设备在我们应用中的唯一标识符"));
        addParameter(new Parameter("deviceName", "DeviceName", "设备信息", "设备在我们应用中的显示名称"));

        addParameter(new Parameter("platform", "Platform", "设备信息", "设备平台（Android）"));
        addParameter(new Parameter("osVersion", "OsVersion", "设备信息", "操作系统版本号"));
        addParameter(new Parameter("androidSdk", "AndroidSdk", "设备信息", "Android SDK版本"));
        addParameter(new Parameter("innerModel", "InnerModel", "设备信息", "设备内部型号"));
        addParameter(new Parameter("buildRelease", "BuildRelease", "设备信息", "构建版本号"));
        addParameter(new Parameter("softVersion", "SoftVersion", "设备信息", "软件版本号"));
        
        addParameter(new Parameter("packageName", "PackageName", "应用信息", "当前应用的包名"));
        addParameter(new Parameter("versionCode", "VersionCode", "应用信息", "应用的版本代码"));
        addParameter(new Parameter("versionName", "VersionName", "应用信息", "应用的版本名称"));
        addParameter(new Parameter("appName", "AppName", "应用信息", "应用的显示名称"));
        
        addParameter(new Parameter("grey", "Grey", "网络加密", "环境灰度值"));
        addParameter(new Parameter("ts", "Ts", "网络加密", "Token状态"));
        addParameter(new Parameter("ae", "Ae", "网络加密", "加密Key"));
        addParameter(new Parameter("rsaPublicKey", "RsaPublicKey", "网络加密", "RSA公钥"));
        addParameter(new Parameter("selfRsaPublicKey", "SelfRsaPublicKey", "网络加密", "自签名RSA公钥"));
        addParameter(new Parameter("httpHeadParam", "HttpHeadParam", "网络加密", "HTTP头参数"));
        addParameter(new Parameter("encSwitch", "EncSwitch", "网络加密", "加密开关状态"));
        
        addParameter(new Parameter("watchId", "WatchId", "设备标识", "手表设备ID"));
        addParameter(new Parameter("macAddr", "MacAddr", "设备标识", "设备MAC地址"));
        addParameter(new Parameter("bindNumber", "BindNumber", "设备标识", "绑定号"));
        
        addParameter(new Parameter("locale", "Locale", "区域设置", "设备语言区域设置"));
        addParameter(new Parameter("region", "Region", "区域设置", "设备地区设置"));
        addParameter(new Parameter("serverInner", "ServerInner", "区域设置", "内部服务器代号"));
        addParameter(new Parameter("language", "Language", "区域设置", "设备语言设置"));
        addParameter(new Parameter("timeZone", "TimeZone", "区域设置", "设备时区设置"));
        addParameter(new Parameter("dataCenterCode", "DataCenterCode", "区域设置", "数据中心代号"));
        
        addParameter(new Parameter("watchModel", "WatchModel", "设备型号", "手表型号名称"));
        addParameter(new Parameter("watchPriModel", "WatchPriModel", "设备型号", "主要设备型号"));
        addParameter(new Parameter("innerModelEx", "InnerModelEx", "设备型号", "内部型号扩展信息"));
        addParameter(new Parameter("showModel", "ShowModel", "设备型号", "显示给用户的型号"));
        addParameter(new Parameter("caremeOsVersion", "CaremeOsVersion", "设备型号", "CaremeOS版本"));
        
        addParameter(new Parameter("buildType", "BuildType", "系统构建", "一般填user"));
        addParameter(new Parameter("hardware", "Hardware", "系统构建", "硬件平台信息"));
        addParameter(new Parameter("chipId", "ChipId", "系统构建", "设备芯片ID"));
        
        addParameter(new Parameter("timestamp", "Timestamp", "系统参数", "当前时间戳（ISO格式）"));
        addParameter(new Parameter("currentTime", "CurrentTime", "系统参数", "当前时间（yyyy-MM-dd HH:mm:ss）"));
        addParameter(new Parameter("requestId", "RequestId", "系统参数", "请求的唯一标识符"));
        addParameter(new Parameter("sessionId", "SessionId", "系统参数", "会话的唯一标识符"));
        
    }
    
    private void addParameter(Parameter parameter) {
        parameterMap.put(parameter.getName(), parameter);
    }
    
    /**
     * 获取所有可用参数
     */
    public List<Parameter> getAvailableParameters() {
        return new ArrayList<>(parameterMap.values());
    }
    
    /**
     * 按分类获取参数
     */
    public List<Parameter> getParametersByCategory(String category) {
        List<Parameter> result = new ArrayList<>();
        for (Parameter param : parameterMap.values()) {
            if (param.getCategory().equals(category)) {
                result.add(param);
            }
        }
        return result;
    }
    
    /**
     * 根据参数名获取参数对象
     */
    public Parameter getParameterByName(String paramName) {
        if (paramName == null || paramName.isEmpty()) {
            return null;
        }
        return parameterMap.get(paramName);
    }
    
    /**
     * 格式化请求体，替换参数模板（支持位置参数）
     */
    public String formatRequestBody(String template, DeviceInfo device) {
        if (template == null || template.trim().isEmpty()) {
            return template;
        }
        
        // 使用String.format进行格式化，支持位置参数
        if (containsPercentParameters(template)) {
            return formatWithPositionalParameters(template, device);
        }
        
        return template;
    }
    
    /**
     * 根据参数列表格式化请求体
     */
    public String formatRequestBodyWithParameters(String template, DeviceInfo device, List<String> parameterNames) {
        if (template == null || template.trim().isEmpty() || parameterNames == null || parameterNames.isEmpty()) {
            return template;
        }
        
        try {
            // 构建参数值数组
            List<Object> paramValues = new ArrayList<>();
            for (String paramName : parameterNames) {
                Object paramValue = getParameterValueByName(paramName, device);
                paramValues.add(paramValue);
            }
            
            // 使用String.format进行格式化
            return String.format(template, paramValues.toArray());
        } catch (Exception e) {
            // 如果格式化失败，返回原始模板
            log("参数格式化失败: " + e.getMessage());
            return template;
        }
    }
    
    /**
     * 根据参数名获取参数值
     */
    public Object getParameterValueByName(String paramName, DeviceInfo device) {
        if (device == null) {
            return "";
        }
        
        ContextManager context = device.getContext();
        
        // 根据参数名返回相应的值
        switch (paramName) {
            // 设备信息参数
            case "deviceId":
                return device.getAdbDeviceId() != null ? device.getAdbDeviceId() : "";
            case "deviceName":
                return device.getDeviceName() != null ? device.getDeviceName() : "";
            case "platform":
                return "Android";
            case "osVersion":
                return context != null && context.getBuildRelease() != null ? 
                    context.getBuildRelease() : "";
            case "androidSdk":
                return context != null && context.getAndroidSdk() != null ? 
                    context.getAndroidSdk() : 0;
            case "innerModel":
                return context != null && context.getInnerModel() != null ? 
                    context.getInnerModel() : "";
            case "buildRelease":
                return context != null && context.getBuildRelease() != null ? 
                    context.getBuildRelease() : "";
            case "softVersion":
                return context != null && context.getSoftVersion() != null ? 
                    context.getSoftVersion() : "";
            
            // 应用信息参数
            case "packageName":
                return context != null ? context.getPackageName() : "";
            case "versionCode":
                return context != null && context.getPackageVersionCode() != null ? 
                    context.getPackageVersionCode() : 0;
            case "versionName":
                return context != null ? context.getPackageVersionName() : "";
            case "appName":
                return context != null && context.getPackageName() != null ? 
                    context.getPackageName() : "";
            
            // 网络加密参数
            case "grey":
                return context != null && context.getGrey() != null ? 
                    context.getGrey() : "";
            case "ts":
                return context != null && context.getTs() != null ? 
                    context.getTs() : 0;
            case "ae":
                return context != null && context.getAe() != null ? 
                    context.getAe() : "";
            case "rsaPublicKey":
                return context != null && context.getRsaPublicKey() != null ? 
                    context.getRsaPublicKey() : "";
            case "selfRsaPublicKey":
                return context != null && context.getSelfRsaPublicKey() != null ? 
                    context.getSelfRsaPublicKey() : "";
            case "httpHeadParam":
                return context != null && context.getHttpHeadParam() != null ? 
                    context.getHttpHeadParam() : "";
            case "encSwitch":
                return context != null && context.getEncSwitch() != null ? 
                    context.getEncSwitch() : "";
            
            // 设备标识参数
            case "watchId":
                return context != null && context.getWatchId() != null ? 
                    context.getWatchId() : "";
            case "macAddr":
                return context != null && context.getMacAddr() != null ? 
                    context.getMacAddr() : "";
            case "bindNumber":
                return context != null && context.getBindNumber() != null ? 
                    context.getBindNumber() : "";
            
            // 区域设置参数
            case "locale":
                return context != null && context.getLocale() != null ? 
                    context.getLocale() : "";
            case "region":
                return context != null && context.getRegion() != null ? 
                    context.getRegion() : "";
            case "serverInner":
                return context != null && context.getServerInner() != null ? 
                    context.getServerInner() : "";
            case "language":
                return context != null && context.getLanguage() != null ? 
                    context.getLanguage() : "";
            case "timeZone":
                return context != null && context.getTimeZone() != null ? 
                    context.getTimeZone() : "";
            case "dataCenterCode":
                return context != null && context.getDataCenterCode() != null ? 
                    context.getDataCenterCode() : "";
            
            // 设备型号参数
            case "watchModel":
                return context != null && context.getWatchModel() != null ? 
                    context.getWatchModel() : "";
            case "watchPriModel":
                return context != null && context.getWatchPriModel() != null ? 
                    context.getWatchPriModel() : "";
            case "innerModelEx":
                return context != null && context.getInnerModelEx() != null ? 
                    context.getInnerModelEx() : "";
            case "showModel":
                return context != null && context.getShowModel() != null ? 
                    context.getShowModel() : "";
            case "caremeOsVersion":
                return context != null && context.getCaremeOsVersion() != null ? 
                    context.getCaremeOsVersion() : "";
            
            // 系统构建参数
            case "buildType":
                return context != null && context.getBuildType() != null ? 
                    context.getBuildType() : "";
            case "hardware":
                return context != null && context.getHardware() != null ? 
                    context.getHardware() : "";
            case "chipId":
                return context != null && context.getChipId() != null ? 
                    context.getChipId() : "";
            
            // 系统参数
            case "timestamp":
                return System.currentTimeMillis();
            case "currentTime":
                return java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            case "requestId":
                return java.util.UUID.randomUUID().toString();
            case "sessionId":
                return "session_" + System.currentTimeMillis();
            
            default:
                return "未找到参数: " + paramName;
        }
    }
    
    /**
     * 使用位置参数格式化请求体
     */
    private String formatWithPositionalParameters(String template, DeviceInfo device) {
        try {
            ContextManager context = device.getContext();
            
            // 构建参数值数组，按顺序填充所有可用参数
            List<Object> paramValues = new ArrayList<>();
            
            // 添加设备信息参数
            paramValues.add(device.getAdbDeviceId() != null ? device.getAdbDeviceId() : "");
            paramValues.add(device.getDeviceName() != null ? device.getDeviceName() : "");
            paramValues.add("Android");
            paramValues.add(context != null && context.getBuildRelease() != null ? 
                context.getBuildRelease() : "");
            paramValues.add(context != null && context.getAndroidSdk() != null ? 
                context.getAndroidSdk() : 0);
            paramValues.add(context != null && context.getInnerModel() != null ? 
                context.getInnerModel() : "");
            paramValues.add(context != null && context.getBuildRelease() != null ? 
                context.getBuildRelease() : "");
            paramValues.add(context != null && context.getSoftVersion() != null ? 
                context.getSoftVersion() : "");
            
            // 添加应用信息参数
            paramValues.add(context != null ? context.getPackageName() : "");
            paramValues.add(context != null && context.getPackageVersionCode() != null ? 
                context.getPackageVersionCode() : 0);
            paramValues.add(context != null ? context.getPackageVersionName() : "");
            paramValues.add(context != null && context.getPackageName() != null ? 
                context.getPackageName() : "");
            
            // 添加网络加密参数
            paramValues.add(context != null && context.getGrey() != null ? 
                context.getGrey() : "");
            paramValues.add(context != null && context.getTs() != null ? 
                context.getTs() : 0);
            paramValues.add(context != null && context.getAe() != null ? 
                context.getAe() : "");
            paramValues.add(context != null && context.getRsaPublicKey() != null ? 
                context.getRsaPublicKey() : "");
            paramValues.add(context != null && context.getSelfRsaPublicKey() != null ? 
                context.getSelfRsaPublicKey() : "");
            paramValues.add(context != null && context.getHttpHeadParam() != null ? 
                context.getHttpHeadParam() : "");
            paramValues.add(context != null && context.getEncSwitch() != null ? 
                context.getEncSwitch() : "");
            
            // 添加设备标识参数
            paramValues.add(context != null && context.getWatchId() != null ? 
                context.getWatchId() : "");
            paramValues.add(context != null && context.getMacAddr() != null ? 
                context.getMacAddr() : "");
            paramValues.add(context != null && context.getBindNumber() != null ? 
                context.getBindNumber() : "");
            
            // 添加区域设置参数
            paramValues.add(context != null && context.getLocale() != null ? 
                context.getLocale() : "");
            paramValues.add(context != null && context.getRegion() != null ? 
                context.getRegion() : "");
            paramValues.add(context != null && context.getServerInner() != null ? 
                context.getServerInner() : "");
            paramValues.add(context != null && context.getLanguage() != null ? 
                context.getLanguage() : "");
            paramValues.add(context != null && context.getTimeZone() != null ? 
                context.getTimeZone() : "");
            paramValues.add(context != null && context.getDataCenterCode() != null ? 
                context.getDataCenterCode() : "");
            
            // 添加设备型号参数
            paramValues.add(context != null && context.getWatchModel() != null ? 
                context.getWatchModel() : "");
            paramValues.add(context != null && context.getWatchPriModel() != null ? 
                context.getWatchPriModel() : "");
            paramValues.add(context != null && context.getInnerModelEx() != null ? 
                context.getInnerModelEx() : "");
            paramValues.add(context != null && context.getShowModel() != null ? 
                context.getShowModel() : "");
            paramValues.add(context != null && context.getCaremeOsVersion() != null ? 
                context.getCaremeOsVersion() : "");
            
            // 添加系统构建参数
            paramValues.add(context != null && context.getBuildType() != null ? 
                context.getBuildType() : "");
            paramValues.add(context != null && context.getHardware() != null ? 
                context.getHardware() : "");
            paramValues.add(context != null && context.getChipId() != null ? 
                context.getChipId() : "");
            
            // 添加系统参数
            paramValues.add(System.currentTimeMillis());
            paramValues.add(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            paramValues.add(java.util.UUID.randomUUID().toString());
            paramValues.add("session_" + System.currentTimeMillis());
            
            // 使用String.format进行格式化
            return String.format(template, paramValues.toArray());
        } catch (Exception e) {
            // 如果格式化失败，返回原始模板
            log("位置参数格式化失败: " + e.getMessage());
            return template;
        }
    }
    

    
    /**
     * 检查模板中是否包含%格式参数
     */
    public boolean containsPercentParameters(String template) {
        return template != null && PERCENT_PATTERN.matcher(template).find();
    }
    
    /**
     * 检查模板中是否包含任何格式的参数
     */
    public boolean containsAnyParameters(String template) {
        return containsPercentParameters(template);
    }
    
    /**
     * 获取模板中的所有参数名
     */
    public List<String> extractParameterNames(String template) {
        List<String> paramNames = new ArrayList<>();
        if (template == null) return paramNames;
        
        // 由于使用格式化符号，这个方法不再需要具体参数名
        return paramNames;
    }
    
    /**
     * 日志记录
     */
    private void log(String message) {
        System.out.println("[ParameterManager] " + message);
    }
}