package com.yc.yclibrary;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import static com.yc.yclibrary.YcConstUtils.BYTE;
import static com.yc.yclibrary.YcConstUtils.GB;
import static com.yc.yclibrary.YcConstUtils.KB;
import static com.yc.yclibrary.YcConstUtils.MB;

//数据处理相关

/**
 * 数据的判断
 * isNullString                : 判断字符串是否为空 为空即true
 * isEmpty                     : 判断对象是否为空 为空即true
 * isInteger                   : 判断字符串是否是整数
 * isDouble                    : 判断字符串是否是浮点数
 * isNumber                    : 判断字符串是否是数字
 * getAstro                    : 根据日期判断星座
 * <p>
 * 数据的转换
 * stringToInt                 : 字符串转换成整数 ,转换失败将会 return 0;
 * stringToLong                : 字符串转换成long ,转换失败将会 return 0;
 * stringToDouble              : 字符串转换成double ,转换失败将会 return 0;
 * StringToInputStream         : 字符串转InputStream
 * stringTwoLines               将字符串从中间对折显示两行
 * hideCenterString             隐藏字符串中间的缺省
 * upperFirstLetter            : 首字母大写
 * lowerFirstLetter            : 首字母小写
 * reverse                     : 反转字符串
 * toDBC                       : 转化为半角字符
 * toSBC                       : 转化为全角字符
 * oneCn2ASCII                 : 单个汉字转成ASCII码
 * oneCn2PY                    : 单个汉字转成拼音
 * getPYFirstLetter            : 获得第一个汉字首字母
 * cn2PY                       : 中文转拼音
 * bytes2HexString             : byteArr转hexString
 * hexString2Bytes             : hexString转byteArr
 * hex2Dec                     : hexChar转int
 * chars2Bytes                 : charArr转byteArr
 * bytes2Chars                 : byteArr转charArr
 * byte2Size                   : 字节数转以unit为单位的size
 * size2Byte                   : 以unit为单位的size转字节数
 * byte2FitSize                : 字节数转合适大小
 * input2OutputStream          : inputStream转outputStream
 * output2InputStream          : outputStream转inputStream
 * inputStream2Bytes           : inputStream转byteArr
 * bytes2InputStream           : byteArr转inputStream
 * outputStream2Bytes          : outputStream转byteArr
 * bytes2OutputStream          : outputStream转byteArr
 * inputStream2String          : inputStream转string按编码
 * string2InputStream          : string转inputStream按编码
 * outputStream2String         : outputStream转string按编码
 * string2OutputStream         : string转outputStream按编码
 * format2Decimals                将字符串格式化为带两位小数的字符串 四舍五入
 * formatDecimalsRounding       保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值 四舍五入
 * formatDecimals                将字符串格式化为 (自定义位数 ) 小数的字符串  不四舍五入
 * formatDecimals                将字符串格式化为 (自定义位数 ) 小数的字符串 自定义是否四舍五入
 * formatDecimalsNoRounding       保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值  不四舍五入
 * <p>
 * formatNoRoundingDecimals     将字符串格式化为 (自定义位数 ) 小数的字符串 不四舍五入 不够位数原值返回  够则取舍
 * <p>
 * formatDecimals (三参)         将字符串格式化为 (自定义位数 ) 小数的字符串  自定义舍入模式
 * 如果数值为 0.0000000000001   自定义的舍入小数为#0.0000  四位小数  则 直接返回0.0000
 * 如果数值为0.0001   自定义舍入小数为#0.0000000  七位小数位   则返回 0.0001000
 * <p>
 * getAmountValue                金额格式化
 * getRoundUp                    格式化数值 四舍五入
 * getRoundDown                  格式化数值 不四舍五入
 * getRoundUpDown                自定义四舍五入
 * getPercentValue               获取百分比（乘100）
 * baseToSubunit                 将字符串 乘 10 的几次方 (如 10的18次方)
 * subunitToBase                 将大整形 除以 10 的 几次方
 */
