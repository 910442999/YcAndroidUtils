package com.yc.yclibrary;

import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import static com.yc.yclibrary.YcConstUtils.*;
import static com.yc.yclibrary.YcConstUtils.KB;
import static com.yc.yclibrary.YcConstUtils.MB;

/**
 * 字符串相关 YcStringUtils
 * isEmpty         : 判断字符串是否为 null 或长度为 0
 * isTrimEmpty     : 判断字符串是否为 null 或全为空格
 * equals          : 判断两字符串是否相等
 * equalsIgnoreCase: 判断两字符串忽略大小写是否相等
 * null2Length0    : null 转为长度为 0 的字符串
 * length          : 返回字符串长度
 * byte2FitSize     字节数转合适大小
 * bytes2HexString  byteArr转hexString
 * hexString2Bytes  hexString转byteArr
 * hex2Dec          hexChar转int
 * ExistOtherChar   判断字符串中是否出现非数字字符，如果出现非数字字符，返回true，否则返回false
 * decode           将十六进制字符串解码成字符串
 * hexStr2Str       将16进制字符串转化为10进制字符串
 * toStringHex      将16进制字符串转化为10进制字符串
 */

public class YcStringUtils {
    public static String hexString = "0123456789ABCDEF";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};


    private YcStringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 如果字符串为null或0长度，则返回true
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0 || "null".equals(str);
    }

    /**
     * Return whether the string is null or whitespace.
     *
     * @param s The string.
     * @return {@code true}: yes<br> {@code false}: no
     */
    public static boolean isTrimEmpty(@Nullable final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * Returns true if a and b are equal, including if they are both null.
     * <p><i>Note: In platform versions 1.1 and earlier, this method only worked well if
     * both the arguments were instances of String.</i></p>
     *
     * @param a first CharSequence to check
     * @param b second CharSequence to check
     * @return true if a and b are equal
     */
    public static boolean equals(@Nullable CharSequence a, @Nullable CharSequence b) {
        if (a == b)
            return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Return whether string1 is equals to string2, ignoring case considerations..
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean equalsIgnoreCase(@Nullable final String s1, @Nullable final String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    /**
     * Return {@code ""} if string equals null.
     *
     * @param s The string.
     * @return {@code ""} if string equals null
     */
    public static String null2Length0(@Nullable final String s) {
        return s == null ? "" : s;
    }

    /**
     * Return the length of string.
     *
     * @param s The string.
     * @return the length of string
     */
    public static int length(@Nullable final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 字节数转合适大小
     * <p>保留3位小数</p>
     *
     * @param byteNum 字节数
     * @return 1...1024 unit
     */
    public static String byte2FitSize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < KB) {
            return String.format(Locale.getDefault(), "%.3fB", (double) byteNum);
        } else if (byteNum < MB) {
            return String.format(Locale.getDefault(), "%.3fKB", (double) byteNum / KB);
        } else if (byteNum < GB) {
            return String.format(Locale.getDefault(), "%.3fMB", (double) byteNum / MB);
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", (double) byteNum / GB);
        }
    }

    /**
     * byteArr转hexString
     * <p>例如：</p>
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes byte数组
     * @return 16进制大写字符串
     */
    public static String bytes2HexString(byte[] bytes) {
        char[] ret = new char[bytes.length << 1];
        for (int i = 0, j = 0; i < bytes.length; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >>> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * hexString转byteArr
     * <p>例如：</p>
     * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String hexString) {
        int len = hexString.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >>> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    /**
     * hexChar转int
     *
     * @param hexChar hex单个字节
     * @return 0..15
     */
    private static int hex2Dec(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }
    /*判断字符串中是否出现非数字字符，如果出现非数字字符，返回true，否则返回false*/

    public static boolean ExistOtherChar(String str) {
        String numstr = "0123456789";
        int i = 0;
        for (i = 0; i < str.length(); i++) {
            if (numstr.indexOf(str.charAt(i)) == -1) {
                return true;
            }
        }
        return false;
    }

    /*将十六进制字符串解码成字符串*/


    public static String decode(String bytes)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }
    /*将字符串转化为16进制，存入字符串数组中*/


    public static String string2Hex(String content) {
        byte[] bytes = content.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转化为10进制字符串
     **/


    public static String hexStr2Str(String hexStr) {
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = hexString.indexOf(hexs[2 * i]) * 16;
            n += hexString.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /*将16进制字符串转化为10进制字符串*/


    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");//UTF-16 le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}
