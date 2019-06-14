package com.yc.yclibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.yc.yclibrary.view.glide.YcGlideBlurformation;
import com.yc.yclibrary.view.glide.YcGlideCircleTransform;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Glide加载网络图片相关
 * <p>
 * loadImageView       加载网络图片
 * LoadingThumbnails   缩略图支持
 * loadingGif          加载gif图片
 * loadFilletImage     加载网络圆角图片
 * loadRoundCornerImage     自定义圆角角度
 * loadCircleImage     加载网络圆型图片
 * loadLocal           加载本地图片
 * loadCrossFade       加载图片带淡入淡出的动画效果
 * loadingBlurformation    加载一个图片为高斯模糊效果
 * loadingClean            清理磁盘的缓存
 * commonRequestOptions 公共的 gilde请求选项
 * getImagePath Glide 获得图片缓存路径
 * loadCircleBorderTransform 圆形带边框
 */
public class YcGlideUtils {


    public static void loadImageView(Context context, String url, ImageView img) {
        if (context == null)
            return;
        loadImageView(context, url, img, R.drawable.placeholder, R.drawable.placeholder, YcConstUtils.NONE);
    }

    public static void loadImageView(Context context, String url, ImageView img, int load) {
        if (context == null)
            return;
        loadImageView(context, url, img, load, R.drawable.placeholder, YcConstUtils.NONE);
    }

    public static void loadImageView(Context context, String url, ImageView img, int load, int err) {
        if (context == null)
            return;
        loadImageView(context, url, img, load, err, YcConstUtils.NONE);
    }

    /**
     * @param url
     * @param img
     * @param load
     * @param err
     * @param diskCache 如果是 YcConstUtils.NONE 不缓存  如果是 YcConstUtils.ALL   全部缓存  AUTOMATIC默认
     */
    public static void loadImageView(Context context, String url, ImageView img, int load, int err, int diskCache) {
        if (context == null)
            return;
        RequestOptions requestOptions = commonRequestOptions(load, err, null, diskCache);
        loading(context, url, requestOptions, img);
    }

    /**
     * 缩略图支持 - 现在，您可以同时将多个图像加载到相同的视图中，这样您就可以最大限度地减少用户在不牺牲质量的情况下查看加载调整器的时间。要首先在视图大小的1/10处加载缩略图，然后将完整图像加载到顶部
     *
     * @param context
     * @param url            连接
     * @param sizeMultiplier 0.1f 压缩大小乘数
     * @param imageView
     */
    public static void LoadingThumbnails(Context context, String url, float sizeMultiplier, ImageView imageView) {
        if (context == null)
            return;
        Glide.with(context).load(url).thumbnail(sizeMultiplier).into(imageView);
    }

    /**
     * glide加载本地 gif 图片
     *
     * @param context
     * @param url
     * @param as
     */
    public static void loadingGif(Context context, int url, ImageView imageView, int as, int preRes, int preErr, Transformation<Bitmap> transformation, int diskCache) {
        if (context == null)
            return;
        RequestOptions requestOptions = commonRequestOptions(preRes, preErr, transformation, diskCache);
        if (as == YcConstUtils.NONE) {
            Glide.with(context).asBitmap().apply(requestOptions).load(url).into(imageView);//它始终加载静态图像，
        } else {
            Glide.with(context).asGif().apply(requestOptions).load(url).into(imageView);//除非图像是动画gif ，否则将失败。
        }
    }

    /**
     * glide加载网络 gif 图片
     *
     * @param context
     * @param url
     * @param as
     */
    public static void loadingGif(Context context, String url, ImageView imageView, int as, int preRes, int preErr, Transformation<Bitmap> transformation, int diskCache) {
        if (context == null)
            return;
        RequestOptions requestOptions = commonRequestOptions(preRes, preErr, transformation, diskCache);
        if (as == YcConstUtils.NONE) {
            Glide.with(context).asBitmap().apply(requestOptions).load(url).into(imageView);//它始终加载静态图像，
        } else {
            Glide.with(context).asGif().apply(requestOptions).load(url).into(imageView);//除非图像是动画gif ，否则将失败。
        }
    }

    //自定义圆角角度
    public static void loadRoundCornerImage(Context context, String url, ImageView imageView, int radiusDp, int loading) {
        if (context == null)
            return;
        loadRoundCornerImage(context, url, imageView, radiusDp, loading, 300, 300, YcConstUtils.ALL);
    }

    /**
     * 自定义圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radiusDp
     * @param loading
     * @param width     采样率 500
     * @param height    采样率 500
     * @param diskCache
     */
    public static void loadRoundCornerImage(Context context, String url, ImageView imageView, int radiusDp, int loading, int width, int height, int diskCache) {
        if (context == null)
            return;
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(radiusDp);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions requestOptions = commonRequestOptions(loading, loading, roundedCorners, diskCache).override(width, height);
        loading(context, url, requestOptions, imageView);
    }

