package com.xtc.httplib.constant;

public class HttpConstants {
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_REQUEST_ID = "X-Request-ID";
    
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    
    public static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    public static final int DEFAULT_READ_TIMEOUT = 30000;
    public static final int DEFAULT_WRITE_TIMEOUT = 30000;
}