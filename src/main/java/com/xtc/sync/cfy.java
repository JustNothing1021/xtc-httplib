package com.xtc.sync;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


// DeepSeek写的

public class cfy implements Interceptor {

    private static final int MAX_LOG_LENGTH = 1000;
    private static final String ELLIPSIS = "…";
    private static final String TAG = "LogInterceptor";
    private static final String APPLICATION_TYPE = "application";
    private static final String JSON_SUBTYPE = "json";

    private static final ThreadLocal<StringBuilder> threadLocalStringBuilder = 
        ThreadLocal.withInitial(StringBuilder::new);

    // 重构变量名：f5348a -> httpClient
    private cem httpClient;

    public void a(cem httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = threadLocalStringBuilder.get();
        sb.setLength(0);

        Request request = chain.request();

        try {
            String requestId = UUID.randomUUID().toString();


            sb.append("--> ");
            Request processedRequest = processRequest(request, sb, requestId);
            sb.append('\n');

            long callStart = System.nanoTime();
            Response response = chain.proceed(processedRequest);
            long callTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - callStart);

            Response processedResponse = processResponse(response, callTime, sb);

            System.out.println("[HTTP] " + sb.toString());

            return processedResponse;

        } catch (Throwable th) {
            sb.append("<-- ").append(th);
            System.out.println("[HTTP] " + sb.toString());
            if (th instanceof IOException) {
                throw (IOException) th;
            }
            throw new IOException(th);
        }
    }

    private Request processRequest(Request request, StringBuilder sb, String requestId) throws IOException {
        Request.Builder builder = request.newBuilder();

        if (requestId != null) {
            builder.addHeader("uuid", requestId); // 原代码使用cfb.f
        }

        Request newRequest = builder.build();

        sb.append(newRequest.method());
        sb.append('(');
        sb.append(newRequest.url());
        sb.append(')');
        sb.append('\n');

        Headers headers = newRequest.headers();
        for (int i = 0; i < headers.size(); i++) {
            sb.append(headers.name(i));
            sb.append(':');
            sb.append(headers.value(i));
            sb.append('\n');
        }

        RequestBody body = newRequest.body();
        if (body != null) {
            if (isJsonContent(body.contentType())) {
                BufferedRequestBody bufferedBody = new BufferedRequestBody(body);
                Request requestWithBufferedBody = newRequest.newBuilder()
                    .method(request.method(), bufferedBody)
                    .build();

                String bodyContent = bufferedBody.getContent();
                if (bodyContent.length() > MAX_LOG_LENGTH) {
                    sb.append(bodyContent.substring(0, MAX_LOG_LENGTH)).append(ELLIPSIS);
                } else {
                    sb.append(bodyContent);
                }

                return requestWithBufferedBody;
            } else {
                sb.append(String.format(Locale.ROOT, "[%s, %.2fKb]", 
                    body.contentType(), 
                    (float) body.contentLength() / 1024.0f));
            }
        }

        return newRequest;
    }

    private Response processResponse(Response response, long callTime, StringBuilder sb) throws IOException {
        sb.append("<-- ");
        sb.append(response.code());
        sb.append('-');
        sb.append(response.message());
        sb.append('(');
        sb.append(callTime);
        sb.append("ms)\n");

        ResponseBody body = response.body();
        if (body == null) {
            sb.append("[null]");
            return response;
        }

        if (!isJsonContent(body.contentType())) {
            sb.append(String.format(Locale.ROOT, "[%s, %.2fKb]", 
                body.contentType(), 
                (float) body.contentLength() / 1024.0f));
            return response;
        }

        BufferedResponseBody bufferedBody = new BufferedResponseBody(body);
        Response newResponse = response.newBuilder().body(bufferedBody).build();

        String bodyContent = bufferedBody.getContent();
        if (bodyContent.length() > MAX_LOG_LENGTH) {
            sb.append(bodyContent.substring(0, MAX_LOG_LENGTH)).append(ELLIPSIS);
        } else {
            sb.append(bodyContent);
        }

        return newResponse;
    }

    private boolean isJsonContent(MediaType mediaType) {
        return mediaType != null && 
               APPLICATION_TYPE.equals(mediaType.type()) && 
               JSON_SUBTYPE.equals(mediaType.subtype());
    }

    private static class BufferedRequestBody extends RequestBody {
        private final RequestBody originalBody;
        private final byte[] content;

        public BufferedRequestBody(RequestBody originalBody) throws IOException {
            this.originalBody = originalBody;
            Buffer buffer = new Buffer();
            originalBody.writeTo(buffer);
            this.content = buffer.readByteArray();
        }

        public String getContent() {
            return new String(content, StandardCharsets.UTF_8);
        }

        @Override
        public MediaType contentType() {
            return originalBody.contentType();
        }

        @Override
        public long contentLength() {
            return content.length;
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            sink.write(content);
        }
    }

    private static class BufferedResponseBody extends ResponseBody {
        private final ResponseBody originalBody;
        private final byte[] content;

        public BufferedResponseBody(ResponseBody originalBody) throws IOException {
            this.originalBody = originalBody;
            this.content = originalBody.bytes();
        }

        public String getContent() {
            return new String(content, StandardCharsets.UTF_8);
        }

        @Override
        public MediaType contentType() {
            return originalBody.contentType();
        }

        @Override
        public long contentLength() {
            return content.length;
        }

        @Override
        public BufferedSource source() {
            return new Buffer().write(content);
        }
    }

    // TODO: 原始代码中的其他方法（如m4522a, b等）需要根据HideRequestLog注解实现
    public static boolean m4522a(Request request) {
        return false;
    }

    public static boolean b(Request request) {
        return false;
    }
}