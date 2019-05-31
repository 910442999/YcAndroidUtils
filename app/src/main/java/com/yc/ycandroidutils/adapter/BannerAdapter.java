package com.yc.ycandroidutils.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yc.ycandroidutils.R;
import com.yc.yclibrary.YcConstUtils;
import com.yc.yclibrary.YcGlideUtils;

import java.util.List;

/**
 * Created by yc on 2018/8/30.
 */

public class BannerAdapter extends PagerAdapter {
    private List<String> mStringList;
    private Context mContext;

    public BannerAdapter(Context context, List<String> list) {
        mContext = context;
        mStringList = list;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager, null);
        ImageView itemImage = (ImageView) view.findViewById(R.id.item_image);
        YcGlideUtils.loadRoundCornerImage(mContext, mStringList.get(position), itemImage, 20, R.drawable.placeholder, 300, 300, YcConstUtils.ALL);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

