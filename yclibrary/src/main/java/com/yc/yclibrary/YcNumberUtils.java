package com.yc.yclibrary;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字，汉字相关 //用户名：3-15任意字符组合 usernameRegex = /^\w{3,15}$/; //密码：6-12任意字符组合
 * passwordRegex = /^\w{6,12}$/; //邮箱：自己实现验证规则 emailRegex = /^\w+@\w+(\.\w+)+$/;
 * //真实姓名：必须是2-5中文 realNameRegex = /^[\u4e00-\u9fa5]{2,5}$/;
 *
 * @author shangyf
 */
public class YcNumberUtils {
    /**
     * 判断是否为手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^1[0-9][0-9]\\d{8}$");
        //        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断是否是8到12位  同时包含字母或数字
     *
     * @param number
     * @return
     */
    public static boolean isEightToTwelveLetterNumber(String number) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,12}$";
        return Pattern.matches(reg, number);
    }


    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断当前为纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断当前为纯字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断当前为汉字
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断当前字符串为数字和字母
     *
     * @param number
     * @return
     */
    public static boolean isLetterAndAlphabet(String number) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 将服务器返回的int-----> double 分------->元
     *
     * @param Decimal
     * @return
     */
    public static String getDecimal(int Decimal) {
        double a = (double) Decimal / 100;
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(a);
    }

    /**
     * 保留俩位小数
     *
     * @param price
     * @return
     */
    public static String getDecimal(double price) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(price);
    }

    /**
     * 保留一位小数
     *
     * @param price
     * @return
     */
    public static String getDecimalOne(double price) {
        DecimalFormat df = new DecimalFormat("##0.0");
        return df.format(price);
    }

    /**
     * 验证用户名
     *
     * @param realName
     * @return
     */
    public static boolean getUserName(String realName) {
        String name = "^[\u4e00-\u9fa5]{2,5}$;";
        Pattern pattern = Pattern.compile(name);
        return pattern.matcher(realName).matches();
    }

    /**
     * 获取带*的手机号码
     *
     * @param phone
     * @return
     */
    public static String getPhone(String phone) {
        String s1 = phone.substring(0, 3);
        String s2 = phone.substring(7, 11);
        return s1 + "****" + s2;
    }

    /**
     * 获取随机数
     *
     * @param count
     * @return
     */
    public static String getRandom(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断车牌号
     *
     * @param number
     * @return
     */
    public static boolean isPlateNumber(String number) {
        if (TextUtils.isEmpty(number))
            return false;
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 判断车架号
     *
     * @param number
     * @return
     */
    public static boolean isCarFrame(String number) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{0,17}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 判断发动机号
     *
     * @param number
     * @return
     */
    public static boolean isEngine(String number) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9-]{0,25}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }
}
