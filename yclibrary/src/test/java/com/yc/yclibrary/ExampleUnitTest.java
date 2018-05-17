package com.yc.yclibrary;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        //        assertEquals(4, 2 + 2);

        //10852.691
        String v = "5426334523454563.012332111222";
        String v2 = "5426334523454563.012332111222";
        double v6 = 5426334523454563.012332111222;
        double v7 = 5426334523454563.012332111222;
        double v8 = 3454563.222332111222;
        double v9 = 5426334.012332111222;
        System.out.println(v9 + "");
        Format format = new DecimalFormat();
        //        format.
        String addition = YcCalculationRelatedUtils.multiplication(v, v2);
        System.out.println(addition);
        double addition2 = YcCalculationRelatedUtils.multiplication(v6, v7);
        System.out.println(addition2);
        System.out.println(Double.toString(v8));
        System.out.println(v8 + "");
//        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df = new DecimalFormat("#.0000000000000");
        df.setMaximumFractionDigits(3);
        System.out.println(df.format(v7));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(v8));
        System.out.println(String.format("%.4f", v8));
        DecimalFormat format5 = (DecimalFormat) NumberFormat.getPercentInstance();
        format5.applyPattern( "#####0 ");
        System.out.println( format5.format(v8));
        //        System.out.println(Double.toString(v6));
//        BigDecimal p1 = new BigDecimal(Double.toString(v6));
//        BigDecimal p2 = new BigDecimal(v8 + "");
//        System.out.println(p1.multiply(p2).toString());
//
//        Float aFloat = Float.valueOf(v);
//        Float aFloat2 = Float.valueOf(v2);
//        float v1 = aFloat * aFloat2.floatValue();
//        float v3 = aFloat * aFloat2;
//        System.out.println(v1);
//        System.out.println(v3);
//
//        System.out.println(v1 * v3);
//        float addition5 = YcCalculationRelatedUtils.multiplicationToFloat(v1, v3);
//        System.out.println(addition5);

    }
}