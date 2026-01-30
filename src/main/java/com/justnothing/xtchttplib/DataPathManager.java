package com.justnothing.xtchttplib;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 数据文件路径管理工具类
 * 统一管理所有数据文件的存储路径，确保数据文件都存储在根目录的data文件夹中
 */
public class DataPathManager {
    
    private static final String DATA_DIR = "data";
    
    /**
     * 获取数据文件夹路径
     */
    public static Path getDataDir() {
        return Paths.get(DATA_DIR);
    }
    
    /**
     * 获取设备列表文件路径
     */
    public static Path getDevicesJsonPath() {
        return getDataDir().resolve("devices.json");
    }
    
    /**
     * 获取包配置列表文件路径
     */
    public static Path getPackagesJsonPath() {
        return getDataDir().resolve("packages.json");
    }
    
    /**
     * 获取HTTP脚本文件路径
     */
    public static Path getHttpScriptsJsonPath() {
        return getDataDir().resolve("http_scripts.json");
    }
    
    /**
     * 确保数据文件夹存在
     */
    public static void ensureDataDirExists() {
        try {
            java.nio.file.Files.createDirectories(getDataDir());
        } catch (Exception e) {
            System.err.println("创建数据文件夹失败: " + e.getMessage());
        }
    }
    
    /**
     * 检查数据文件夹是否存在
     */
    public static boolean dataDirExists() {
        return java.nio.file.Files.exists(getDataDir());
    }
    
    /**
     * 获取默认的包配置资源路径（用于首次运行时创建默认配置）
     */
    public static String getDefaultPackagesResourcePath() {
        return "/packages.json";
    }
    
    /**
     * 获取默认的HTTP脚本资源路径（用于首次运行时创建默认配置）
     */
    public static String getDefaultScriptsResourcePath() {
        return "/default_scripts.json";
    }
    
    /**
     * 获取HTTP结果码资源路径（作为资源文件，不存储在data文件夹）
     */
    public static String getHttpResultResourcePath() {
        return "/http_result.json";
    }
    
    /**
     * 获取服务器结果码资源路径（作为资源文件，不存储在data文件夹）
     */
    public static String getServerCodeResultResourcePath() {
        return "/server_code_result.json";
    }
}