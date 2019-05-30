package com.yc.ycandroidutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yc.yclibrary.YcGlideUtils;
import com.yc.yclibrary.YcSPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    TextView mText1;
    @BindView(R.id.text2)
    TextView mText2;
    @BindView(R.id.ImageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //测试ios
    }

    @OnClick({R.id.text1, R.id.text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                YcSPUtils.getInstance().put("name", "名字");
                YcGlideUtils.loadFilletImage(this, "http://img4.imgtn.bdimg.com/it/u=1665207864,746409922&fm=27&gp=0.jpg", mImageView, 10,R.drawable.placeholder,R.drawable.placeholder,-1);
                //                YcGlideUtils.LoadingThumbnails(this, "http://img4.imgtn.bdimg.com/it/u=1665207864,746409922&fm=27&gp=0.jpg", 0.1f,mImageView);
                break;
            case R.id.text2:

                break;
        }
    }
}
