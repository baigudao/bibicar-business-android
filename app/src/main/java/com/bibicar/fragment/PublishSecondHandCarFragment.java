package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/29 11:55.
 * QQ : 971060378
 * Used as : 发布二手车页面
 */
public class PublishSecondHandCarFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_publish_second_hand_car;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("我要卖车");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            default:
                break;
        }
    }
}
