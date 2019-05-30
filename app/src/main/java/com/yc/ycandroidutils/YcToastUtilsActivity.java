package com.yc.ycandroidutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YcToastUtilsActivity extends AppCompatActivity {

    @BindView(R.id.test1)
    TextView mTest1;
    @BindView(R.id.test2)
    TextView mTest2;
    @BindView(R.id.test3)
    TextView mTest3;
    @BindView(R.id.test4)
    TextView mTest4;
    @BindView(R.id.test5)
    TextView mTest5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yc_toast_utils);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test1, R.id.test2, R.id.test3, R.id.test4, R.id.test5, R.id.test6, R.id.test7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test1:
                break;
            case R.id.test2:
                break;
            case R.id.test3:
                break;
            case R.id.test4:
                break;
            case R.id.test5:
                break;
            case R.id.test6:
                break;
            case R.id.test7:
                break;

        }
    }
}
