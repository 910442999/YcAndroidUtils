package com.yc.yclibrary.view.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.yc.yclibrary.YcImageUtils;

import java.security.MessageDigest;

public class YcGlideBlurformation extends BitmapTransformation {
    private Context context;
    public YcGlideBlurformation(Context context) {
        this.context = context;
    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return YcImageUtils.blurBitmap(context, toTransform, 20,outWidth,outHeight);
    }
    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }
}