package com.bibicar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibicar.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by jackie on 2017/6/29 13:58.
 * QQ : 971060378
 * Used as : 自定义下拉刷新，上拉加载更多的头部
 */
public class PullToRefreshListViewHeader extends LinearLayout {

    private LinearLayout mContainer;
    private ImageView ivCar;
    private TextView tvTime;
    private int mState = STATE_NORMAL;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;


    public PullToRefreshListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public PullToRefreshListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.customization_listview_header, null);

        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);
        ivCar = (ImageView) findViewById(R.id.giv_customization_listView_header);
        tvTime = (TextView) findViewById(R.id.tv_customization_listView_header_time);

        try {
            // 如果加载的是gif动图，第一步需要先将gif动图资源转化为GifDrawable
            // 将gif图资源转化为GifDrawable
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.refresh_gif);
            // gif1加载一个动态图gif
            ivCar.setImageDrawable(gifDrawable);
            //tvTime.setText("最后更新:"+Utils.getTimeStr(context, BaseApplication.getmLoading_time()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setState(int state) {
        if (state == mState)
            return;
        ivCar.setVisibility(View.VISIBLE);
        switch (state) {
            case STATE_NORMAL:
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                }
                break;
            case STATE_REFRESHING:
                break;
            default:
                break;
        }

        mState = state;
    }

    public void setVisibleHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        return mContainer.getHeight();
    }
}