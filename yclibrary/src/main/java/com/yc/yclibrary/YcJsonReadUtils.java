package com.yc.yclibrary;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * To do : 读取本地json文件
 *
 * asset文件 转 json字符串
 * Raw 文件转 json字符串
 *
 */

public class YcJsonReadUtils {
    /**
     * 从Assets目录里面读取json文件 并转换成String 字符串
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getAssetString(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 从Raw目录里面读取json文件 并转换成String 字符串
     *
     * @param context
     * @param id      R.raw.allcity
     * @return
     */
    public static String getRawString(Context context, int id) {
        StringBuilder stringBuffer = new StringBuilder();
        try {
            InputStream is = context.getResources().openRawResource(id);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = null;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
