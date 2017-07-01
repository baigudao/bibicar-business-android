package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/28 15:19.
 * QQ : 971060378
 * Used as : 视频定制的页面
 */
public class VideoCustomizationFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_customization;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("视频定制");
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
