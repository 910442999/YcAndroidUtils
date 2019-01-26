package com.yc.yclibrary;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

/**
 * 圆角工具类
 */
public class YcCircularBeadUtils {
    private static YcCircularBeadUtils sInstance;

    private YcCircularBeadUtils() {
    }

    public static YcCircularBeadUtils instance() {
        if (sInstance == null) {
            synchronized (YcCircularBeadUtils.class) {
                if (sInstance == null) {
                    sInstance = new YcCircularBeadUtils();
                }
            }
        }
        return sInstance;
    }

    /**
     * 圆角Bitmap图片
     *
     * @param pool
     * @param source
     * @param radius
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Bitmap roundCrop(BitmapPool pool, Bitmap source, float radius) {
        if (source == null)
            return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }
}
