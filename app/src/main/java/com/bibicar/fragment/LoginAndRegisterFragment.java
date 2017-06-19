package com.bibicar.fragment;

import android.view.View;

import com.bibicar.R;
import com.blankj.utilcode.util.ToastUtils;

import static com.bibicar.R.id.btn_login;

/**
 * Created by jackie on 2017/6/17 14:25.
 * QQ : 971060378
 * Used as : 登录注册页面
 */
public class LoginAndRegisterFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        //        BarUtils.hideStatusBar(this);
        return R.layout.fragment_login_and_register;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(btn_login).setOnClickListener(this);
        view.findViewById(R.id.btn_register).setOnClickListener(this);
        view.findViewById(R.id.tv_recommend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case btn_login:
                ToastUtils.showLong("登录");
                break;
            case R.id.btn_register:
                gotoPager(RegisterFragment.class,null);
                break;
            case R.id.tv_recommend:
                //                Toast.makeText(mContext, "bibibc", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
