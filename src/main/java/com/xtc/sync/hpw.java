package com.xtc.sync;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Timeout.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0000H\u0016J\b\u0010\t\u001a\u00020\u0000H\u0016J\u0016\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u001f\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012H\u0086\bJ\b\u0010\u0013\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokio/Timeout;", "", "()V", "deadlineNanoTime", "", "hasDeadline", "", "timeoutNanos", "clearDeadline", "clearTimeout", "deadline", "duration", "unit", "Ljava/util/concurrent/TimeUnit;", "intersectWith", "", "other", "block", "Lkotlin/Function0;", "throwIfReached", "timeout", "waitUntilNotified", "monitor", "Companion", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public class hpw {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final hpw NONE = new b();
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public hpw timeout(long j, TimeUnit timeUnit) {
        fpf.f(timeUnit, "unit");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("timeout < 0: " + j).toString());
        }
        this.timeoutNanos = timeUnit.toNanos(j);
        return this;
    }

    /* renamed from: timeoutNanos, reason: from getter */
    public long getTimeoutNanos() {
        return this.timeoutNanos;
    }

    /* renamed from: hasDeadline, reason: from getter */
    public boolean getHasDeadline() {
        return this.hasDeadline;
    }

    public long deadlineNanoTime() {
        if (!this.hasDeadline) {
            throw new IllegalStateException("No deadline".toString());
        }
        return this.deadlineNanoTime;
    }

    public hpw deadlineNanoTime(long j) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j;
        return this;
    }

    public final hpw deadline(long j, TimeUnit timeUnit) {
        fpf.f(timeUnit, "unit");
        if (!(j > 0)) {
            throw new IllegalArgumentException(("duration <= 0: " + j).toString());
        }
        return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j));
    }

    public hpw clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public hpw clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final void waitUntilNotified(Object monitor) throws InterruptedIOException {
        fpf.f(monitor, "monitor");
        try {
            boolean hasDeadline = getHasDeadline();
            long timeoutNanos = getTimeoutNanos();
            long j = 0;
            if (!hasDeadline && timeoutNanos == 0) {
                monitor.wait();
                return;
            }
            long nanoTime = System.nanoTime();
            if (hasDeadline && timeoutNanos != 0) {
                timeoutNanos = Math.min(timeoutNanos, deadlineNanoTime() - nanoTime);
            } else if (hasDeadline) {
                timeoutNanos = deadlineNanoTime() - nanoTime;
            }
            if (timeoutNanos > 0) {
                long j2 = timeoutNanos / 1000000;
                Long.signum(j2);
                monitor.wait(j2, (int) (timeoutNanos - (1000000 * j2)));
                j = System.nanoTime() - nanoTime;
            }
            if (j >= timeoutNanos) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public final void intersectWith(hpw hpwVar, Function0<fhb> function0) {
        fpf.f(hpwVar, "other");
        fpf.f(function0, "block");
        long timeoutNanos = getTimeoutNanos();
        timeout(INSTANCE.a(hpwVar.getTimeoutNanos(), getTimeoutNanos()), TimeUnit.NANOSECONDS);
        if (getHasDeadline()) {
            long deadlineNanoTime = deadlineNanoTime();
            if (hpwVar.getHasDeadline()) {
                deadlineNanoTime(Math.min(deadlineNanoTime(), hpwVar.deadlineNanoTime()));
            }
            try {
                function0.invoke();
                return;
            } finally {
                fpc.b(1);
                timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (hpwVar.getHasDeadline()) {
                    deadlineNanoTime(deadlineNanoTime);
                }
                fpc.c(1);
            }
        }
        if (hpwVar.getHasDeadline()) {
            deadlineNanoTime(hpwVar.deadlineNanoTime());
        }
        try {
            function0.invoke();
        } finally {
            fpc.b(1);
            timeout(timeoutNanos, TimeUnit.NANOSECONDS);
            if (hpwVar.getHasDeadline()) {
                clearDeadline();
            }
            fpc.c(1);
        }
    }

    /* compiled from: Timeout.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokio/Timeout$Companion;", "", "()V", "NONE", "Lokio/Timeout;", "minTimeout", "", "aNanos", "bNanos", "jvm"}, k = 1, mv = {1, 1, 11})
    /* renamed from: com.xtc.i3launcher.hpw$a, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public final long a(long j, long j2) {
            return (j != 0 && (j2 == 0 || j < j2)) ? j : j2;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(fou fouVar) {
            this();
        }
    }

    /* compiled from: Timeout.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"okio/Timeout$Companion$NONE$1", "Lokio/Timeout;", "deadlineNanoTime", "", "throwIfReached", "", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "jvm"}, k = 1, mv = {1, 1, 11})
    /* loaded from: classes2.dex */
    public static final class b extends hpw {
        @Override // com.xtc.sync.hpw
        public void throwIfReached() {
        }

        b() {
        }

        @Override // com.xtc.sync.hpw
        public hpw timeout(long j, TimeUnit timeUnit) {
            fpf.f(timeUnit, "unit");
            return this;
        }

        @Override // com.xtc.sync.hpw
        public hpw deadlineNanoTime(long j) {
            return this;
        }
    }
}