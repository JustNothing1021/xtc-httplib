package com.justnothing.xtchttplib;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// DeepSeek写的
public class NetworkInterceptor implements Interceptor {
    
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequest(request);
        Response response = chain.proceed(request);
        logResponse(response);
        return response;
    }
    
    private void logRequest(Request request) {
        System.out.println("┌────── 请求开始 ──────");
        System.out.println("│ 方法: " + request.method());
        System.out.println("│ URL: " + request.url());
        
        // 请求头
        Headers headers = request.headers();
        if (headers.size() > 0) {
            System.out.println("│ 请求头:");
            for (int i = 0; i < headers.size(); i++) {
                System.out.println("│   " + headers.name(i) + ": " + headers.value(i));
            }
        }
        
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            try {
                RequestBody copyRequestBody = requestBody;
                String contentType = copyRequestBody.contentType() != null ? 
                    copyRequestBody.contentType().toString() : "unknown";
                System.out.println("│ 请求体[" + contentType + "]:");
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                
                String bodyString = buffer.readString(StandardCharsets.UTF_8);
                if (!bodyString.isEmpty()) {
                    String[] lines = bodyString.split("\n");
                    for (String line : lines) {
                        System.out.println("│   " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("│   无法读取请求体: " + e.getMessage());
            }
        }
        System.out.println("└────── 请求结束 ──────");
    }
    
    private void logResponse(Response response) {
        System.out.println("┌────── 响应开始 ──────");
        System.out.println("│ 状态码: " + response.code());
        System.out.println("│ 消息: " + response.message());
        System.out.println("│ URL: " + response.request().url());
        
        Headers headers = response.headers();
        if (headers.size() > 0) {
            System.out.println("│ 响应头:");
            for (int i = 0; i < headers.size(); i++) {
                System.out.println("│   " + headers.name(i) + ": " + headers.value(i));
            }
        }
        
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            try {
                ResponseBody copyResponseBody = responseBody;
                BufferedSource source = copyResponseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                
                String contentType = copyResponseBody.contentType() != null ? 
                    copyResponseBody.contentType().toString() : "unknown";
                System.out.println("│ 响应体[" + contentType + "]:");

                
                String bodyString = buffer.clone().readString(StandardCharsets.UTF_8);
                if (!bodyString.isEmpty()) {
                    String[] lines = bodyString.split("\n");
                    for (String line : lines) {
                        System.out.println("│   " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("│   无法读取响应体: " + e.getMessage());
            }
        }
        System.out.println("└────── 响应结束 ──────");
    }
}