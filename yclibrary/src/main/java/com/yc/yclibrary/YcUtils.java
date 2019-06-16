package com.yc.yclibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yc.yclibrary.interfaces.OnSimpleClickListener;

import java.net.URL;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Yc工具类 YcUtils
 * getContext 获取 Context
 * dip2px   dip转px
 * dp2px    dp转px
 * px2dip   px转dip
 * px2dp    px转dp
 * sp2px     sp转px
 * px2sp     px转sp
 * getRsString      获取string 资源文件
 * getRsDrawable    获取 Drawable 资源文件
 * getRsColor       获取 Color 资源文件
 * delayToDo        延迟任务
 * countDown        倒计时
 * setEdTwoDecimal  设置Edittext 首位小数点自动加零，最多两位小数
 * setEdType        设置光标输入位置
 * getSystemLanguage 获取系统语言
 * getAppLanguage   获取App语言
 * changeAppLanguage 更改App语言
 * stringFilter     只允许数字和汉字
 * setEditNumberAuto  输入框控件
 * getWebIconUrl  获取连接中的ico图片
 */
public class YcUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        YcUtils.context = context.getApplicationContext();
    }

    /**
     * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
     * <p>
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先调用init()方法");
    }


    /**
     * dip转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dip2px(float dpValue) {
        return dp2px(dpValue);
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param pxValue px值
     * @return dip值
     */
    public static int px2dip(float pxValue) {
        return px2dp(pxValue);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    //-------------------------------------获取资源文件 start---------------------------------------------------------

    /**
     * 获取string 资源文件
     *
     * @param id
     * @return
     */
    public static String getRsString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取 Drawable 资源文件
     *
     * @param id
     * @return
     */
    public static Drawable getRsDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取 Color 资源文件
     *
     * @param id
     * @return
     */
    public static int getRsColor(Context context, int id) {
        return context.getResources().getColor(id);
    }
    //-------------------------------------获取资源文件 end---------------------------------------------------------


    //----------------------------------------------------------------------------------------------延时任务封装 start
    public static void delayToDo(long delayTime, final OnSimpleClickListener onSimpleClickListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //execute the task
                onSimpleClickListener.onCallBack("", "");
            }
        }, delayTime);
    }
    //----------------------------------------------------------------------------------------------延时任务封装 end

    /**
     * 倒计时
     *
     * @param textView 控件
     * @param waitTime 倒计时总时长
     * @param interval 倒计时的间隔时间
     * @param hint     倒计时完毕时显示的文字
     * @param prompt   提示文案 如:已发送10妙 , 剩下 10妙
     * @param unit     提示文案单位 如:10妙 ,  10s
     */
    public static void countDown(final TextView textView, final String prompt, final String unit, long waitTime, long interval, final String hint) {
        textView.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(waitTime, interval) {

            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format(prompt + "%d" + unit, millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(hint);
            }
        };
        timer.start();
    }

    /**
     * Edittext 首位小数点自动加零，最多两位小数
     *
     * @param editText
     */
    public static void setEdTwoDecimal(EditText editText) {
        setEdDecimal(editText, 2);
    }

    /**
     * 设置光标输入位置
     *
     * @param editText
     */
    public static void setEdType(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void
            beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void
            onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = editText.getText().toString();
                String str = stringFilter(editable);
                if (!editable.equals(str)) {
                    editText.setText(str);
                    //设置新的光标所在位置
                    editText.setSelection(str.length());
                }
            }

            @Override
            public void
            afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * // 只允许数字和汉字
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {

        String regEx = "[^0-9\u4E00-\u9FA5]";//正则表达式
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void setEdDecimal(EditText editText, int count) {
        if (count < 0) {
            count = 0;
        }

        count += 1;

        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

        //设置字符过滤
        final int finalCount = count;
        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (".".contentEquals(source) && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == finalCount) {
                        return "";
                    }
                }

                if (dest.toString().equals("0") && source.equals("0")) {
                    return "";
                }

                return null;
            }
        }});
    }

    /**
     * @param editText       输入框控件
     * @param number         位数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是否从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumberAuto(final EditText editText, final int number, final boolean isStartForZero) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setEditNumber(editText, number, isStartForZero);
                }
            }
        });
    }

    /**
     * @param editText       输入框控件
     * @param number         位数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是否从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumber(EditText editText, int number, boolean isStartForZero) {
        StringBuilder s = new StringBuilder(editText.getText().toString());
        StringBuilder temp = new StringBuilder();

        int i;
        for (i = s.length(); i < number; ++i) {
            s.insert(0, "0");
        }
        if (!isStartForZero) {
            for (i = 0; i < number; ++i) {
                temp.append("0");
            }

            if (s.toString().equals(temp.toString())) {
                s = new StringBuilder(temp.substring(1) + "1");
            }
        }
        editText.setText(s.toString());
    }


    /**
     * 获取App语言
     *
     * @param context 必须传应用的上下文否则无法获取资源文件
     * @return
     */
    public static String getAppLanguage(Context context) {
        return getAppLanguage(context, null);
    }

    /**
     * 获取App语言
     *
     * @param context 必须传应用的上下文否则无法获取资源文件
     * @param country 国家
     * @return
     */
    public static String getAppLanguage(Context context, String country) {
        try {
            Locale locale;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = context.getResources().getConfiguration().getLocales().get(0);
            } else {
                locale = context.getResources().getConfiguration().locale;
            }
            //或者仅仅使用 locale = Locale.getDefault(); 不需要考虑接口
            // deprecated(弃用)问题
            if (TextUtils.isEmpty(country)) {
                return locale.getLanguage();
            } else {
                return locale.getLanguage() + "-" + locale.getCountry();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取系统语言
     *
     * @return
     */
    public static String getSystemLanguage() {
        try {
            String language = Locale.getDefault().getLanguage();
            return language;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 更改App语言
     *
     * @param context  必须传应用的上下文否则无法获取资源文件
     * @param language
     */
    public static void changeAppLanguage(Context context, String language) {
        try {
            // 本地语言设置
            Resources res = context.getResources();
            Locale myLocale = new Locale(language);
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接中的ico图片
     *
     * @param mUrl
     * @return
     */
    public static String getWebIconUrl(String mUrl) {
        String webUrl = null;
        try {
            URL url = new URL(mUrl);
            if (url != null) {
                webUrl = url.getProtocol() + "://" + url.getHost() + "/favicon.ico";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webUrl;
    }
}
