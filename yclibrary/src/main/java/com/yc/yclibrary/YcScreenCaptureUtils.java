package com.yc.yclibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.view.View;

/**
 * 截图相关
 */
public class YcScreenCaptureUtils {

    /**
     * 使用View的缓存功能，截取指定区域的View
     */
    public static Bitmap screenShotView(View view) {
        //开启缓存功能
        view.setDrawingCacheEnabled(true);
        //创建缓存
        view.buildDrawingCache();
        //获取缓存Bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmap;
    }

    /**
     * 使用View的缓存功能，获取整个窗口的截图
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static Bitmap captureScreen(Activity context) {
        View cv = context.getWindow().getDecorView();

        cv.setDrawingCacheEnabled(true);
        cv.buildDrawingCache();
        Bitmap bmp = cv.getDrawingCache();
        if (bmp == null) {
            return null;
        }

        bmp.setHasAlpha(false);
        bmp.prepareToDraw();
        return bmp;
    }

    /**
     * 使用重绘 ,对单独某个View进行截图
     *
     * @param v
     * @return
     */
    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth() > 0 ? v.getWidth() : 720, v.getHeight() > 0 ? v.getHeight() : 1280, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(screenshot);
        c.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(c);
        return screenshot;
    }

    /**
     * 使用重绘 ,整个窗口的截图
     *
     * @param
     * @return
     */
    public static Bitmap loadBitmapFromActivity(Activity context) {
        if (context == null) {
            return null;
        }
        View v = context.getWindow().getDecorView();
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth() > 0 ? v.getWidth() : 720, v.getHeight() > 0 ? v.getHeight() : 1280, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(screenshot);
        c.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(c);
        return screenshot;
    }

    /**
     * 对WebView进行截图(webView加载的整个内容的大小)
     * 注意:5.0之后webview键入优化机制 需要设置加载整个webview才可整页截取 ,否则只截取显示部分
     * if (android.os.Build.VERSION.SDK_INT >= 21) {
     * enableSlowWholeDocumentDraw();
     * }
     *
     * @param webView
     * @return
     */
    public static Bitmap captureWebView(android.webkit.WebView webView) {//可执行
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
                snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }
}
