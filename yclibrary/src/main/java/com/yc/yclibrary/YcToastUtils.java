package com.yc.yclibrary;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *
 * </pre>
 */
public final class YcToastUtils {

    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    @ColorInt
    private static final int ERROR_COLOR = Color.parseColor("#FD4C5B");

    @ColorInt
    private static final int INFO_COLOR = Color.parseColor("#3F51B5");

    @ColorInt
    private static final int SUCCESS_COLOR = Color.parseColor("#388E3C");

    @ColorInt
    private static final int WARNING_COLOR = Color.parseColor("#FFA900");

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    private static Toast currentToast;

    //*******************************************普通 使用ApplicationContext 方法*********************
    /**
     * Toast 替代方法 ：立即显示无需等待
     */
    private static Toast mToast;
    private static long mExitTime;

    //*******************************************常规方法********************************************


    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message) {
        return normal(context, message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, Drawable icon) {
        return normal(context, message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration) {
        return normal(context, message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration, Drawable icon) {
        return normal(context, message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message) {
        return warning(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message, int duration) {
        return warning(context, message, duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_error_outline_white_48dp), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message) {
        return info(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message, int duration) {
        return info(context, message, duration, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_info_outline_white_48dp), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message) {
        return success(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message, int duration) {
        return success(context, message, duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_check_white_48dp), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message) {
        return error(context, message, Toast.LENGTH_SHORT, true);
    }

    //===========================================常规方法============================================

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message, int duration) {
        return error(context, message, duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_clear_white_48dp), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }

    //*******************************************内需方法********************************************

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return custom(context, message, getDrawable(context, iconRes), textColor, tintColor, duration, withIcon, shouldTint);
    }

    //左侧图标
    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return customize(context, message, icon, textColor, tintColor, duration, withIcon, shouldTint, R.layout.toast_layout, false);
    }

    //*************************************** 自定义  上方图标  start *******************************************
    public static Toast showToastAnimator(@NonNull Context context, @NonNull String message, Drawable icon) {
        return customize(context, message, icon, DEFAULT_TEXT_COLOR, -1, Toast.LENGTH_SHORT, true, false, R.layout.customize_toast_layout, true);
    }

    public static Toast showToast(@NonNull Context context, @NonNull String message, Drawable icon) {
        return customize(context, message, icon, DEFAULT_TEXT_COLOR, -1, Toast.LENGTH_SHORT, true, false, R.layout.customize_toast_layout, false);
    }

    public static Toast showToast(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor) {
        return customize(context, message, icon, textColor, -1, Toast.LENGTH_SHORT, true, false, R.layout.customize_toast_layout, false);
    }


    public static Toast showToast(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return customize(context, message, icon, textColor, tintColor, Toast.LENGTH_SHORT, true, false, R.layout.customize_toast_layout, false);
    }

    //*************************************** 自定义  上方图标  end *******************************************

    /**
     * 自定义图标位置
     *
     * @param context
     * @param message
     * @param icon
     * @param textColor
     * @param tintColor
     * @param duration
     * @param withIcon
     * @param shouldTint
     * @param resource
     * @return
     */
    @CheckResult
    public static Toast customize(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint, int resource, boolean iconAnimator) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resource, null);
        final ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = getDrawable(context, R.drawable.toast_frame);
        }
        setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            setBackground(toastIcon, icon);
            if (iconAnimator)
                ObjectAnimator.ofFloat(toastIcon, "rotationY", 0, 360).setDuration(1000).start();
        } else
            toastIcon.setVisibility(View.GONE);

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        currentToast.setGravity(Gravity.BOTTOM, 0, 400);
        return currentToast;
    }

    public static final Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }
    //===========================================内需方法============================================


    //******************************************系统 Toast 替代方法***************************************

    public static final void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(drawable);
        else
            view.setBackgroundDrawable(drawable);
    }

    public static final Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id);
        else
            return context.getResources().getDrawable(id);
    }


    public static boolean doubleClickExit(@NonNull Context context) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            YcToastUtils.normal(context, "再按一次退出");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }
}
