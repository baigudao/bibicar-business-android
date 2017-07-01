package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.AppUtils;

/**
 * Created by jackie on 2017/6/27 15:59.
 * QQ : 971060378
 * Used as : 关于BiBiCar商家版
 */
public class AboutBiBiCarFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about_bibicar;
    }

    @Override
    protected void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_version)).setText("当前BiBiCar版本：" + AppUtils.getAppVersionName());
    }

    @Override
    public void onClick(View v) {

    }
}
