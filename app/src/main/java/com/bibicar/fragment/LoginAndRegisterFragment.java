package com.bibicar.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
                //                gotoPager(LoginFragment.class, null);//登录页面
                //                gotoPager(CheHangFragment.class, null);//车行页面
                //                gotoPager(StaffInfoFragment.class, null);
                //                gotoPager(ADCustomizationFragment.class, null);
                //                gotoPager(VideoCustomizationFragment.class, null);
                //                gotoPager(ContentCustomizationFragment.class, null);
                gotoPager(PublishNewCarFragment.class, null);
                //                gotoPager(CarListFragment.class, null);
                //                gotoPager(BrowseInfoFragment.class, null);
                //                gotoPager(PublishSecondHandCarFragment.class, null);
                //                gotoPager(StaffFragment.class, null);
                break;
            case R.id.btn_register:
                gotoPager(RegisterFragment.class, null);
                break;
            case R.id.tv_recommend:
                doStartApp();
                break;
            default:
                break;
        }
    }

    private void doStartApp() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage("com.wiserz.pbibi");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showLong("你的手机尚未安装BiBiCar个人版，赶紧下载安装吧！");
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://android.myapp.com/myapp/detail.htm?apkName=com.wiserz.pbibi"));
            startActivity(viewIntent);
        }
    }
}
