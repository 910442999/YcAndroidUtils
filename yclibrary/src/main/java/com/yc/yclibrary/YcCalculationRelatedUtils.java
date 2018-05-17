package com.yc.yclibrary;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

import static java.math.MathContext.UNLIMITED;

/**
 * Created by zhanghan on 2018/5/17.
 */

/**
 * addition 加法运算
 * subtraction 减法运算
 * multiplication 乘法运算
 * DivideOperation 除法运算
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
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法运算
     *
     * @param m1
     * @param m2
     * @param scale
     * @return
     */
    public static double DivideOperation(String m1, String m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
