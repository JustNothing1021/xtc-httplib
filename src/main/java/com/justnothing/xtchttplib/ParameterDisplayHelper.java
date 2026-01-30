package com.justnothing.xtchttplib;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数显示帮助类
 * 负责参数名和显示文本之间的转换
 * 显示格式可以灵活修改，不影响数据存储
 */
public class ParameterDisplayHelper {
    
    /**
     * 将参数名列表转换为显示文本列表
     * @param parameterNames 参数名列表，如 ["serverInner", "deviceId"]
     * @return 显示文本列表，如 ["ServerInner (serverInner) -> %1$s", "DeviceName (deviceId) -> %2$s"]
     */
    public static List<String> toDisplayList(List<String> parameterNames) {
        List<String> displayList = new ArrayList<>();
        if (parameterNames == null || parameterNames.isEmpty()) {
            return displayList;
        }
        
        ParameterManager paramManager = new ParameterManager();
        for (int i = 0; i < parameterNames.size(); i++) {
            String paramName = parameterNames.get(i);
            Parameter param = paramManager.getParameterByName(paramName);
            if (param != null) {
                // 格式: "显示名称 (参数名) -> %1$s"
                String displayText = param.getDisplayName() + " (" + param.getName() + ") -> %" + (i + 1) + "$s";
                displayList.add(displayText);
            } else {
                // 如果找不到参数，使用原始参数名
                String displayText = paramName + " -> %" + (i + 1) + "$s";
                displayList.add(displayText);
            }
        }
        return displayList;
    }
    
    /**
     * 将显示文本列表转换为参数名列表
     * @param displayList 显示文本列表，如 ["ServerInner (serverInner) -> %1$s", "DeviceName (deviceId) -> %2$s"]
     * @return 参数名列表，如 ["serverInner", "deviceId"]
     */
    public static List<String> fromDisplayList(List<String> displayList) {
        List<String> parameterNames = new ArrayList<>();
        if (displayList == null || displayList.isEmpty()) {
            return parameterNames;
        }
        
        for (String displayText : displayList) {
            String paramName = extractParameterName(displayText);
            if (paramName != null) {
                parameterNames.add(paramName);
            }
        }
        return parameterNames;
    }
    
    /**
     * 从显示文本中提取参数名
     * 格式: "显示名称 (参数名) -> %1$s" 或 "显示名称 (参数名)"
     */
    private static String extractParameterName(String displayText) {
        if (displayText == null || displayText.isEmpty()) {
            return null;
        }
        
        // 格式: "显示名称 (参数名) -> %1$s" 或 "显示名称 (参数名)"
        int startIndex = displayText.lastIndexOf("(");
        int endIndex = displayText.lastIndexOf(")");
        
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            String paramName = displayText.substring(startIndex + 1, endIndex).trim();
            if (!paramName.isEmpty()) {
                return paramName;
            }
        }
        
        return null;
    }
    
    /**
     * 生成单个参数的显示文本
     * @param paramName 参数名
     * @param index 参数位置索引（从1开始）
     * @return 显示文本，如 "ServerInner (serverInner) -> %1$s"
     */
    public static String toDisplayText(String paramName, int index) {
        ParameterManager paramManager = new ParameterManager();
        Parameter param = paramManager.getParameterByName(paramName);
        if (param != null) {
            return param.getDisplayName() + " (" + param.getName() + ") -> %" + index + "$s";
        } else {
            return paramName + " -> %" + index + "$s";
        }
    }
    
    /**
     * 获取参数的显示名称（不带位置信息）
     * @param paramName 参数名
     * @return 显示名称，如 "ServerInner (serverInner)"
     */
    public static String getDisplayName(String paramName) {
        ParameterManager paramManager = new ParameterManager();
        Parameter param = paramManager.getParameterByName(paramName);
        if (param != null) {
            return param.getDisplayName() + " (" + param.getName() + ")";
        } else {
            return paramName;
        }
    }
}