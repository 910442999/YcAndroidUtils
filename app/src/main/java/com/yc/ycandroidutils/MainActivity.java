package com.yc.ycandroidutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yc.yclibrary.YcConstUtils;
import com.yc.yclibrary.YcGlideUtils;
import com.yc.yclibrary.YcSPUtils;
import com.yc.yclibrary.view.YcNoticeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.notice_view)
    YcNoticeView noticeView;
    @BindView(R.id.text2)
    TextView mText2;
    @BindView(R.id.ImageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
    }

    @OnClick({R.id.text1, R.id.text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                YcSPUtils.getInstance().put("url", "http://img4.imgtn.bdimg.com/it/u=1665207864,746409922&fm=27&gp=0.jpg");
                //                YcGlideUtils.LoadingThumbnails(this, "http://img4.imgtn.bdimg.com/it/u=1665207864,746409922&fm=27&gp=0.jpg", 0.1f,mImageView);
                YcGlideUtils.loadRoundCornerImage(this, YcSPUtils.getInstance().getString("url"), mImageView, 20, R.drawable.placeholder, 300, 300, YcConstUtils.ALL);

                break;
            case R.id.text2:
                Intent intent = new Intent(this, BannerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
