package com.xtc.sync;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;

// DeepSeek写的，虽然没啥用（直接用Okio就行）

/* compiled from: Okio.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a\n\u0010\b\u001a\u00020\u0006*\u00020\t\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0006\u001a\n\u0010\n\u001a\u00020\f*\u00020\r\u001a\u0016\u0010\u000e\u001a\u00020\u0006*\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0001H\u0007\u001a\n\u0010\u000e\u001a\u00020\u0006*\u00020\u0010\u001a\n\u0010\u000e\u001a\u00020\u0006*\u00020\u0011\u001a%\u0010\u000e\u001a\u00020\u0006*\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\t\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\u0018\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\u0011\u001a%\u0010\u0017\u001a\u00020\r*\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0019\"\u001c\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0004¨\u0006\u001a"}, d2 = {"isAndroidGetsocknameError", "", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "blackholeSink", "Lokio/Sink;", "blackhole", "appendingSink", "Ljava/io/File;", "buffer", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "sink", "append", "Ljava/io/OutputStream;", "Ljava/net/Socket;", "Ljava/nio/file/Path;", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "source", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "jvm"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class hpj {
    
    public static okio.Source a(okio.Source source) {
        return Okio.buffer(source);
    }
    
    public static okio.Sink a(okio.Sink sink) {
        return Okio.buffer(sink);
    }
    
    public static okio.Sink a(OutputStream outputStream) {
        return Okio.sink(outputStream);
    }
    
    public static okio.Source a(InputStream inputStream) {
        return Okio.source(inputStream);
    }
    
    public static okio.Sink a() {
        return Okio.blackhole();
    }
    
    public static okio.Sink a(Socket socket) throws IOException {
        return Okio.sink(socket);
    }
    
    public static okio.Source b(Socket socket) throws IOException {
        return Okio.source(socket);
    }
    
    public static okio.Sink a(File file) throws FileNotFoundException {
        return Okio.sink(file);
    }
    
    public static okio.Sink a(File file, boolean append) throws FileNotFoundException {
        return append ? Okio.appendingSink(file) : Okio.sink(file);
    }
    
    public static okio.Source b(File file) throws FileNotFoundException {
        return Okio.source(file);
    }
    
    public static okio.Sink a(Path path, OpenOption... openOptionArr) throws IOException {
        return Okio.sink(path, openOptionArr);
    }
    
    // public static okio.Source a(Path path, OpenOption... openOptionArr) throws IOException {
    //     return Okio.source(path, openOptionArr);
    // }
    
    public static boolean a(AssertionError assertionError) {
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        return message != null && message.contains("getsockname failed");
    }
    
    // 保持原有方法签名的兼容性方法
    public static okio.BufferedSource a(hpe gzipSource) {
        return Okio.buffer(gzipSource);
    }
}