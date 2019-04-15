package com.yc.yclibrary;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * isSoftInputVisible                : 判断软键盘是否可见
 * toggleSoftInput                   : 切换键盘显示与否状态
 * showSoftInput                     : 显示软键盘
 * hideSoftInput                     : 隐藏软键盘
 */
public class YcKeyboardUtils {

    private YcKeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取输入法打开的状态
     *
     * @param activity The activity.
     * @return {@code true}: yes打开<br>{@code false}: no关闭
     */
    public static boolean isSoftInputVisible(final Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();//若返回true，则表示输入法打开
    }

    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     */
    public static void toggleSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制显示
     *
     * @param activity The activity.
     */
    public static void showSoftInput(final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        showSoftInput(activity, view);
    }

    /**
     * 强制显示键盘
     *
     * @param activity
     * @param view     为接受软键盘输入的视图
     */
    public static void showSoftInput(final Activity activity, final View view) {
        showSoftInput(activity,view,InputMethodManager.SHOW_FORCED);
    }
  /**
     * 强制显示键盘
     *
     * @param activity
     * @param view     为接受软键盘输入的视图
     */
    public static void showSoftInput(final Activity activity, final View view,int inputMethod) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null)
            return;
        imm.showSoftInput(view, inputMethod);
    }

    /**
     * 显示键盘
     *
     * @param view 为接受软键盘输入的视图
     */
    public static void showSoftInput(final View view) {
        showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 显示键盘
     *
     * @param inputMethod 模式  如 SHOW_FORCED 强制
     * @param view        为接受软键盘输入的视图
     */
    public static void showSoftInput(final View view, int inputMethod) {
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, 0);
        if (inputManager == null)
            return;
        inputManager.showSoftInput(view, inputMethod);
    }

    /**
     * 强制隐藏键盘
     *
     * @param activity
     */
    public static void hideSoftInput(final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        hideSoftInput(activity, view);
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     * @param view     为接受软键盘输入的视图
     */
    public static void hideSoftInput(final Activity activity, final View view) {
        hideSoftInput(activity,view,InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
 /**
     * 隐藏键盘
     *
     * @param activity
     * @param view     为接受软键盘输入的视图
     */
    public static void hideSoftInput(final Activity activity, final View view ,int inputMethod) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null)
            return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), inputMethod);
    }

    /**
     * 隐藏键盘
     *
     * @param view     为接受软键盘输入的视图
     */
    public static void hideSoftInput(final View view) {
        hideSoftInput(view,InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
    /**
     * 隐藏键盘
     *
     * @param view     为接受软键盘输入的视图
     */
    public static void hideSoftInput(final View view ,int inputMethod) {
        InputMethodManager imm =
                (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null)
            return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), inputMethod);
    }
}