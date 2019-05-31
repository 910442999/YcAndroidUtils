package com.yc.yclibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.yc.yclibrary.R;

import java.util.List;

/**
 * 轮播公告Veiw
 */
public class YcNoticeView extends ViewFlipper implements View.OnClickListener {
    private List<String> mNotices;
    float size = 26f;
    int color = Color.GRAY;
    int lines = 1;
    int gravity = Gravity.LEFT;
    private int mInterval = 3000;
    private int mDuration = -1;

    public YcNoticeView(Context context) {
        super(context);
    }

    public YcNoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YcNoticeView);
        size = a.getDimension(R.styleable.YcNoticeView_nvTextSize, size);
        gravity = a.getInteger(R.styleable.YcNoticeView_nvTextGravity, gravity);
        color = a.getColor(R.styleable.YcNoticeView_nvTextColor, color);
        lines = a.getInteger(R.styleable.YcNoticeView_nvTextMaxLines, lines);
        mInterval = a.getInteger(R.styleable.YcNoticeView_nvInterval, mInterval);
        mDuration = a.getInteger(R.styleable.YcNoticeView_nvDuration, mDuration);
        // 轮播间隔时间为3s
        setFlipInterval(mInterval);
        // 内边距5dp
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        // 设置enter和leave动画
        Animation animationIn = AnimationUtils.loadAnimation(getContext(), R.anim.notify_in);
        Animation animationOut = AnimationUtils.loadAnimation(getContext(), R.anim.notify_out);
        if (mDuration != -1) {
            animationIn.setDuration(mDuration);
            animationOut.setDuration(mDuration);
        }
        setInAnimation(animationIn);
        setOutAnimation(animationOut);
    }

    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
    public void addNotice(List<String> notices) {
        removeAllViews();
        mNotices = notices;
        for (int i = 0; i < notices.size(); i++) {
            // 根据公告内容构建一个Tetview
            String notice = notices.get(i);
            TextView textView = new TextView(getContext());
            textView.setSingleLine();
            textView.setText(notice);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            textView.setMaxLines(lines);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(color);
            textView.setGravity(Gravity.CENTER_VERTICAL | gravity);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(i);
            textView.setOnClickListener(this);
            // 添加到ViewFlipper
            YcNoticeView.this.addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        String notice = mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNotieClick(position, notice);
        }
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNotieClick(int position, String notice);
    }

    private OnNoticeClickListener mOnNoticeClickListener;

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }
}
