package com.yc.ycandroidutils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.yc.ycandroidutils.adapter.BannerAdapter;
import com.yc.yclibrary.YcUtils;
import com.yc.yclibrary.view.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    List<String> mStringList;
    private LoopViewPager looviewpager;
    private LinearLayout mLlPoint;
    private int mCurrentIndex = 0;//当前小圆点的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initBanner();
    }

    private void initBanner() {
        mStringList = new ArrayList<>();
        mStringList.add("http://img.zcool.cn/community/01f749595c4d34a8012193a3023d57.jpg");
        mStringList.add("http://img.zcool.cn/community/01a2c359382ed8a8012193a3ef9bf8.jpg@1280w_1l_2o_100sh.jpg");
        mStringList.add("http://img.zcool.cn/community/01836c57b93c2a0000012e7ef5384d.jpg@2o.jpg");
        mStringList.add("http://img.zcool.cn/community/01592157635bbc0000018c1baf7d9a.jpg@1280w_1l_2o_100sh.jpg");
        mStringList.add("http://img.zcool.cn/community/012daa59ad0aaca801211d25fb3a0e.png@1280w_1l_2o_100sh.png");
        looviewpager = (LoopViewPager) findViewById(R.id.looviewpager);
        mLlPoint = (LinearLayout) findViewById(R.id.ll_point);
        looviewpager.setAdapter(new BannerAdapter(this, mStringList));
        looviewpager.setOffscreenPageLimit(3);
        initBannerPoint(mStringList);
        looviewpager.setPageTransformer(true, new ViewPager.PageTransformer() {
            float scale = 0.9f;

            @Override
            public void transformPage(View page, float position) {
                if (position >= 0 && position <= 1) {
                    page.setScaleY(scale + (1 - scale) * (1 - position));
                } else if (position > -1 && position < 0) {
                    page.setScaleY(1 + (1 - scale) * position);
                } else {
                    page.setScaleY(scale);
                }
            }
        });
        //设置监听
        looviewpager.setOnPageChangeListener(getListener());
    }
    /***
     * 初始化点
     * 可以根据图片多少自动增加点
     */
    private void initBannerPoint(List<String> stringList) {

        View view;
        for (int i = 0; i < stringList.size(); i++) {
            view = new View(this);
            view.setBackgroundResource(R.drawable.banner_point_selector);//自己在Drawable文件夹目录下写的xml文件
            view.setEnabled(false);
            LinearLayout.LayoutParams bannerParamsL = new LinearLayout.LayoutParams(YcUtils.dp2px(15), YcUtils.dp2px(2));
            //设置间隔
            if (i != 0) {
                bannerParamsL.leftMargin = YcUtils.dp2px(5);  //如果不是第一个，就设置左边距为10dp
            }
            mLlPoint.addView(view, bannerParamsL);//ll是在xml文件中装原点的LinearLayout的id；
        }

        //初始化第一个小圆点 (mCurrentIndex默认0)
        mCurrentIndex = looviewpager.getRealItem();
        mLlPoint.getChildAt(mCurrentIndex).setEnabled(true);
    }
    /***
     * viewpager监听
     *
     * @return
     */
    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.e("tag", "当前" + mCurrentIndex + "选中" + position + "");
                mLlPoint.getChildAt(mCurrentIndex).setEnabled(false);
                mLlPoint.getChildAt(position).setEnabled(true);
                mCurrentIndex = position;         //mCurrentIndex指的是当前的小圆点的位置


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        looviewpager.autoLoop(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        looviewpager.autoLoop(false);
    }
}
