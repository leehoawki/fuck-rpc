package com.shihang.fuck.rpc.utils;

public abstract class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }

    public static String[] split(String toSplit, String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return new String[]{toSplit};
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return new String[]{toSplit};
        }

        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[]{beforeDelimiter, afterDelimiter};
    }

    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

    public static int count(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ch) {
                count += 1;
            }
        }
        return count;
    }
}
