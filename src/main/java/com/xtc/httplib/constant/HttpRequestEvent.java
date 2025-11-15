package com.xtc.httplib.constant;

import com.xtc.sync.cbb;
import com.xtc.sync.cfb;

/* loaded from: classes2.dex */
public class HttpRequestEvent {
    String id = "id";
    String pkgName = "pkg_name";
    String appVer = "app_ver";
    String url = "url";
    String optUrl = "opt_url";
    String host = "host";
    String reqDate = "req_date";
    String reqHour = "req_hour";
    String dnsResult = cbb.f22694b;
    String dnsProvider = String.valueOf(-1);
    String dnsCostTime = "0";
    String reqContentLength = "0";
    String respContentLength = "0";
    String interceptorLogTime = "0";
    String interceptorShutdownLctTime = "0";
    String interceptorTimeoutTime = "0";
    String interceptorMonitorTime = "0";
    String interceptorPreprocessorTime = "0";
    String interceptorReqTime = "0";
    String interceptorRespTime = "0";
    String callServerCostTime = "0";
    String totalCostTime = "0";
    String code = cfb.g;
    String errorReason = "Unknown";
    String netTag = "Unknown";

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getAppVer() {
        return this.appVer;
    }

    public void setAppVer(String str) {
        this.appVer = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getOptUrl() {
        return this.optUrl;
    }

    public void setOptUrl(String str) {
        this.optUrl = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getReqDate() {
        return this.reqDate;
    }

    public void setReqDate(String str) {
        this.reqDate = str;
    }

    public String getReqHour() {
        return this.reqHour;
    }

    public void setReqHour(String str) {
        this.reqHour = str;
    }

    public String getDnsProvider() {
        return this.dnsProvider;
    }

    public void setDnsProvider(String str) {
        this.dnsProvider = str;
    }

    public String getDnsCostTime() {
        return this.dnsCostTime;
    }

    public void setDnsCostTime(String str) {
        this.dnsCostTime = str;
    }

    public String getReqContentLength() {
        return this.reqContentLength;
    }

    public void setReqContentLength(String str) {
        this.reqContentLength = str;
    }

    public String getRespContentLength() {
        return this.respContentLength;
    }

    public void setRespContentLength(String str) {
        this.respContentLength = str;
    }

    public String getInterceptorLogTime() {
        return this.interceptorLogTime;
    }

    public void setInterceptorLogTime(String str) {
        this.interceptorLogTime = str;
    }

    public String getInterceptorShutdownLctTime() {
        return this.interceptorShutdownLctTime;
    }

    public void setInterceptorShutdownLctTime(String str) {
        this.interceptorShutdownLctTime = str;
    }

    public String getInterceptorTimeoutTime() {
        return this.interceptorTimeoutTime;
    }

    public void setInterceptorTimeoutTime(String str) {
        this.interceptorTimeoutTime = str;
    }

    public String getInterceptorMonitorTime() {
        return this.interceptorMonitorTime;
    }

    public void setInterceptorMonitorTime(String str) {
        this.interceptorMonitorTime = str;
    }

    public String getInterceptorPreprocessorTime() {
        return this.interceptorPreprocessorTime;
    }

    public void setInterceptorPreprocessorTime(String str) {
        this.interceptorPreprocessorTime = str;
    }

    public String getInterceptorReqTime() {
        return this.interceptorReqTime;
    }

    public void setInterceptorReqTime(String str) {
        this.interceptorReqTime = str;
    }

    public String getInterceptorRespTime() {
        return this.interceptorRespTime;
    }

    public void setInterceptorRespTime(String str) {
        this.interceptorRespTime = str;
    }

    public String getTotalCostTime() {
        return this.totalCostTime;
    }

    public void setTotalCostTime(String str) {
        this.totalCostTime = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getErrorReason() {
        return this.errorReason;
    }

    public void setErrorReason(String str) {
        this.errorReason = str;
    }

    public String getNetTag() {
        return this.netTag;
    }

    public void setNetTag(String str) {
        this.netTag = str;
    }

    public String getDnsResult() {
        return this.dnsResult;
    }

    public void setDnsResult(String str) {
        this.dnsResult = str;
    }

    public String getCallServerCostTime() {
        return this.callServerCostTime;
    }

    public void setCallServerCostTime(String str) {
        this.callServerCostTime = str;
    }

    public String toString() {
        return "HttpRequestEvent{id='" + this.id + "'\n, pkgName='" + this.pkgName + "'\n, appVer='" + this.appVer + "'\n, url='" + this.url + "'\n, opt_url='" + this.optUrl + "'\n, host='" + this.host + "'\n, req_date='" + this.reqDate + "'\n, req_hour='" + this.reqHour + "'\n, dns_result='" + this.dnsResult + "'\n, dns_provider='" + this.dnsProvider + "'\n, dns_cost_time='" + this.dnsCostTime + "'\n, req_content_length='" + this.reqContentLength + "'\n, resp_content_length='" + this.respContentLength + "'\n, interceptor_log_time='" + this.interceptorLogTime + "'\n, interceptor_shutdown_lct_time='" + this.interceptorShutdownLctTime + "'\n, interceptor_timeout_time='" + this.interceptorTimeoutTime + "'\n, interceptor_monitor_time='" + this.interceptorMonitorTime + "'\n, interceptor_preprocessor_time='" + this.interceptorPreprocessorTime + "'\n, interceptor_req_time='" + this.interceptorReqTime + "'\n, interceptor_resp_time='" + this.interceptorRespTime + "'\n, total_cost_time='" + this.totalCostTime + "'\n, call_server_cost_time='" + this.callServerCostTime + "'\n, code='" + this.code + "'\n, error_reason='" + this.errorReason + "'\n, net_tag='" + this.netTag + "'\n}";
    }
}