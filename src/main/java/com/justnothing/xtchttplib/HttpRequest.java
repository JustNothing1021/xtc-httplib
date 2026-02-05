package com.justnothing.xtchttplib;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtc.sync.cen;

import okhttp3.*;

import java.io.IOException;

public class HttpRequest {
    private String method;
    private String url;
    private String body;
    private String packageName;
    private Integer versionCode;
    private String versionName;
    private List<String> parameters;
    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    
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
            JSONArray paramsArray = json.optJSONArray("parameters");
            if (paramsArray != null) {
                for (int i = 0; i < paramsArray.length(); i++) {
                    String paramDisplay = paramsArray.optString(i, "");
                    if (paramDisplay != null && !paramDisplay.isEmpty()) {
                        request.getParameters().add(paramDisplay);
                    }
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
            JSONArray paramsArray = new JSONArray();
            for (String param : parameters) {
                paramsArray.put(param);
            }
            json.put("parameters", paramsArray);
        }
        return json;
    }

    public String getFormattedBody(DeviceInfo device) {
        if (body == null || body.trim().isEmpty()) {
            return body;
        }
        
        if (parameters == null || parameters.isEmpty()) {
            return body;
        }
        
        return new ParameterManager().formatRequestBody(body, device, parameters);
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
    

    public Response execute(DeviceInfo device) throws IOException {
        ContextManager context = device.getContext().clone();
        if (context == null) {
            logger.error("指定的设备没有提供ContextManager");
            throw new IllegalArgumentException("指定的设备没有提供ContextManager");
        }
        if (packageName != null && !packageName.isEmpty()) {
            context.setPackageName(packageName);
            if (versionCode != null) {
                context.setPackageVersionCode(versionCode);
            }
            if (versionName != null && !versionName.isEmpty()) {
                context.setPackageVersionName(versionName);
            }
            logger.info("本次设置应用包名为: " + packageName + "(版本号: " + versionCode + ", 版本名: " + versionName + ")");
        } else {
            logger.info("本次无需设置应用包名");
        }
        cen httpManager = com.xtc.sync.cen.a(context);
        OkHttpClient okHttpClient = ((com.xtc.sync.cft) httpManager.m3021a()).m3072a();
        String formattedBody = getFormattedBody(device);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        switch (method) {
            case "POST" -> {
                @SuppressWarnings("deprecation")
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("application/json"),
                        formattedBody
                );
                requestBuilder.post(requestBody);
            }
            case "GET" -> requestBuilder.get();
            case "PUT" -> {
                @SuppressWarnings("deprecation")
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("application/json"),
                        formattedBody
                );
                requestBuilder.put(requestBody);
            }
            case "DELETE" -> requestBuilder.delete();
            case "PATCH" -> {
                @SuppressWarnings("deprecation")
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("application/json"),
                        formattedBody
                );
                requestBuilder.patch(requestBody);
            }
            default -> throw new IllegalArgumentException("不支持的请求方法: " + method);
        }
        return okHttpClient.newCall(requestBuilder.build()).execute();
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
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", body='" + body + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
