package com.justnothing.xtchttplib;

/**
 * 参数类，用于管理请求体中的可替换参数
 */
public class Parameter {
    private final String name;
    private final String displayName;
    private final String category;
    private final String description;
    
    public Parameter(String name, String displayName, String category, String description) {
        this.name = name;
        this.displayName = displayName;
        this.category = category;
        this.description = description;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDisplayName() { return displayName; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    
    @Override
    public String toString() {
        return displayName + " (" + name + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Parameter parameter = (Parameter) obj;
        return name.equals(parameter.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}