package com.yc.yclibrary;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yc.yclibrary.view.glide.YcGlideBlurformation;
import com.yc.yclibrary.view.glide.YcGlideRoundTransform;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Glide加载网络图片相关
 * <p>
 * loadImageView       加载网络图片
 * LoadingThumbnails   缩略图支持
 * loadingGif          加载gif图片
 * loadFilletImage     加载网络圆角图片
 * loadCircleImage     加载网络圆型图片
 * loadLocal           加载本地图片
 * loadCrossFade       加载图片带淡入淡出的动画效果
 * loadingBlurformation    加载一个图片为高斯模糊效果
 * loadingClean            清理磁盘的缓存
 */
public class YcGlideUtils {

    /**
     * @param url
     * @param img
     * @param load
     * @param err
     * @param diskCache 如果是 YcConstUtils.NONE 不缓存  如果是 YcConstUtils.ALL   全部缓存  AUTOMATIC默认
     */
    public static void loadImageView(Context context, String url, ImageView img, int load, int err, int diskCache) {
        RequestOptions options = new RequestOptions()
                .placeholder(load);// 正在加载中的图片
        options.error(err); // 加载失败的图片
        if (diskCache == YcConstUtils.NONE) {
            options.diskCacheStrategy(DiskCacheStrategy.NONE); // 磁盘缓存策略
        } else if (diskCache == YcConstUtils.ALL) {
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else if (diskCache == YcConstUtils.DATA) {
            options.diskCacheStrategy(DiskCacheStrategy.DATA);
        } else if (diskCache == YcConstUtils.RESOURCE) {
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        }

        loading(context, url, options, img);
    }

    public static void loadImageView(Context context, String url, ImageView img) {
        loadImageView(context, url, img, R.drawable.placeholder, R.drawable.placeholder, YcConstUtils.NONE);
    }

    public static void loadImageView(Context context, String url, ImageView img, int load) {
        loadImageView(context, url, img, load, R.drawable.placeholder, YcConstUtils.NONE);
    }

    public static void loadImageView(Context context, String url, ImageView img, int load, int err) {
        loadImageView(context, url, img, load, err, YcConstUtils.NONE);
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
        Glide.with(context).load(url).thumbnail(0.1f).into(imageView);
    }

    /**
     * glide加载网络 gif 图片
     *
     * @param context
     * @param url
     * @param as
     */
    public static void loadingGif(Context context, String url, int as) {
        if (as == YcConstUtils.NONE) {
            Glide.with(context).asBitmap().load(url);//它始终加载静态图像，
        } else {
            Glide.with(context).asGif().load(url);//除非图像是动画gif ，否则将失败。
        }
    }

    /**
     * 加载网络圆角图片
     *
     * @param url
     * @param imageView
     */
    public static void loadFilletImage(Context context, String url, ImageView imageView, int circular_bead) {
        loading(context, url, RequestOptions.bitmapTransform(new YcGlideRoundTransform(context, circular_bead)), imageView);
    }

    /**
     * 加载网络圆型图片
     *
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        loading(context, url, RequestOptions.circleCropTransform(), imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int load, int err) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(load)// 正在加载中的图片
                .error(err) // 加载失败的图片
                .circleCrop() //圆形裁剪
                .autoClone(); //自动填充
        loading(context, url, requestOptions, imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param gifId
     * @param imageView
     */
    public static void loadLocal(Context context, int gifId, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(gifId)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载图片带淡入淡出的动画效果
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCrossFade(Context context, String url, ImageView imageView) {
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
     * 清理磁盘的缓存
     *
     * @param context applicationContext  全局上下文
     */
    public static void loadingClean(Context context) {
        try {
            Glide.get(context).clearDiskCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载一个图片为高斯模糊效果
     *
     * @param context applicationContext  全局上下文
     */
    public static void loadingBlurformation(Context context, String url, ImageView imageView) {
        try {
            loading(context, url, RequestOptions.bitmapTransform(new YcGlideBlurformation(context)), imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            Glide.with(context).load(url).apply(requestOptions).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 仅从缓存加载图片

     GlideApp.with(fragment)
     .load(url)
     .onlyRetrieveFromCache(true)
     .into(imageView);
     跳过内存缓存

     GlideApp.with(fragment)
     .load(url)
     .skipMemoryCache(true)
     .into(view);
     跳过磁盘缓存

     GlideApp.with(fragment)
     .load(url)
     .diskCacheStrategy(DiskCacheStrategy.NONE)
     .into(view);
     跳过所有的缓存

     GlideApp.with(fragment)
     .load(url)
     .diskCacheStrategy(DiskCacheStrategy.NONE)
     .skipMemoryCache(true)
     .into(view);
     */


}
