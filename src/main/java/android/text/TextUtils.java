package android.text;

public class TextUtils {
    // 占位符。
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
