package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by jackie on 2017/6/28 11:35.
 * QQ : 971060378
 * Used as : 员工信息页面
 */
public class StaffInfoFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_staff_info;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("员工信息");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);
        TextView tv_more = (TextView) view.findViewById(R.id.tv_more);
        tv_more.setVisibility(View.VISIBLE);
        tv_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.tv_more:
                ToastUtils.showShort("更多");
                break;
            default:
                break;
        }
    }
}
