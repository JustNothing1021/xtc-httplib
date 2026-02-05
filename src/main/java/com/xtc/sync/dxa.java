package com.xtc.sync;

import android.content.Context;
// import android.database.Cursor;
import android.text.TextUtils;

import com.justnothing.xtchttplib.ContextManager;
import com.xtc.system.account.bean.HttpConfig;
// import com.xtc.system.account.bean.ImAccountInfo;
// import com.xtc.system.account.bean.WatchInfo;
// import com.xtc.utils.encode.JSONUtil;
// import com.xtc.utils.storage.ShareDBHelper;
// import com.xtc.utils.storage.ShareUserIdUtil;

/* compiled from: InitServiceData.java */
/* loaded from: classes2.dex */
public class dxa {

    /* renamed from: a, reason: collision with root package name */
    public static final String TAG = "InitServiceData";

    /* renamed from: b, reason: collision with root package name */
    private static final String f25384b = "com.xtc.i3launcher";
    private static final String c = "i3launcher.db";


    /* compiled from: InitServiceData.java */
    /* loaded from: classes2.dex */
    // public interface a {
    //     void a();

    //     void a(HttpConfig httpConfig);
    // }

    // /* compiled from: InitServiceData.java */
    // /* loaded from: classes2.dex */
    // public interface b {
    //     void a();

    //     void a(ImAccountInfo imAccountInfo);
    // }

    /* compiled from: InitServiceData.java */
    /* loaded from: classes2.dex */
    // public interface c {
    //     void a();

    //     void a(String str);
    // }

    /* compiled from: InitServiceData.java */
    /* loaded from: classes2.dex */
    // public interface d {
    //     void a();

    //     void a(WatchInfo watchInfo);
    // }

    // /* renamed from: a, reason: collision with other method in class */
    // public static String m4433a(Context context) {
    //     return dxd.m4439a(context);
    // }

    // public static void a(Context context, c cVar) {
    //     if (cVar == null) {
    //         throw new IllegalArgumentException("listener is null");
    //     }
    //     String m4439a = dxd.m4439a(context);
    //     if (TextUtils.isEmpty(m4439a)) {
    //         dkw.e(f25383a, "watchId is null");
    //         cVar.a();
    //     } else {
    //         cVar.a(m4439a);
    //     }
    // }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a4, code lost:
    
