package com.yc.yclibrary;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

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

        //        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df = new DecimalFormat("#.000");
        df.setMaximumFractionDigits(10);
        System.out.println(df.format(v7));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(v8));
        System.out.println(String.format("%.4f", v8));
        DecimalFormat format5 = (DecimalFormat) NumberFormat.getPercentInstance();
        format5.applyPattern("#####0 ");
        System.out.println(format5.format(v8));
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

    @Test
    public void test() {
//        Double str = 11111534534.00000000000000000000001222;
//        String str2 = "11111534534.00000000000000000000001222";

         Double str = 0.000001;
        String str2 = "0.000000000000000000000000000000001";


//        String str = "1111111111sdfghjklsdfghjkert";
        //        int index = str.last IndexOf(".");//寻找小数点的索引位置，若不是小数，则为-1
        //        if (index > -1) {
        //
        //            int len = str.substring(index + 1).length();//取得小数点后的数值，不包括小数点
        //            System.out.println(len);
        ////            str += len == 1 ? "0" : "";
        //            System.out.println(str);
        //        }

        //        DecimalFormat df = new DecimalFormat("#.");
        //        //        BigDecimal result = new BigDecimal(str);
        //        ////        String bigDecimal = result.setScale(10, BigDecimal.ROUND_HALF_DOWN).toString();
        //        df.setMaximumFractionDigits(10);
        //        System.out.println(df.format(str));
        //------------------------------------------------------
        //        StringBuilder stringBuilder  = new StringBuilder();
        //        stringBuilder.append("#0.");
        //        for (int j = 0; j < 5; j++) {
        //            stringBuilder.append("0");
        //        }
        //        DecimalFormat df = new DecimalFormat(stringBuilder.toString());
        //        System.out.println(df.format(str));
        //---------------------------------

        System.out.println(BigDecimal.ROUND_HALF_UP==4);

        System.out.println( new BigDecimal(str).setScale(12, BigDecimal.ROUND_FLOOR).toString() );



        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
//        System.out.println(formater.format(str2));
//        String result = formater.format("3.1495926");
//        System.out.println(result);

        DecimalFormat formater2 = new DecimalFormat("#0.0000000000");
        formater2.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(formater2.format(str));
//        System.out.println(formater2.format(str2));

    }
}