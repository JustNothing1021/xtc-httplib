package com.xtc.sync;

// import android.util.Base64;
import java.util.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtil.java */
/* loaded from: classes2.dex */
public class ekn {

    /* renamed from: a, reason: collision with root package name */
    public static final String f26051a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfQz49r6aXpY49YPr3a9n1PHUy/bYzufv/Ag3Cdv1GLtugWy3qYBE6uj2CrRuBGgaWlmTtWpw9EmQtnen4xsKjxw8eqqNqud+pAUEG4k2YLEBe79MOuslld6R6vT+a9kvpY60rGO7/Pm+x8fVyCZvIQouzbf+T2b/GDTU0XNJR0wIDAQAB";

    /* renamed from: b, reason: collision with root package name */
    private static final String f26052b = "RSA/ECB/PKCS1Padding";
    private static final String c = "UTF-8";

    /* renamed from: a, reason: collision with other method in class */
    private static PublicKey m4862a(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(ekh.a(str)));
    }

    private static PrivateKey a(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(ekh.a(str)));
    }

    public static void a(String[] strArr) throws Exception {
        b("heal", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOD5Y19YslL7R9Srz33ra/2+RVGinKz6r1bGS8Rcr7a2/zPLvkGpscMdrpFiXFIUXLTnFLPjGNNpvkqfJKKCuHMCAwEAAQ==");
        c("X521AF2AvjRd4FCBPBKSzF+zWpgknWjKygyQFgHgY2JSpc21rTJh3uc39kV4BXvivc3X6QNGnjXPnIgH1f6epg==", "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAovOBRlkhAd7M3RBJzVlvwvxyxhMjksJmCNwdWGQF4n2RgLEKzc3b9wuOs9Q3BOEFjimDiXnd9N2iP9uYP7a/VQIDAQABAkB64BoMfSs5qNNco2qzkYyIQSsfF9GMWlDsv2bVf188oPU5UQBkDPT239NLe6wiqFCB3X5+jqqxcBlWkVDeOSYlAiEA0+WvYfhXw/FiyCNxxJ6hblhRfQ/CrRKMz89BlC3t+FsCIQDE3eBeL90/OLS8X3qAIA3wChON4VmGzVznRWk0ssY2DwIhAITGxfET1pr3ZLiYTS+xXuJwAQ/mkkw09Xs6GZOqfBVFAiAmhmj25ZT9X0J3LpQRaLRxifdDp5rWd2+7zmiFKIsDXwIhAI+t4kFXZIGr0HE8DmkKjhTmMiVNsPOYswNumeh33XT5");
    }

    public static String a(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance(f26052b);
            cipher.init(1, a(str2));
            return ekh.a(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance(f26052b);
            cipher.init(1, m4862a(str2));
            return ekh.a(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String c(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance(f26052b);
            cipher.init(2, a(str2));
            return new String(cipher.doFinal(ekh.a(str)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String d(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance(f26052b);
            cipher.init(2, m4862a(str2));
            return new String(cipher.doFinal(ekh.a(str)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(str3)));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(generatePublic);
            signature.update(str.getBytes());
            return signature.verify(Base64.getDecoder().decode(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}