package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by jackie on 2017/6/28 14:54.
 * QQ : 971060378
 * Used as : 广告定制的页面
 */
public class ADCustomizationFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ad_customization;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("广告定制");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        view.findViewById(R.id.btn_start_customization).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.btn_start_customization:
                ToastUtils.showShort("开始定制");
                break;
            default:
                break;
        }
    }
}
