package com.xtc.sync;

import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Source;
import okio.Timeout;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;

/* compiled from: GzipSource.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "offset", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class hpe implements okio.Source {

    private byte section;
    private final GzipSource gzipSource;
    private final okio.Source source;

    public hpe(okio.Source source) {
        this.source = source;
        this.gzipSource = new GzipSource(source);
    }

    @Override // okio.Source
    public long read(okio.Buffer sink, long byteCount) throws IOException {
        return gzipSource.read(sink, byteCount);
    }

    @Override // okio.Source
    public Timeout timeout() {
        return gzipSource.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        gzipSource.close();
    }

    // 保持原有方法名，但使用 Okio 的实现
    private final void checkEqual(String name, int expected, int actual) throws IOException {
        if (actual != expected) {
            String format = String.format("%s: actual 0x%08x != expected 0x%08x", 
                name, actual, expected);
            throw new IOException(format);
        }
    }

    // 空实现，因为 Okio 的 GzipSource 已经处理了这些
    private final void consumeHeader() throws IOException {
        // Okio 的 GzipSource 会自动处理头部
    }

    private final void consumeTrailer() throws IOException {
        // Okio 的 GzipSource 会自动处理尾部
    }

    private final void updateCrc(okio.Buffer buffer, long offset, long byteCount) {
        // Okio 的 GzipSource 会自动处理 CRC
    }
}