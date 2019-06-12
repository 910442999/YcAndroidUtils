package com.yc.yclibrary;

import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//暂时去掉log工具类
public class YcLogUtils {

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    private YcLogUtils() {

    }

    public static void v(final Object... contents) {

    }

    public static void vTag(final String tag, final Object... contents) {

    }

    public static void d(final Object... contents) {

    }

    public static void dTag(final String tag, final Object... contents) {

    }

    public static void i(final Object... contents) {

    }

    public static void iTag(final String tag, final Object... contents) {

    }

    public static void w(final Object... contents) {

    }

    public static void wTag(final String tag, final Object... contents) {

    }

    public static void e(final Object... contents) {

    }

    public static void eTag(final String tag, final Object... contents) {

    }

    public static void a(final Object... contents) {

    }

    public static void aTag(final String tag, final Object... contents) {

    }

    public static void file(final Object content) {

    }

    public static void file(@TYPE final int type, final Object content) {

    }

    public static void file(final String tag, final Object content) {

    }

    public static void file(@TYPE final int type, final String tag, final Object content) {

    }

    public static void json(final String content) {

    }

    public static void json(@TYPE final int type, final String content) {

    }

    public static void json(final String tag, final String content) {

    }

    public static void json(@TYPE final int type, final String tag, final String content) {

    }

    public static void xml(final String content) {

    }

    public static void xml(@TYPE final int type, final String content) {

    }

    public static void xml(final String tag, final String content) {

    }

    public static void xml(@TYPE final int type, final String tag, final String content) {

    }
}

