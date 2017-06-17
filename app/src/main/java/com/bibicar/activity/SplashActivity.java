package com.bibicar.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bibicar.R;
import com.blankj.utilcode.util.BarUtils;

/**
 * Created by jackie on 2017/6/17 14:18.
 * QQ : 971060378
 * Used as : 启动页面
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        BarUtils.hideStatusBar(this);
        setContentView(R.layout.fragment_login_and_register);



//        //如果用户没有登录，就进入登录注册页面
//        Intent intent = new Intent(SplashActivity.this, EmptyActivity.class);
//        intent.putExtra(Constant.FRAGMENT_NAME, LoginAndRegisterFragment.class.getName());
    }
}
