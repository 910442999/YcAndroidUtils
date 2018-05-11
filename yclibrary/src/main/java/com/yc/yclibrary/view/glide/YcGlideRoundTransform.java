package com.yc.yclibrary.view.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.yc.yclibrary.YcCircularBeadUtils;

import java.security.MessageDigest;

public class YcGlideRoundTransform extends BitmapTransformation {

    private  float radius = 0f;
    private Context context;

    public YcGlideRoundTransform(Context context, int dp) {
        this.context = context;
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return YcCircularBeadUtils.instance().roundCrop(pool, toTransform,radius);
    }



    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}

