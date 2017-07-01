package com.bibicar.fragment;

import android.view.View;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/17 11:43.
 * QQ : 971060378
 * Used as : 平台页面
 */
public class PlatformFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_platform;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ll_content_customization).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.ll_content_customization:
        gotoPager(ContentCustomizationFragment.class,null);
        break;
    default:
        break;
}
    }

}
