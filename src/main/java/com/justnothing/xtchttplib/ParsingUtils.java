package com.justnothing.xtchttplib;

import java.io.IOError;
import java.io.IOException;

import com.xtc.sync.cel;
import com.xtc.sync.cev;
import com.xtc.sync.cfu;

import android.text.TextUtils;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ParsingUtils {


    // 还有问题没改（key缓存），以后研究吧
    public static Response decryptResponseBody(Response response) {
        String str = ContextManager.getInstance().getAe();
        if (response.body() == null) {
            return response;
        }
        String string = null;   
        try {
            string = response.peekBody(1048576L).string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOError(e);
        }
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(str)) {
            String header = response.header(cel.a.s);
            String header2 = response.header(cel.a.g);
            if (header != null && header.contains(cel.a.s)) {
                string = cfu.b(str, string, cev.a().a(header2));
            }
        } else {
            // dkw.d(f22964b, "result or eebbkKey is null");
            System.err.println("result or eebbkKey is null");
            throw new RuntimeException("result or eebbkKey is null");
        }
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body(ResponseBody.create(MediaType.parse(cel.a.f), string));
        return newBuilder.build();
    }

    // 还有问题没改，以后研究吧
    // public static String decryptResponseBody(String body, String responseEebbkSign) throws RuntimeException, IOException {
    //     String str = ContextManager.getInstance().getAe();
    //     if (responseEebbkSign == null) {
    //         System.err.println("responseEebbkSign is null");
    //         throw new RuntimeException("responseEebbkSign is null");
    //     }
    //     if (body == null) {
    //         return null;
    //     }
    //     if (!TextUtils.isEmpty(body) && !TextUtils.isEmpty(str)) {
    //         body = cfu.b(str, body, cev.a().a(responseEebbkSign));
    //     } else {
    //         System.err.println("result or eebbkKey is null");
    //         throw new RuntimeException("result or eebbkKey is null");
    //     }
    //     return body;
    // }
}
