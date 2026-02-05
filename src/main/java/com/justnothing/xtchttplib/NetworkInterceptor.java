package com.justnothing.xtchttplib;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class NetworkInterceptor implements Interceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(NetworkInterceptor.class);
    
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequest(request);
        Response response = chain.proceed(request);
        logResponse(response);
        return response;
    }
    
    private void logRequest(Request request) {
        logger.info("┌────── 请求开始 ──────");
        logger.info("│ 方法: " + request.method());
        logger.info("│ URL: " + request.url());
        
        Headers headers = request.headers();
        if (headers.size() > 0) {
            logger.info("│ 请求头:");
            for (int i = 0; i < headers.size(); i++) {
                logger.info("│   " + headers.name(i) + ": " + headers.value(i));
            }
        }

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            try {
                String contentType = requestBody.contentType() != null ? 
                    requestBody.contentType().toString() : "unknown";
                logger.info("│ 请求体[" + contentType + "]:");
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                String bodyString = buffer.readString(StandardCharsets.UTF_8);
                if (bodyString == null || bodyString.isEmpty()) {
                    logger.info("│   (空)");
                } else {
                    String[] lines = bodyString.split("\n");
                    for (String line : lines) {
                        logger.info("│   " + line);
                    }
                }
            } catch (IOException e) {
                logger.error("│   无法读取请求体: " + e.getMessage());
            }
        } else {
            logger.info("│ 请求体: (无)");
        }
        logger.info("└────── 请求结束 ──────");
    }
    
    private void logResponse(Response response) {
        logger.info("┌────── 响应开始 ──────");
        logger.info("│ 状态码: " + response.code());
        logger.info("│ 消息: " + response.message());
        logger.info("│ URL: " + response.request().url());
        
        Headers headers = response.headers();
        if (headers.size() > 0) {
            logger.info("│ 响应头:");
            for (int i = 0; i < headers.size(); i++) {
                logger.info("│   " + headers.name(i) + ": " + headers.value(i));
            }
        }
        
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            try {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.getBuffer();
                
                String contentType = responseBody.contentType() != null ? 
                    Objects.requireNonNull(responseBody.contentType()).toString() : "unknown";
                logger.info("│ 响应体[" + contentType + "]:");
                
                String bodyString = buffer.clone().readString(StandardCharsets.UTF_8);
                if (bodyString.isEmpty()) {
                    logger.info("│   (空)");
                } else {
                    String[] lines = bodyString.split("\n");
                    for (String line : lines) {
                        logger.info("│   " + line);
                    }
                }
            } catch (IOException e) {
                logger.error("│   无法读取响应体: " + e.getMessage());
            }
        } else {
            logger.info("│ 响应体: (无)");
        }
        logger.info("└────── 响应结束 ──────");
    }
}