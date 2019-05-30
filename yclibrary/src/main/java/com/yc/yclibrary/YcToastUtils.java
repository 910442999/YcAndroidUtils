package com.yc.yclibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <pre>
 *
 * </pre>
 */
public final class YcToastUtils {

    private static long mExitTime;

    public static void show(Context context, String text) {
        show(context, text, R.layout.customize_toast_layout, -1);
    }

    public static void show(Context context, @StringRes int strRes, Object... args) {
        show(context, context.getString(strRes, args));
    }

    public static void show(Context context, String text, int style) {
        show(context, text, R.layout.customize_toast_layout, style);
    }

    /**
     * @param context
     * @param text
     * @param resource
     * @param style    -1 则默认 1 2 3 4 5 对应 , 其他传入自定义 R.drawable.toast
     */
    public static void show(Context context, String text, int resource, int style) {
        if (context == null || TextUtils.isEmpty(text)) {
            return;
        }
        //把一个布局变成一个View对象
        LayoutInflater inflater = LayoutInflater.from(context);
        View toast_layout = inflater.inflate(resource, null);
        final ImageView toastIcon = (ImageView) toast_layout.findViewById(R.id.toast_icon);
        final TextView toastTextView = (TextView) toast_layout.findViewById(R.id.toast_text);
        int resIdByType = getResIdByType(style);
        if (resIdByType != -1) {
            toastIcon.setVisibility(View.VISIBLE);
            toastIcon.setImageResource(resIdByType);
        }
        toastTextView.setText(text);
        Toast toast = new Toast(context);
        //把获取到的View对象作为setView的参数
        toast.setView(toast_layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    private static int getResIdByType(int style) {
        if (style == 1) {
            return R.drawable.toast_success;
        } else if (style == 2) {
            return R.drawable.toast_failed;
        } else if (style == 3) {
            return R.drawable.toast_hint;
        } else if (style == 4) {
            return R.drawable.note_del_sucess;
        } else if (style == 5) {
            return R.drawable.note_save_empty;
        }
        return style;
    }

    public static boolean doubleClickExit(@NonNull Context context) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            show(context, "再按一次退出");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }
}
