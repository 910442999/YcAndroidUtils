package com.yc.yclibrary;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.MathContext.UNLIMITED;

/**
 * Created by zhanghan on 2018/5/17.
 */

/**
 * addition 加法运算
 * subtraction 减法运算
 * multiplication 乘法运算
 * DivideOperation 除法运算
 *  DecimalProcessing    提供精确的小数位处理(格式化数字,自定义摄入模式)
 */
public class YcCalculationRelatedUtils {
    /**
     * 加法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static double addition(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).doubleValue();
    }

    /**
     * 加法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static String addition(String m1, String m2) {
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.add(p2).toString();
    }

    /**
     * 减法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static double subtraction(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static String subtraction(String m1, String m2) {
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.subtract(p2).toString();
    }

    /**
     * 乘法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static double multiplication(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static String multiplication(String m1, String m2) {
        BigDecimal p1 = new BigDecimal(m1, UNLIMITED);
        BigDecimal p2 = new BigDecimal(m2, UNLIMITED);
        return p1.multiply(p2).toString();
    }

    /**
     * 乘法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static float multiplicationToFloat(float m1, float m2) {
        BigDecimal p1 = new BigDecimal(Float.toString(m1));
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return p1.multiply(p2).floatValue();
    }

    /**
     * 乘法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static String multiplicationToString(float m1, float m2) {
        BigDecimal p1 = new BigDecimal(Float.toString(m1));
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return p1.multiply(p2).toString();
    }


    /**
     * 除法运算
     *
     * @param m1
     * @param m2
     * @param scale
     * @return
     */
    public static double DivideOperation(double m1, double m2, int scale) {

        return DivideOperation(Double.toString(m1), Double.toString(m2), scale);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，(BigDecimal.ROUND_HALF_UP)以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double DivideOperation(String v1, String v2, int scale) {
        return DivideOperation(v1, v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，自定义四舍五入模式。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double DivideOperation(String v1, String v2, int scale, int roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b2.divide(b1, scale, roundingMode).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，自定义四舍五入模式。
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        表示表示需要精确到小数点以后几位。
     * @param roundingMode 舍入模式
     * @return 两个参数的商
     */
    public static double DivideOperation(String v1, String v2, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b2.divide(b1, scale, roundingMode).doubleValue();
    }

    /**
     * 提供精确的小数位处理。
     *
     * @param v            需要四舍五入的数字
     * @param scale        小数点后保留几位
     * @param roundingMode 舍入模式 参考 BigDecimal.ROUND_HALF_UP(四舍五入模式)
     * @return 结果
     */
    public static double DecimalProcessing(double v, int scale, int roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, roundingMode).doubleValue();
    }

    /**
     * 提供精确的小数位处理。
     *
     * @param v            需要四舍五入的数字
     * @param scale        小数点后保留几位
     * @param roundingMode 舍入模式 参考 BigDecimal.ROUND_HALF_UP(四舍五入模式)
     * @return 结果
     */
    public static double DecimalProcessing(String v, int scale, int roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, roundingMode).doubleValue();
    }

}
