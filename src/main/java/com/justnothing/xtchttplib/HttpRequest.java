package com.justnothing.xtchttplib;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;

public class HttpRequest {
    private String method;
    private String url;
    private String body;
    private String packageName;
    private Integer versionCode;
    private String versionName;
    private List<String> parameters;
    public HttpRequest() {
        this.parameters = new ArrayList<>();
    }

    public HttpRequest(String method, String url, String body, String packageName) {
        this.method = method;
        this.url = url;
        this.body = body;
        this.packageName = packageName;
        this.parameters = new ArrayList<>();
    }

    public HttpRequest(String method, String url, String body, String packageName, Integer versionCode, String versionName) {
        this.method = method;
        this.url = url;
        this.body = body;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.parameters = new ArrayList<>();
    }

    public static HttpRequest fromJson(JSONObject json) {
        HttpRequest request = new HttpRequest();
        request.setMethod(json.optString("method", "GET"));
        request.setUrl(json.optString("url", ""));
        request.setBody(json.optString("body", ""));
        request.setPackageName(json.optString("packageName", ""));
        if (json.has("versionCode")) {
            request.setVersionCode(json.optInt("versionCode"));
        }
        if (json.has("versionName")) {
            request.setVersionName(json.optString("versionName", ""));
        }
        if (json.has("parameters")) {
            org.json.JSONArray paramsArray = json.optJSONArray("parameters");
            if (paramsArray != null) {
                for (int i = 0; i < paramsArray.length(); i++) {
                    request.getParameters().add(paramsArray.optString(i, ""));
                }
            }
        }
        return request;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("method", method);
        json.put("url", url);
        json.put("body", body);
        json.put("packageName", packageName);
        if (versionCode != null) {
            json.put("versionCode", versionCode);
        }
        if (versionName != null) {
            json.put("versionName", versionName);
        }
        if (parameters != null && !parameters.isEmpty()) {
            org.json.JSONArray paramsArray = new org.json.JSONArray();
            for (String param : parameters) {
                paramsArray.put(param);
            }
            json.put("parameters", paramsArray);
        }
        return json;
    }
    
    /**
     * 格式化请求体，替换参数模板（根据保存的参数列表）
     */
    public String getFormattedBody(DeviceInfo device) {
        if (body == null || body.trim().isEmpty()) {
            return body;
        }
        
        if (parameters == null || parameters.isEmpty()) {
            return body;
        }
        
        // 直接使用参数名列表进行格式化
        ParameterManager paramManager = new ParameterManager();
        return paramManager.formatRequestBodyWithParameters(body, device, parameters);
    }
    
    /**
     * 检查是否有参数
     */
    public boolean hasParameters() {
        return parameters != null && !parameters.isEmpty();
    }
    
    /**
     * 获取参数数量
     */
    public int getParameterCount() {
        return parameters != null ? parameters.size() : 0;
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return method + " " + url;
    }
}