public class YcDataConversionUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 金额 格式化
     */
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("###,###,###,##0.00");

    /**
     * 判断字符串是否为空 为空即true
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNullString(@Nullable String str) {
        return str == null || str.length() == 0 || "null".equals(str);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是双精度浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return value.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 判断字符串有几位小数 Judging the string has a few decimal places
     */
    public static int judgingStringHasFewDecimal(String str) {
        int index = str.lastIndexOf(".");//寻找小数点的索引位置，若不是小数，则为-1
        if (index > -1) {
            index = str.substring(index + 1).length();//取得小数点后的数值，不包括小数点
        }
        return index;
    }


    /**
     * 根据日期判断星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getAstro(int month, int day) {
        String[] starArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        int[] DayArr = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};  // 两个星座分割日

        if (month <= 0 || day <= 0) {
            return "猴年马月座";
        } else if (month > 12 || day > 31) {
            return "猴年马月座";
        }

        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < DayArr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return starArr[index];
    }

    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone4(String mobile_phone) {
        if (mobile_phone.length() != 11) {
            return "手机号码不正确";
        }
        return mobile_phone.substring(0, 3) + "****" + mobile_phone.substring(7, 11);
    }

    /**
     * 格式化银行卡 加*
     * 3749 **** **** 330
     *
     * @param cardNo 银行卡
     * @return 3749 **** **** 330
     */
    public static String formatCard(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 银行卡后四位
     *
     * @param cardNo
     * @return
     */
    public static String formatCardEnd4(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 字符串转换成整数 ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    public static int stringToInt(String str) {
        if (isNullString(str)) {
            return 0;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 字符串转换成整型数组
     *
     * @param s
     * @return
     */
    public static int[] stringToInts(String s) {
        int[] n = new int[s.length()];
        if (isNullString(s)) {

        } else {
            for (int i = 0; i < s.length(); i++) {
                n[i] = Integer.parseInt(s.substring(i, i + 1));
            }
        }
        return n;
    }

    /**
     * 整型数组求和
     *
     * @param ints
     * @return
     */
    public static int intsGetSum(int[] ints) {
        int sum = 0;

        for (int i = 0, len = ints.length; i < len; i++) {
            sum += ints[i];
        }

        return sum;
    }

    /**
     * 字符串转换成long ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    public static long stringToLong(String str) {
        if (isNullString(str)) {
            return 0;
        } else {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 字符串转换成double ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    public static double stringToDouble(String str) {
        if (isNullString(str)) {
            return 0;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 字符串转换成浮点型 Float
     *
     * @param str 待转换的字符串
     * @return 转换后的 float
     */
    public static float stringToFloat(String str) {
        if (isNullString(str)) {
            return 0;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 将字符串格式化为带两位小数的字符串 四舍五入
     *
     * @param str 字符串
     * @return
     */
    public static String format2Decimals(String str) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (df.format(stringToDouble(str)).startsWith(".")) {
            return "0" + df.format(stringToDouble(str));
        } else {
            return df.format(stringToDouble(str));
        }
    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串 四舍五入
     *
     * @param str 字符串 "#0.00"
     * @return
     */
    public static String formatDecimals(Double str, String digits) {
        return formatDecimals(str, digits, RoundingMode.HALF_UP);
    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串 不四舍五入
     *
     * @param digits 字符串 "#0.00"
     * @return
     */
    public static String formatDecimals(String str, String digits) {
        return formatDecimals(stringToDouble(str), digits, RoundingMode.FLOOR);
    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串 不四舍五入 不够位数原值返回  够则取舍
     *
     * @param digits 小数位
     * @return
     */
    public static String formatNoRoundingDecimals(String str, int digits) {
        int i = judgingStringHasFewDecimal(str);
        if (digits > i) {
            return str;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#0.");
            for (int j = 0; j < digits; j++) {
                stringBuilder.append("0");
            }
            return formatDecimals(stringToDouble(str), stringBuilder.toString(), RoundingMode.FLOOR);
        }

    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串  自定义舍入模式
     * 如果数值为 0.0000000000001   自定义的舍入小数为#0.0000  四位小数  则 直接返回0.0000
     * 如果数值为0.0001   自定义舍入小数为#0.0000000  七位小数位   则返回 0.0001000
     *
     * @param digits 字符串 "#0.00"
     * @param FLOOR  舍入模式 RoundingMode
     * @return
     */
    public static String formatDecimals(Double str, String digits, RoundingMode FLOOR) {
        DecimalFormat df = new DecimalFormat(digits);
        df.setRoundingMode(FLOOR);
        return df.format(str);
    }


    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串  不四舍五入
     *
     * @param aDouble
     * @param digits  保留的小数点的位数
     * @return
     */
    public static String formatDecimals(Double aDouble, int digits) {
        return formatDecimals(aDouble, digits, 0);
    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串  不四舍五入
     *
     * @param aDouble
     * @param digits
     * @param groupingSize
     * @return
     */
    public static String formatDecimals(Double aDouble, int digits, int groupingSize) {
        return formatDecimals(aDouble, digits, groupingSize, RoundingMode.FLOOR);
    }

    /**
     * 将字符串格式化为 (自定义位数 ) 小数的字符串 自定义是否四舍五入 如果超出位数,小数点全是0.00000000 的时候   直接返回0
     *
     * @param aDouble
     * @param digits
     * @param groupingSize
     * @param FLOOR
     * @return
     */
    public static String formatDecimals(Double aDouble, int digits, int groupingSize, RoundingMode FLOOR) {
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(digits);
        formater.setGroupingSize(groupingSize);
        formater.setRoundingMode(FLOOR);
        String result = formater.format(aDouble);
        return result;
    }

    /**
     * 将字符串从中间对折显示两行
     *
     * @param str 字符串
     * @return
     */
    public static String stringTwoLines(String str) {
        if (YcStringUtils.isEmpty(str))
            return "";
        int i = str.length() / 2;
        String substring = str.substring(0, i);
        String substring1 = str.substring(i, str.length());
        return substring + "\n" + substring1;
    }

    /**
     * 隐藏字符串中间的缺省
     *
     * @param start
     * @param end
     * @param center
     * @param string
     * @param centerString
     * @return
     */
    public static String hideCenterString(int start, int end, int center, String centerString, String string) {
        if (YcStringUtils.isEmpty(string))
            return "";
        String substring = string.substring(0, start);
        String substring1 = string.substring(string.length() - end, string.length());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < center; i++) {
            stringBuilder.append(centerString);
        }
        return substring + stringBuilder.toString() + substring1;
    }


    /**
     * 将Double格式化为带两位小数的字符串
     *
     * @param str 字符串
     * @return
     */
    public static String format2Decimals(Double str) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (df.format(str).startsWith(".")) {
            return "0" + df.format(str);
        } else {
            return df.format(str);
        }
    }

    /**
     * 保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值 四舍五入
     *
     * @param str    "#0.00"
     * @param digits
     * @return
     */

    public static String formatDecimals(String str, int digit, String digits) {

        int i = judgingStringHasFewDecimal(str);
        if (digit > i) {
            return str;
        } else {
            DecimalFormat df = new DecimalFormat(digits);
            if (df.format(stringToDouble(str)).startsWith(".")) {
                return "0" + df.format(stringToDouble(str));
            } else {
                return df.format(stringToDouble(str));
            }
        }
    }

    /**
     * 保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值 四舍五入
     *
     * @param digit
     * @return
     */

    public static String formatDecimalsRounding(String str, int digit) {

        int i = judgingStringHasFewDecimal(str);
        if (digit > i) {
            return str;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#0.");
            for (int j = 0; j < digit; j++) {
                stringBuilder.append("0");
            }
            DecimalFormat df = new DecimalFormat(stringBuilder.toString());
            if (df.format(stringToDouble(str)).startsWith(".")) {
                return "0" + df.format(stringToDouble(str));
            } else {
                return df.format(stringToDouble(str));
            }
        }
    }

    /**
     * 保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值  不四舍五入
     *
     * @param digit
     * @return
     */

    public static String formatDecimalsNoRounding(String str, int digit) {

        int i = judgingStringHasFewDecimal(str);
        if (digit > i) {
            return str;
        } else {
            return formatDecimals(stringToDouble(str), digit);
        }
    }

    /**
     * 字符串转InputStream
     *
     * @param str
     * @return
     */
    public static InputStream StringToInputStream(String str) {
        InputStream in_nocode = new ByteArrayInputStream(str.getBytes());
        //InputStream   in_withcode   =   new ByteArrayInputStream(str.getBytes("UTF-8"));
        return in_nocode;
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isNullString(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isNullString(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isNullString(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isNullString(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 单个汉字转成ASCII码
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的ascii码，否则返回-1
     */
    public static int oneCn2ASCII(String s) {
        if (s.length() != 1) {
            return -1;
        }
        int ascii = 0;
        try {
            byte[] bytes = s.getBytes("GB2312");
            if (bytes.length == 1) {
                ascii = bytes[0];
            } else if (bytes.length == 2) {
                int highByte = 256 + bytes[0];
                int lowByte = 256 + bytes[1];
                ascii = (256 * highByte + lowByte) - 256 * 256;
            } else {
                throw new IllegalArgumentException("Illegal resource string");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ascii;
    }

    /**
     * 单个汉字转成拼音
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的拼音，否则返回{@code null}
     */
    public static String oneCn2PY(String s) {
        int ascii = oneCn2ASCII(s);
        if (ascii == -1) {
            return null;
        }
        String ret = null;
        if (0 <= ascii && ascii <= 127) {
            ret = String.valueOf((char) ascii);
        } else {
            for (int i = pyValue.length - 1; i >= 0; i--) {
                if (pyValue[i] <= ascii) {
                    ret = pyStr[i];
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 获得第一个汉字首字母
     *
     * @param s 单个汉字字符串
     * @return 拼音
     */
    public static String getPYFirstLetter(String s) {
        if (isNullString(s)) {
            return "";
        }
        String first, py;
        first = s.substring(0, 1);
        py = oneCn2PY(first);
        if (py == null) {
            return null;
        }
        return py.substring(0, 1);
    }

    /**
     * 中文转拼音
     *
     * @param s 汉字字符串
     * @return 拼音
     */
    public static String cn2PY(String s) {
        String hz, py;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            hz = s.substring(i, i + 1);
            py = oneCn2PY(hz);
            if (py == null) {
                py = "?";
            }
            sb.append(py);
        }
        return sb.toString();
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

    /**
     * charArr转byteArr
     *
     * @param chars 字符数组
     * @return 字节数组
     */
    public static byte[] chars2Bytes(char[] chars) {
        int len = chars.length;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (chars[i]);
        }
        return bytes;
    }

    /**
     * byteArr转charArr
     *
     * @param bytes 字节数组
     * @return 字符数组
     */
    public static char[] bytes2Chars(byte[] bytes) {
        int len = bytes.length;
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) (bytes[i] & 0xff);
        }
        return chars;
    }

    /**
     * 字节数转以unit为单位的size
     *
     * @param byteNum 字节数
     * @return 以unit为单位的size
     */
    public static double byte2Size(long byteNum, int unit) {
        if (byteNum < 0) {
            return -1;
        }
        switch (unit) {
            default:
            case BYTE:
                return (double) byteNum / BYTE;
            case KB:
                return (double) byteNum / KB;
            case MB:
                return (double) byteNum / MB;
            case GB:
                return (double) byteNum / GB;
        }
    }

    /**
     * 以unit为单位的size转字节数
     *
     * @param size 大小
     * @return 字节数
     */
    public static long size2Byte(long size, int unit) {
        if (size < 0) {
            return -1;
        }
        switch (unit) {
            default:
            case BYTE:
                return size * BYTE;
            case KB:
                return size * KB;
            case MB:
                return size * MB;
            case GB:
                return size * GB;
        }
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
     * inputStream转outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */
    public static ByteArrayOutputStream input2OutputStream(InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[KB];
            int len;
            while ((len = is.read(b, 0, KB)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            YcFileUtils.closeIO(is);
        }
    }

    /**
     * inputStream转byteArr
     *
     * @param is 输入流
     * @return 字节数组
     */
    public static byte[] inputStream2Bytes(InputStream is) {
        return input2OutputStream(is).toByteArray();
    }

    /**
     * byteArr转inputStream
     *
     * @param bytes 字节数组
     * @return 输入流
     */
    public static InputStream bytes2InputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * outputStream转byteArr
     *
     * @param out 输出流
     * @return 字节数组
     */
    public static byte[] outputStream2Bytes(OutputStream out) {
        if (out == null) {
            return null;
        }
        return ((ByteArrayOutputStream) out).toByteArray();
    }

    /**
     * outputStream转byteArr
     *
     * @param bytes 字节数组
     * @return 字节数组
     */
    public static OutputStream bytes2OutputStream(byte[] bytes) {
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            os.write(bytes);
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            YcFileUtils.closeIO(os);
        }
    }

    /**
     * inputStream转string按编码
     *
     * @param is          输入流
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String inputStream2String(InputStream is, String charsetName) {
        if (is == null || isNullString(charsetName)) {
            return null;
        }
        try {
            return new String(inputStream2Bytes(is), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string转inputStream按编码
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输入流
     */
    public static InputStream string2InputStream(String string, String charsetName) {
        if (string == null || isNullString(charsetName)) {
            return null;
        }
        try {
            return new ByteArrayInputStream(string.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * outputStream转string按编码
     *
     * @param out         输出流
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String outputStream2String(OutputStream out, String charsetName) {
        if (out == null) {
            return null;
        }
        try {
            return new String(outputStream2Bytes(out), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string转outputStream按编码
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输入流
     */
    public static OutputStream string2OutputStream(String string, String charsetName) {
        if (string == null || isNullString(charsetName)) {
            return null;
        }
        try {
            return bytes2OutputStream(string.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(double value) {
        return AMOUNT_FORMAT.format(value);
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(String value) {
        if (isNullString(value)) {
            return "0";
        }
        return AMOUNT_FORMAT.format(Double.parseDouble(value));
    }

    /**
     * 将数值四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(BigDecimal value, int digit) {
        return value.setScale(digit, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 将数值四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(String value, int digit) {
        if (isNullString(value)) {
            return "0";
        }
        return new BigDecimal(value).setScale(digit, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 将数值不四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundDown(String value, int digit) {
        if (isNullString(value)) {
            return "0";
        }
        return new BigDecimal(value).setScale(digit, BigDecimal.ROUND_DOWN).toPlainString();
    }

    /**
     * 将数值不四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundDown(double value, int digit) {
        return new BigDecimal(value).setScale(digit, BigDecimal.ROUND_DOWN).toPlainString();
    }

    /**
     * 自定义四舍五入模式
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUpDown(double value, int digit, int roundingMode) {
        BigDecimal result = new BigDecimal(value);
        return result.setScale(digit, roundingMode).toPlainString();
    }

    /**
     * 自定义四舍五入模式
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUpDown(String value, int digit, int roundingMode) {
        if (isNullString(value)) {
            return "0";
        }
        BigDecimal result = new BigDecimal(value);
        return result.setScale(digit, roundingMode).toPlainString();
    }

    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(BigDecimal value, int digit) {
        BigDecimal result = value.multiply(new BigDecimal(100));
        return getRoundUp(result, digit);
    }

    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数的位数
     * @return
     */
    public static String getPercentValue(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, digit);
    }

    /**
     * 将字符串 乘 10 的几次方
     * <p>
     * Base - taken to mean default unit for a currency e.g. ETH, DOLLARS
     * 基准 - 用于表示货币的默认单位，例如, ETH，美元
     * Subunit - taken to mean subdivision of base e.g. WEI, CENTS
     * 亚基 - 用于表示基础的细分例如, WEI，CENTS
     *
     * @param baseAmountStr - decimal amonut in base unit of a given currency 以给定货币的基本单位计算十进制货币
     * @param decimals      - decimal places used to convert to subunits 小数位用于转换为亚单位
     * @return amount in subunits   金额在亚基
     */
    public static BigInteger baseToSubunit(String baseAmountStr, int decimals) {
        assert (decimals >= 0);
        BigDecimal baseAmount = new BigDecimal(baseAmountStr);
        BigDecimal subunitAmount = baseAmount.multiply(BigDecimal.valueOf(10).pow(decimals));
        try {
            return subunitAmount.toBigIntegerExact();
        } catch (ArithmeticException ex) {
            assert (false);
            return subunitAmount.toBigInteger();
        }
    }

    /**
     * 将大整形 除以 10 的 几次方
     *
     * @param subunitAmount - amouunt in subunits 在亚基中是有限的
     * @param decimals      - decimal places used to convert subunits to base 小数位用于将子单位转换为基数
     * @return amount in base units 金额以基本单位表示
     */
    public static BigDecimal subunitToBase(BigInteger subunitAmount, int decimals) {
        assert (decimals >= 0);
        return new BigDecimal(subunitAmount).divide(BigDecimal.valueOf(10).pow(decimals));
    }

    /**
     * outputStream转inputStream
     *
     * @param out 输出流
     * @return inputStream子类
     */
    public ByteArrayInputStream output2InputStream(OutputStream out) {
        if (out == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
    }

    private static int[] pyValue = new int[]{
            -20319, -20317, -20304, -20295, -20292, -20283, -20265, -20257, -20242,
            -20230, -20051, -20036, -20032,
            -20026, -20002, -19990, -19986, -19982, -19976, -19805, -19784, -19775, -19774, -19763, -19756, -19751,
            -19746, -19741, -19739, -19728,
            -19725, -19715, -19540, -19531, -19525, -19515, -19500, -19484, -19479, -19467, -19289, -19288, -19281,
            -19275, -19270, -19263, -19261,
            -19249, -19243, -19242, -19238, -19235, -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006,
            -19003, -18996, -18977, -18961,
            -18952, -18783, -18774, -18773, -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697, -18696,
            -18526, -18518, -18501, -18490,
            -18478, -18463, -18448, -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201, -18184, -18183,
            -18181, -18012, -17997, -17988,
            -17970, -17964, -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752, -17733, -17730, -17721,
            -17703, -17701, -17697, -17692,
            -17683, -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427, -17417, -17202, -17185, -16983,
            -16970, -16942, -16915, -16733,
            -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470, -16465, -16459, -16452, -16448, -16433,
            -16429, -16427, -16423, -16419,
            -16412, -16407, -16403, -16401, -16393, -16220, -16216, -16212, -16205, -16202, -16187, -16180, -16171,
            -16169, -16158, -16155, -15959,
            -15958, -15944, -15933, -15920, -15915, -15903, -15889, -15878, -15707, -15701, -15681, -15667, -15661,
            -15659, -15652, -15640, -15631,
            -15625, -15454, -15448, -15436, -15435, -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369,
            -15363, -15362, -15183, -15180,
            -15165, -15158, -15153, -15150, -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121, -15119,
            -15117, -15110, -15109, -14941,
            -14937, -14933, -14930, -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902, -14894, -14889,
            -14882, -14873, -14871, -14857,
            -14678, -14674, -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429, -14407, -14399, -14384,
            -14379, -14368, -14355, -14353,
            -14345, -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135, -14125, -14123, -14122, -14112,
            -14109, -14099, -14097, -14094,
            -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907, -13906, -13905, -13896, -13894, -13878,
            -13870, -13859, -13847, -13831,
            -13658, -13611, -13601, -13406, -13404, -13400, -13398, -13395, -13391, -13387, -13383, -13367, -13359,
            -13356, -13343, -13340, -13329,
            -13326, -13318, -13147, -13138, -13120, -13107, -13096, -13095, -13091, -13076, -13068, -13063, -13060,
            -12888, -12875, -12871, -12860,
            -12858, -12852, -12849, -12838, -12831, -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556,
            -12359, -12346, -12320, -12300,
            -12120, -12099, -12089, -12074, -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798, -11781,
            -11604, -11589, -11536, -11358,
            -11340, -11339, -11324, -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041, -11038, -11024,
            -11020, -11019, -11018, -11014,
            -10838, -10832, -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533, -10519, -10331, -10329,
            -10328, -10322, -10315, -10309,
            -10307, -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254};
    private static String[] pyStr = new String[]{
            /*A*/
            "a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao",

            /*B*/
            "bei", "ben", "beng", "bi", "bian", "biao", "bie", "bin", "bing", "bo", "bu",

            /*C*/
            "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong", "cou", "cu", "cuan", "cui", "cun", "cuo",

            /*D*/
            "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding", "diu", "dong", "dou", "du", "duan", "dui", "dun", "duo",

            /*E*/
            "e", "en", "er",

            /*F*/
            "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu",

            /*G*/
            "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo",

            /*H*/
            "ha", "hai", "han", "hang", "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun", "huo",

            /*J*/
            "ji", "jia", "jian", "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun",

            /*K*/
            "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo",

            /*L*/
            "la", "lai", "lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan", "lue", "lun", "luo",

            /*M*/
            "ma", "mai", "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu", "mo", "mou", "mu",

            /*N*/
            "na", "nai", "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan", "nue", "nuo",

            /*O*/
            "o", "ou",

            /*P*/
            "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po", "pu",

            /*Q*/
            "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun",

            /*R*/
            "ran", "rang", "rao", "re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo",

            /*S*/
            "sa", "sai", "san", "sang", "sao", "se", "sen", "seng", "sha", "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun", "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo",

            /*T*/
            "ta", "tai", "tan", "tang", "tao", "te", "teng", "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo",

            /*W*/
            "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu",

            /*X*/
            "xi", "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun",

            /*Y*/
            "ya", "yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun",

            /*Z*/
            "za", "zai", "zan", "zang", "zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"
    };
}