    /**
     * 加载网络圆型图片
     *
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        if (context == null)
            return;
        loadCircleImage(context, url, imageView, -1, -1, -1);
    }

    /**
     * 加载网络圆型图片
     *
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int load, int err) {
        if (context == null)
            return;
        loadCircleImage(context, url, imageView, load, err, -1);
    }

    /**
     * 加载网络圆型图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param load      正在加载中的图片
     * @param err       加载失败的图片
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int load, int err, int diskCache) {
        if (context == null)
            return;
        RequestOptions requestOptions = commonRequestOptions(load, err, null, diskCache)
                .circleCrop() //圆形裁剪
                .autoClone(); //自动填充
        loading(context, url, requestOptions, imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void loadLocal(Context context, int resId, ImageView imageView) {
        if (context == null)
            return;
        loadLocal(context, resId, null, imageView, R.drawable.placeholder, R.drawable.placeholder, -1);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void loadLocal(Context context, int resId, Transformation<Bitmap> transformation, ImageView imageView, int preRes, int preErr, int diskCache) {
        if (context == null)
            return;
        RequestOptions requestOptions = commonRequestOptions(preRes, preErr, transformation, diskCache);
        Glide.with(context).load(resId).apply(requestOptions).into(imageView);
    }

    /**
     * 加载图片带淡入淡出的动画效果
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCrossFade(Context context, String url, ImageView imageView) {
        if (context == null)
            return;
        try {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载一个图片为高斯模糊效果
     *
     * @param context
     */
    public static void loadingBlurformation(Context context, String url, ImageView imageView) {
       loadingBlurformation(context, url, imageView, -1, -1, YcConstUtils.ALL);
    }

    /**
     * 加载一个图片为高斯模糊效果
     *
     * @param context
     */
    public static void loadingBlurformation(Context context, String url, ImageView imageView, int preRes, int preErr, int diskCache) {
        if (context == null)
            return;
        loading(context, url, commonRequestOptions(preRes, preErr, new YcGlideBlurformation(context), diskCache), imageView);
    }

    /**
     * 圆形带边框
     *
     * @param context
     * @param resId
     * @param imageView
     * @param preRes
     */

    public static void loadCircleBorderTransform(Context context, int resId, ImageView imageView, float borderWidth, int borderColor, int preRes, int preErr, int diskCache) {
        if (context == null)
            return;
        YcGlideCircleTransform glideCircleTransform = new YcGlideCircleTransform(borderWidth, borderColor);
        loadLocal(context, resId, glideCircleTransform, imageView, preRes, preErr, diskCache);
    }

    /**
     * 圆形带边框
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param preRes
     */
    public static void loadCircleBorderTransform(Context context, String url, ImageView imageView, float borderWidth, int borderColor, int preRes, int preErr, int diskCache) {
        if (context == null)
            return;
        YcGlideCircleTransform glideCircleTransform = new YcGlideCircleTransform(borderWidth, borderColor);
        RequestOptions requestOptions = commonRequestOptions(preRes, preErr, glideCircleTransform, diskCache);
        loading(context, url, requestOptions, imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url            加载的链接
     * @param requestOptions 请求的选项
     * @param imageView      视图控件
     */
    public static void loading(Context context, String url, RequestOptions requestOptions, ImageView imageView) {
        if (context == null)
            return;
        try {
            Glide.with(context).load(url).apply(requestOptions).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Glide 获得图片缓存路径
     */
    public static String getImagePath(Context context, String imgUrl) {
        if (context == null)
            return "";
        String path = null;
        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 清理磁盘的缓存
     *
     * @param context applicationContext  全局上下文
     */
    public static void loadingClean(Context context) {
        if (context == null)
            return;
        try {
            Glide.get(context).clearDiskCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共的 gilde请求选项
     *
     * @param preRes
     * @param transformation
     * @param diskCache      -1 默认
     * @return
     */
    public static RequestOptions commonRequestOptions(int preRes, int preErr, Transformation<Bitmap> transformation, int diskCache) {
        RequestOptions options;
        if (transformation == null) {
            options = new RequestOptions();
        } else {
            options = new RequestOptions().bitmapTransform(transformation);
        }
        if (preRes != -1) {
            options = options.placeholder(preRes);
        }
        if (preErr != -1) {
            options = options.error(preErr);
        }
        if (diskCache != -1) {
            //DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
            if (diskCache == YcConstUtils.NONE) {
                options = options.diskCacheStrategy(DiskCacheStrategy.NONE);// 磁盘缓存策略
            } else if (diskCache == YcConstUtils.ALL) {
                options = options.diskCacheStrategy(DiskCacheStrategy.ALL);
            } else if (diskCache == YcConstUtils.DATA) {
                options = options.diskCacheStrategy(DiskCacheStrategy.DATA);
            } else if (diskCache == YcConstUtils.RESOURCE) {
                options = options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            } else if (diskCache == YcConstUtils.SIGNATURE) {
                //根据key重新缓存
                options = options.signature(new ObjectKey(YcTimeUtils.getNowString()));
            } else if (diskCache == YcConstUtils.ONLY_RETRIEVE_FROM_CACHE) {
                //仅从缓存加载图片
                options = options.onlyRetrieveFromCache(true);
            } else if (diskCache == YcConstUtils.SKIP_MEMORY_CACHE) {
                //跳过内存缓存
                options = options.skipMemoryCache(true);
            } else if (diskCache == YcConstUtils.SKIP_MEMORY_CACHE) {
                //跳过所有的缓存
                options = options.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
            }
        }
        return options;
    }
}
