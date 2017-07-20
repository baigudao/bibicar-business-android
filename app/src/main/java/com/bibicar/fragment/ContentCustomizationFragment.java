package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by jackie on 2017/6/26 17:15.
 * QQ : 971060378
 * Used as : 内容定制页面
 */
public class ContentCustomizationFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content_customization;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("内容定制");
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);

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
