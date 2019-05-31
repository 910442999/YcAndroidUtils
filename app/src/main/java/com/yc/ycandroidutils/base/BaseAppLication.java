package com.yc.ycandroidutils.base;

import android.app.Application;

import com.yc.yclibrary.YcLogUtils;
import com.yc.yclibrary.YcUtils;

public class BaseAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        YcUtils.getInstance().setLog("自定义log").init(this);
    }
}
