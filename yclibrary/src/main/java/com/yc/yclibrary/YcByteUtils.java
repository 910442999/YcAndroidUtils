package com.yc.yclibrary;

import android.util.Log;

import java.nio.ByteBuffer;

/**
 * Byte相关工具类
 * <p>
 * hexStringToByte      将16进制字符串转化为bytes数组
 * bytesToHexString     将bytes数组转化为16进制字符串
 * toByte               将Short Int类型转化为16进制再转化为byte数组
 * int2Bytes            将INT类型转化为10进制byte数组（占4字节）
 * intToHexToBytes      将int类型转化为16进制数，转化为byte数组类型
 * intToHexToByte       将int类型转化为16进制数，转化为byte类型
 * int2OneByte          将int类型转化为byte类型
 * twoBytes2Int         将2个字节的byte数组转化为int类型
 * oneByte2Int          将byte类型数转化为int类型
 * byteToInt16          将16进制的byte类型转化为10进制的int类型
 * bytes2Int            将byte类型数组（4字节）转化为int类型
 * long2Bytes           将长整形转化为byte数组
 * bytes2Long           将byte数组（长度为8）转化为长整形
 * byte162float         将16进制的byte数组转化为float类型
 * float2ByteArray      将float转化为byte数组，占用4个字节
 * bytes2float          将10进制byte数组转化为Float
 * byteCompare          两个byte数组是否值相等的比较
 * byteToBit            将byte（字节）类型转化为位
 * byteHexString        将指定byte数组以16进制的形式转成字符串
 */
public class YcByteUtils {




    /*将16进制字符串转化为bytes数组*/


    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));

        }
        return result;

    }

    /*将bytes数组转化为16进制字符串*/


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;

        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);

            }
            stringBuilder.append(hv);

        }
        return stringBuilder.toString();

    }




    /*将Short Int类型转化为16进制再转化为byte数组*/


    private static byte toByte(char c) {
        byte b = (byte) YcStringUtils.hexString.indexOf(c);
        return b;

    }


    /*将INT类型转化为10进制byte数组（占4字节）*/


    public static byte[] int2Bytes(int num) {
        byte[] byteNum = new byte[4];
        for (int ix = 0; ix < 4; ++ix) {
            int offset = 32 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);

        }
        return byteNum;

    }


    /*将int类型转化为16进制数，转化为byte数组类型*/


    public static byte[] intToHexToBytes(short num) {
        byte[] byteNum = new byte[]{(byte) ((num >> 8) & 0xFF), (byte) (num & 0xFF)};
        return byteNum;

    }

    /*将int类型转化为16进制数，转化为byte类型*/


    public static byte intToHexToByte(int integer) {
        String hexStr = Integer.toHexString(integer);
        Log.i("data", hexStr);
        return Byte.valueOf(hexStr, 16);

    }


    /*将int类型转化为byte类型*/


    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);

    }


    /*将2个字节的byte数组转化为int类型*/


    public static int twoBytes2Int(byte[] buffer) {
        return buffer[0] | buffer[1] << 8;

    }

    /*将byte类型数转化为int类型*/


    public static int oneByte2Int(byte byteNum) {
        return byteNum & 0xFF;

    }

    /*将16进制的byte类型转化为10进制的int类型*/


    public static int byteToInt16(byte b) {
        String result = Integer.toHexString(b & 0xFF);
        return Integer.valueOf(result, 16);

    }

    /*将byte类型数组（4字节）转化为int类型*/


    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < 4; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);

        }
        return num;

    }



    /*将长整形转化为byte数组*/


    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);

        }
        return byteNum;

    }

    /*将byte数组（长度为8）转化为长整形*/


    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);

        }
        return num;

    }


    /**
     * 将16进制的byte数组转化为float类型
     **/


    public static float byte162float(byte[] num) {
        String hexString = bytesToHexString(num);
        Integer temp = Integer.valueOf(hexString.trim(), 16);
        float value = Float.intBitsToFloat(temp.intValue());
        System.out.println(value);
        return value;

    }


    /**
     * 将float转化为byte数组，占用4个字节
     **/


    public static byte[] float2ByteArray(float value)


    {
        return ByteBuffer.allocate(4).putFloat(value).array();

    }

    /**
     * 将10进制byte数组转化为Float
     *
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */


    public static float bytes2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);

    }


    /*两个byte数组是否值相等的比较*/


    public static boolean byteCompare(byte[] data1, byte[] data2, int len) {
        if (data1 == null && data2 == null) {
            return true;

        }
        if (data1 == null || data2 == null) {
            return false;

        }
        if (data1 == data2) {
            return true;

        }
        boolean bEquals = true;
        int i;
        for (i = 0; i < data1.length && i < data2.length && i < len; i++) {
            if (data1[i] != data2[i]) {
                bEquals = false;
                break;

            }

        }
        return bEquals;

    }

    /*将byte（字节）类型转化为位*/


    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) +
                (byte) ((b >> 6) & 0x1) +
                (byte) ((b >> 5) & 0x1) +
                (byte) ((b >> 4) & 0x1) +
                (byte) ((b >> 3) & 0x1) +
                (byte) ((b >> 2) & 0x1) +
                (byte) ((b >> 1) & 0x1) +
                (byte) ((b >> 0) & 0x1);

    }



    /*将指定byte数组以16进制的形式转成字符串*/


    public static String byteHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;

            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}