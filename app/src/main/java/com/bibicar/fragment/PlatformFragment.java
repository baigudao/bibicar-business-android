package com.bibicar.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by jackie on 2017/6/17 11:43.
 * QQ : 971060378
 * Used as : 平台页面
 */
public class PlatformFragment extends BaseFragment {

    private ImageView iv_message;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_platform;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_title)).setText("平台服务");
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_message.setVisibility(View.VISIBLE);
        iv_message.setOnClickListener(this);

        view.findViewById(R.id.ll_content_customization).setOnClickListener(this);
        view.findViewById(R.id.ll_ad_customization).setOnClickListener(this);
        view.findViewById(R.id.ll_video_customization).setOnClickListener(this);
        view.findViewById(R.id.ll_car_insurance).setOnClickListener(this);
        view.findViewById(R.id.ll_car_loan).setOnClickListener(this);
        view.findViewById(R.id.ll_pay_for).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                ToastUtils.showShort("消息");
                break;
            case R.id.ll_content_customization:
                gotoPager(ContentCustomizationFragment.class, null);
                break;
            case R.id.ll_ad_customization:
                gotoPager(ADCustomizationFragment.class, null);
                break;
            case R.id.ll_video_customization:
                gotoPager(VideoCustomizationFragment.class, null);
                break;
            case R.id.ll_car_insurance:
                ToastUtils.showShort("车险服务");
                break;
            case R.id.ll_car_loan:
                ToastUtils.showShort("车贷服务");
                break;
            case R.id.ll_pay_for:
                ToastUtils.showShort("垫资服务");
                break;
            default:
                break;
        }
    }

}
