package com.yc.yclibrary.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.WindowManager;

import com.yc.yclibrary.R;


public class YcLoadingView extends ProgressDialog {

    private int layoutResId;

    public YcLoadingView(Context context) {
        super(context);
    }

    public YcLoadingView(Context context, int theme) {
        super(context, theme);
    }

    public YcLoadingView(Context context, int theme, @LayoutRes int layoutResID) {
        super(context, theme);
        layoutResId = layoutResID;
    }

    public void setLayoutResId(@LayoutRes int layoutResID) {
        layoutResId = layoutResID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        if (layoutResId > 0) {
            setContentView(layoutResId);//loading的xml文件
        } else {
            setContentView(R.layout.loading);//loading的xml文件
        }

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {//开启
        super.show();
    }

    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
}
