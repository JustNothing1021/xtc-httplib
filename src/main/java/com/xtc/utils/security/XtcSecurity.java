package com.xtc.utils.security;

import android.text.TextUtils;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.Jni;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.jni.ProxyClassFactory;
import com.github.unidbg.memory.Memory;

// DeekSeek写的

/* loaded from: classes3.dex */
public class XtcSecurity {

    private static final Logger logger = LoggerFactory.getLogger(XtcSecurity.class);

    /* renamed from: a, reason: collision with root package name */
    private static final String f32502a = "XtcSecurity";

    /* renamed from: b, reason: collision with root package name */
    private static final String f32503b = "KeyEncrypt";


    private static class XtcSecurityJni extends AbstractJni {

        private AndroidEmulator emulator;
        private Memory memory;
        private VM vm;
        private DvmClass dvmClass;
        private boolean initialized = false;

        private XtcSecurityJni() {
            initialize();
        }

        private synchronized void initialize() {
            if (initialized) {
                return;
            }
            
            try {
                emulator = AndroidEmulatorBuilder.for32Bit().build();
                memory = emulator.getMemory();
                memory.setLibraryResolver(new AndroidResolver(23));
                vm = emulator.createDalvikVM();
                vm.setJni(this);
                vm.setVerbose(true);
                File soFile = new File("lib/armeabi/libxtcSecurity.so");
                if (!soFile.exists()) {
                    throw new IOException("XtcSecurity库文件不存在，路径: " + soFile.getAbsolutePath());
                }
                logger.debug("正在加载XtcSecurity库文件: {}", soFile.getAbsolutePath());
                DalvikModule dm = vm.loadLibrary(soFile, false);
                dm.callJNI_OnLoad(emulator);
                dvmClass = vm.resolveClass("com/xtc/utils/security/XtcSecurity");
                logger.debug("XtcSecurity库加载成功");
                initialized = true;
            } catch (Exception e) {
                logger.error("XtcSecurity库加载失败", e);
                throw new RuntimeException("无法加载XtcSecurity库", e);
            }
        }

        public DvmObject<?> callLibrary(String signature, Object... args) {
            if (!initialized) {
                initialize();
            }
            try {
                DvmObject<?> result = dvmClass.callStaticJniMethodObject(emulator, signature, args);
                return result != null ? result : null;
            } catch (Exception e) {
                logger.error("调用XtcSecurity库方法\"{}\"失败", signature);
                logger.error("异常信息: ", e);
                return null;
            }
        }

        public void destroy() {
            if (emulator != null) {
                try {
                    emulator.close();
                    initialized = false;
                    emulator = null;
                    memory = null;
                    vm = null;
                    dvmClass = null;
                    logger.debug("资源释放完成");
                } catch (IOException e) {
                    logger.error("资源释放失败", e);
                }
            }
        }

        public String secretKeyDecrypt(String str) {
            if (!initialized) initialize();
            Object ret = jni.callLibrary("secretKeyDecrypt(Ljava/lang/String;)Ljava/lang/String;", str).getValue();
            return ret != null ? ret.toString() : null;
        }

        public String secretKeyEncrypt(String str) {
            if (!initialized) initialize();
            Object ret = jni.callLibrary("secretKeyEncrypt(Ljava/lang/String;)Ljava/lang/String;", str).getValue();
            return ret != null ? ret.toString() : null;
        }

        public String getDefaultRsaKey() {
            if (!initialized) initialize();
            Object ret = jni.callLibrary("getDefaultRsaKey()Ljava/lang/String;");
            return ret != null ? ret.toString() : null;
        }
    }

    private static final XtcSecurityJni jni = new XtcSecurityJni();

    public static String secretKeyDecrypt(String str) {
        try {
            return jni.secretKeyDecrypt(str);
        } catch (Exception e) {
            logger.error("secretKeyDecrypt出错", e);
            return str;
        }
    }

    public static String secretKeyEncrypt(String str) {
        try {
            return jni.secretKeyEncrypt(str);
        } catch (Exception e) {
            logger.error("secretKeyEncrypt出错", e);
            return str;
        }
    }

    public static String getDefaultRsaKey() {
        try {
            return jni.getDefaultRsaKey();
        } catch (Exception e) {
            logger.error("getDefaultRsaKey出错", e);
            return null;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith(f32503b)) {
            return str;
        }
        String secretKeyEncrypt = secretKeyEncrypt(str);
        if (Objects.equals(secretKeyEncrypt, str)) {
            return str;
        }
        return f32503b + secretKeyEncrypt;
    }

    public static String b(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith(f32503b)) ? secretKeyDecrypt(str.substring(10)) : str;
    }
    
    public static void destroyJni() {
        jni.destroy();
    }
}