        if (r11 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0099, code lost:
    
        if (r11 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x00a9, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00a6, code lost:
    
        r11.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public HttpConfig a(Context context, ContextManager contextManager) {


        String grey = contextManager.getGrey();
        String rsaPublicKey = contextManager.getRsaPublicKey();
        String encSwitch = contextManager.getEncSwitch();
        String selfRsaPublicKey = contextManager.getSelfRsaPublicKey();
        String httpHeadParam = contextManager.getHttpHeadParam();
        int ts = contextManager.getTs();
        String ae = contextManager.getAe();

        dkw.c(TAG, "grey = " + grey + 
            ", rsaPublicKey = " + rsaPublicKey + 
            ", encSwitch = " + encSwitch + 
            ", selfRsaPublicKey = " + selfRsaPublicKey + 
            ", httpHeadParam = " + httpHeadParam + 
            ", ts = " + ts + 
            ", ae null = " + TextUtils.isEmpty(ae));

        return new HttpConfig(grey, encSwitch, rsaPublicKey, selfRsaPublicKey, 
                            httpHeadParam, ts, ae);
    }


    /* renamed from: a, reason: collision with other method in class */
    // private static String m4434a(Cursor cursor, String str) {
    //     if (cursor == null || TextUtils.isEmpty(str)) {
    //         return null;
    //     }
    //     try {
    //         int columnIndex = cursor.getColumnIndex(str);
    //         if (columnIndex < 0) {
    //             return null;
    //         }
    //         return cursor.getString(columnIndex);
    //     } catch (Exception e) {
    //         dkw.e(f25383a, "getCursorStrValue error: ", e);
    //         return null;
    //     }
    // }

    // private static int a(Cursor cursor, String str) {
    //     if (cursor == null || TextUtils.isEmpty(str)) {
    //         return 0;
    //     }
    //     try {
    //         int columnIndex = cursor.getColumnIndex(str);
    //         if (columnIndex < 0) {
    //             return 0;
    //         }
    //         return cursor.getInt(columnIndex);
    //     } catch (Exception e) {
    //         dkw.e(f25383a, "getCursorIntValue error: ", e);
    //         return 0;
    //     }
    // }

    // public static void a(Context context, a aVar) {
    //     if (aVar == null) {
    //         throw new IllegalArgumentException("listener is null");
    //     }
    //     HttpConfig a2 = a(context);
    //     if (a2 != null) {
    //         aVar.a(a2);
    //     } else {
    //         aVar.a();
    //     }
    // }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00eb, code lost:
    
        if (r15 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x00e0, code lost:
    
        if (r15 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x00f0, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00ed, code lost:
    
        r15.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f4  */
    /* JADX WARN: Type inference failed for: r15v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v3, types: [android.database.Cursor] */
    /* renamed from: a, reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    // public static com.xtc.system.account.bean.WatchInfo m4432a(android.content.Context r15) {
        /*
            java.lang.String r0 = "InitServiceData"
            java.lang.String r1 = "name"
            java.lang.String r2 = "number"
            java.lang.String r3 = "shortNumber"
            java.lang.String r4 = "gender"
            java.lang.String r5 = "grade"
            java.lang.String r6 = "birthday"
            java.lang.String r7 = "model"
            java.lang.String r8 = "innerModel"
            java.lang.String[] r11 = new java.lang.String[]{r1, r2, r3, r4, r5, r6, r7, r8}
            r1 = 0
            android.content.ContentResolver r9 = r15.getContentResolver()     // Catch: java.lang.Throwable -> Le3 java.lang.Exception -> Le6
            android.net.Uri r10 = com.xtc.sync.dxd.f8047a     // Catch: java.lang.Throwable -> Le3 java.lang.Exception -> Le6
            r12 = 0
            r13 = 0
            r14 = 0
            android.database.Cursor r15 = r9.query(r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> Le3 java.lang.Exception -> Le6
            if (r15 == 0) goto Le0
            boolean r2 = r15.moveToNext()     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            if (r2 == 0) goto Le0
            java.lang.String r2 = "name"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r4 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "number"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r5 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "shortNumber"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r6 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "gender"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r7 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "grade"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r8 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "birthday"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            long r2 = r15.getLong(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.Long r9 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = "model"
            int r2 = r15.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r2 = r15.getString(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r3 = "innerModel"
            int r3 = r15.getColumnIndex(r3)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r10 = r15.getString(r3)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.<init>()     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = "number = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r5)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", shortNumber = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r6)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", name = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r4)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", gender = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r7)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", grade = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r8)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", birthday = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r9)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", model = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r11 = ", innerModel = "
            r3.append(r11)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3.append(r10)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            com.xtc.sync.dkw.c(r0, r3)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            com.xtc.system.account.bean.WatchInfo r11 = new com.xtc.system.account.bean.WatchInfo     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r11.setModel(r2)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            r11.setInnerModel(r10)     // Catch: java.lang.Exception -> Lde java.lang.Throwable -> Lf1
            if (r15 == 0) goto Ldd
            r15.close()
        Ldd:
            return r11
        Lde:
            r2 = move-exception
            goto Le8
        Le0:
            if (r15 == 0) goto Lf0
            goto Led
        Le3:
            r0 = move-exception
            r15 = r1
            goto Lf2
        Le6:
            r2 = move-exception
            r15 = r1
        Le8:
            com.xtc.sync.dkw.b(r0, r2)     // Catch: java.lang.Throwable -> Lf1
            if (r15 == 0) goto Lf0
        Led:
            r15.close()
        Lf0:
            return r1
        Lf1:
            r0 = move-exception
        Lf2:
            if (r15 == 0) goto Lf7
            r15.close()
        Lf7:
            throw r0
        */
    //     throw new UnsupportedOperationException("Method not decompiled: com.xtc.sync.dxa.m4432a(android.content.Context):com.xtc.system.account.bean.WatchInfo");
    // }

    // public static void a(Context context, d dVar) {
    //     if (dVar == null) {
    //         throw new IllegalArgumentException("listener is null");
    //     }
    //     Cursor cursor = null;
    //     try {
    //         try {
    //             cursor = context.getContentResolver().query(dxd.f8047a, new String[]{"name", dxd.f8049b, dxd.f8051d, "gender", "grade", "birthday", "model", "innerModel"}, null, null, null);
    //             if (cursor != null && cursor.moveToNext()) {
    //                 String string = cursor.getString(cursor.getColumnIndex("name"));
    //                 String string2 = cursor.getString(cursor.getColumnIndex(dxd.f8049b));
    //                 String string3 = cursor.getString(cursor.getColumnIndex(dxd.f8051d));
    //                 String string4 = cursor.getString(cursor.getColumnIndex("gender"));
    //                 String string5 = cursor.getString(cursor.getColumnIndex("grade"));
    //                 Long valueOf = Long.valueOf(cursor.getLong(cursor.getColumnIndex("birthday")));
    //                 String string6 = cursor.getString(cursor.getColumnIndex("model"));
    //                 String string7 = cursor.getString(cursor.getColumnIndex("innerModel"));
    //                 dkw.c(f25383a, "number = " + string2 + ", shortNumber = " + string3 + ", name = " + string + ", gender = " + string4 + ", grade = " + string5 + ", birthday = " + valueOf + ", model = " + string6 + ", innerModel = " + string7);
    //                 WatchInfo watchInfo = new WatchInfo(string, string2, string3, string4, string5, valueOf);
    //                 watchInfo.setModel(string6);
    //                 watchInfo.setInnerModel(string7);
    //                 dVar.a(watchInfo);
    //             } else {
    //                 dVar.a();
    //             }
    //             if (cursor == null) {
    //                 return;
    //             }
    //         } catch (Exception e) {
    //             dkw.b(f25383a, e);
    //             dVar.a();
    //             if (0 == 0) {
    //                 return;
    //             }
    //         }
    //         cursor.close();
    //     } catch (Throwable th) {
    //         if (0 != 0) {
    //             cursor.close();
    //         }
    //         throw th;
    //     }
    // }

    // public static void a(Context context, final b bVar) {
    //     if (bVar == null) {
    //         throw new IllegalArgumentException("listener is null");
    //     }
    //     Context shareContext = ShareUserIdUtil.getShareContext(context, "com.xtc.i3launcher");
    //     if (shareContext == null) {
    //         bVar.a();
    //         return;
    //     }
    //     ShareDBHelper shareDBHelper = null;
    //     try {
    //         shareDBHelper = ShareUserIdUtil.getShareDBHelper(shareContext, "i3launcher.db");
    //         shareDBHelper.getCursorData("select * from watch_account", new ShareDBHelper.ICursorCallBack() { // from class: com.xtc.i3launcher.dxa.1
    //             @Override // com.xtc.utils.storage.ShareDBHelper.ICursorCallBack
    //             public void callBack(Cursor cursor) {
    //                 if (cursor != null && cursor.moveToFirst()) {
    //                     b.this.a((ImAccountInfo) JSONUtil.fromJSON(cursor.getString(cursor.getColumnIndex(axe.i)), ImAccountInfo.class));
    //                 } else {
    //                     b.this.a();
    //                 }
    //                 if (cursor != null) {
    //                     cursor.close();
    //                 }
    //             }
    //         });
    //     } catch (Exception e) {
    //         dkw.b(f25383a, e);
    //         bVar.a();
    //         if (shareDBHelper != null) {
    //             shareDBHelper.closeDb();
    //         }
    //     }
    // }
}
