package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/28 15:48.
 * QQ : 971060378
 * Used as : 发布新车的页面
 */
public class PublishNewCarFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_publish_new_car;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("我要卖车");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        view.findViewById(R.id.rl_choose_car_type).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.rl_choose_car_type:
                gotoPager(CarBrandChooseFragment.class,null);
                break;
            default:
                break;
        }
    }
}
