package com.justnothing.xtchttplib;


import com.xtc.sync.cft;
import com.xtc.utils.security.XtcSecurity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ContextManager ctx = new ContextManager();
        // 这里得自己设置（我的就不给你们看了）
        ctx.setTs(1);
        ctx.setAe("");
        ctx.setRsaPublicKey("");
        ctx.setSelfRsaPublicKey("");
        ctx.setHttpHeadParam("");
        ctx.setEncSwitch("");
        ctx.setWatchId("");
        ctx.setMacAddr("");
        ctx.setBindNumber("");
        ctx.setPackageName("");
        ctx.setPackageVersionName("");
        ctx.setPackageVersionCode(114514);
        ctx.setAndroidSdk(27);
        ctx.setInnerModel("");
        ctx.setInnerModelEx("");
        ctx.setServerInner("");
        ctx.setLocale("");
        ctx.setRegion("");
        ctx.setWatchModel("");
        ctx.setWatchPriModel("");
        ctx.setBuildType("");
        ctx.setHardware("");
        ctx.setCaremeOsVersion("");
        ctx.setShowModel("");
        ctx.setTimeZone("");
        ctx.setDataCenterCode("");
        ctx.setChipId("");
        ctx.setBuildRelease("");
        ctx.setSoftVersion("");

        ContextManager.setInstance(ctx);
        cft httpClient = new cft(null);
        OkHttpClient okHttpClient = httpClient.m3072a();
        String url = "https://api.watch.okii.com/social-service/weichat/slidePage/getShowList"; // 瞎写的
        String data = "";
        Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(
                okhttp3.MediaType.parse("application/json"), data))
            .build();
        System.out.println("发送请求到: " + request.url());

        try (Response response = okHttpClient.newCall(request).execute()) {
            System.out.println("响应状态: " + response.code() + " " + response.message());
            
            if (response.body() != null) {
                String responseBody = response.body().string();
                System.out.println("响应内容: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        XtcSecurity.destroyJni();
    }
}
