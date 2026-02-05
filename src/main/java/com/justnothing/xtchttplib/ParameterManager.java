package com.justnothing.xtchttplib;

import java.util.*;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 参数管理器，负责参数的管理和模板替换
 * 支持所有格式化符号：%s, %d, %f, %x, %c等
 */
public class ParameterManager {
    private static final Pattern PERCENT_PATTERN = Pattern.compile("%([0-9]+\\$)?([a-zA-Z%])");
    private Map<String, Parameter> parameterMap;
    private Logger logger = LoggerFactory.getLogger(ParameterManager.class);
    
    public ParameterManager() {
        initializeParameters();
    }
    
    /**
     * 初始化所有可用参数（支持信息栏所有内容）
     */
    private void initializeParameters() {
        parameterMap = new HashMap<>();
        
        addParameter(new Parameter("deviceId", "设备ID", "设备信息", "设备在我们应用中的唯一标识符"));
        addParameter(new Parameter("deviceName", "设备名称", "设备信息", "设备在我们应用中的显示名称"));
        addParameter(new Parameter("requestId", "当前请求ID", "系统参数", "请求的唯一标识符"));
        addParameter(new Parameter("sessionId", "当前会话ID", "系统参数", "会话的唯一标识符"));
        addParameter(new Parameter("timestamp", "时间戳", "系统参数", "当前时间戳（ISO格式）"));
        addParameter(new Parameter("currentTime", "当前时间信息", "系统参数", "当前时间（yyyy-MM-dd HH:mm:ss）"));
        // 下面来自ContextManager
        addParameter(new Parameter("androidSdk", "AndroidSdk", "设备信息", "Android SDK版本"));
        addParameter(new Parameter("innerModel", "InnerModel", "设备信息", "设备内部型号"));
        addParameter(new Parameter("buildRelease", "BuildRelease", "设备信息", "构建版本号"));
        addParameter(new Parameter("softVersion", "SoftVersion", "设备信息", "软件版本号"));
        addParameter(new Parameter("packageName", "PackageName", "应用信息", "当前应用的包名"));
        addParameter(new Parameter("versionCode", "VersionCode", "应用信息", "应用的版本代码"));
        addParameter(new Parameter("versionName", "VersionName", "应用信息", "应用的版本名称"));
        addParameter(new Parameter("appName", "AppName", "应用信息", "应用的显示名称"));
        addParameter(new Parameter("grey", "Grey", "网络加密", "环境灰度值"));
        addParameter(new Parameter("ts", "TokenState", "网络加密", "Token状态"));
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
     * 根据参数名获取参数对象
     */
    public Parameter getParameterObject(String paramName) {
        if (paramName == null || paramName.isEmpty()) {
            return null;
        }
        return parameterMap.get(paramName);
    }
    

    public String formatRequestBody(String template, DeviceInfo device, List<String> parameterNames) {
        if (template == null || template.trim().isEmpty() || parameterNames == null || parameterNames.isEmpty()) {
            return template;
        }
        
        try {
            List<Object> paramValues = new ArrayList<>();
            for (String paramName : parameterNames) {
                Object paramValue = getParameterValueByName(paramName, device);
                paramValues.add(paramValue);
            }
            
            return String.format(template, paramValues.toArray());
        } catch (Exception e) {
            // 如果格式化失败，返回原始模板
            logger.error("参数格式化失败: " + e.getMessage());
            return template;
        }
    }
    
    /**
     * 根据参数Map格式化请求体
     */
    public String formatRequestBodyWithParameterList(String template, DeviceInfo device, List<String> parameterNames) {
        if (template == null || template.trim().isEmpty() || parameterNames == null || parameterNames.isEmpty()) {
            return template;
        }
        
        try {
            // 构建参数值数组，按参数Map中的顺序
            List<Object> paramValues = new ArrayList<>();
            for (String paramName : parameterNames) {
                Object paramValue = getParameterValueByName(paramName, device);
                paramValues.add(paramValue);
            }
            
            return String.format(template, paramValues.toArray());
        } catch (Exception e) {
            logger.error("参数格式化失败: " + e.getMessage());
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
        
        return switch (paramName) {

            case "deviceId" -> device.getAdbDeviceId() != null ? device.getAdbDeviceId() : "";
            case "deviceName" -> device.getDeviceName() != null ? device.getDeviceName() : "";
            case "platform" -> "Android";
            case "androidSdk" -> context != null && context.getAndroidSdk() != null ?
                    context.getAndroidSdk() : 0;
            case "innerModel" -> context != null && context.getInnerModel() != null ?
                    context.getInnerModel() : "";
            case "buildRelease" -> context != null && context.getBuildRelease() != null ?
                    context.getBuildRelease() : "";
            case "softVersion" -> context != null && context.getSoftVersion() != null ?
                    context.getSoftVersion() : "";
            case "packageName" -> context != null ? context.getPackageName() : "";
            case "versionCode" -> context != null && context.getPackageVersionCode() != null ?
                    context.getPackageVersionCode() : 0;
            case "versionName" -> context != null ? context.getPackageVersionName() : "";
            case "appName" -> context != null && context.getPackageName() != null ?
                    context.getPackageName() : "";
            case "grey" -> context != null && context.getGrey() != null ?
                    context.getGrey() : "";
            case "ts" -> context != null && context.getTs() != null ?
                    context.getTs() : 0;
            case "ae" -> context != null && context.getAe() != null ?
                    context.getAe() : "";
            case "rsaPublicKey" -> context != null && context.getRsaPublicKey() != null ?
                    context.getRsaPublicKey() : "";
            case "selfRsaPublicKey" -> context != null && context.getSelfRsaPublicKey() != null ?
                    context.getSelfRsaPublicKey() : "";
            case "httpHeadParam" -> context != null && context.getHttpHeadParam() != null ?
                    context.getHttpHeadParam() : "";
            case "encSwitch" -> context != null && context.getEncSwitch() != null ?
                    context.getEncSwitch() : "";
            case "watchId" -> context != null && context.getWatchId() != null ?
                    context.getWatchId() : "";
            case "macAddr" -> context != null && context.getMacAddr() != null ?
                    context.getMacAddr() : "";
            case "bindNumber" -> context != null && context.getBindNumber() != null ?
                    context.getBindNumber() : "";
            case "locale" -> context != null && context.getLocale() != null ?
                    context.getLocale() : "";
            case "region" -> context != null && context.getRegion() != null ?
                    context.getRegion() : "";
            case "serverInner" -> context != null && context.getServerInner() != null ?
                    context.getServerInner() : "";
            case "language" -> context != null && context.getLanguage() != null ?
                    context.getLanguage() : "";
            case "timeZone" -> context != null && context.getTimeZone() != null ?
                    context.getTimeZone() : "";
            case "dataCenterCode" -> context != null && context.getDataCenterCode() != null ?
                    context.getDataCenterCode() : "";
            case "watchModel" -> context != null && context.getWatchModel() != null ?
                    context.getWatchModel() : "";
            case "watchPriModel" -> context != null && context.getWatchPriModel() != null ?
                    context.getWatchPriModel() : "";
            case "innerModelEx" -> context != null && context.getInnerModelEx() != null ?
                    context.getInnerModelEx() : "";
            case "showModel" -> context != null && context.getShowModel() != null ?
                    context.getShowModel() : "";
            case "caremeOsVersion" -> context != null && context.getCaremeOsVersion() != null ?
                    context.getCaremeOsVersion() : "";
            case "buildType" -> context != null && context.getBuildType() != null ?
                    context.getBuildType() : "";
            case "hardware" -> context != null && context.getHardware() != null ?
                    context.getHardware() : "";
            case "chipId" -> context != null && context.getChipId() != null ?
                    context.getChipId() : "";
            case "timestamp" -> System.currentTimeMillis();
            case "currentTime" ->
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            case "requestId" -> UUID.randomUUID().toString();
            case "sessionId" -> "session_" + System.currentTimeMillis();
            default -> "未找到参数: " + paramName;
        };
    }
    
    /**
     * 检查模板中是否包含%格式参数
     */
    public boolean containsPercentParameters(String template) {
        return template != null && PERCENT_PATTERN.matcher(template).find();
    }
    
    
}