package com.bibicar.fragment;

import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by jackie on 2017/6/27 14:41.
 * QQ : 971060378
 * Used as : 设置页面
 */
public class SettingFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("设置");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        view.findViewById(R.id.rl_bundling_phone).setOnClickListener(this);
        view.findViewById(R.id.rl_correct_password).setOnClickListener(this);
        view.findViewById(R.id.rl_correct_avatar).setOnClickListener(this);
        view.findViewById(R.id.rl_clean_cache).setOnClickListener(this);
        view.findViewById(R.id.rl_about_bibicar).setOnClickListener(this);
        view.findViewById(R.id.rl_app_evaluate).setOnClickListener(this);
        view.findViewById(R.id.tv_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.rl_bundling_phone:
                ToastUtils.showShort("绑定手机号");
                break;
            case R.id.rl_correct_password:
                gotoPager(ForgetPasswordFragment.class, null);
                break;
            case R.id.rl_correct_avatar:
                ToastUtils.showShort("修改头像");
                break;
            case R.id.rl_clean_cache:
                ToastUtils.showShort("清理缓存");
                break;
            case R.id.rl_about_bibicar:
                gotoPager(AboutBiBiCarFragment.class,null);
                break;
            case R.id.rl_app_evaluate:
                ToastUtils.showShort("应用评价");
                break;
            case R.id.tv_logout:
                ToastUtils.showShort("退出登录");
                break;
            default:
                break;
        }
    }
}
