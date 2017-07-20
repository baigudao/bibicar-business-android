package com.bibicar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.bibicar.BaseApplication;
import com.bibicar.R;
import com.bibicar.fragment.BaseFragment;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.BarUtils;

/**
 * Created by jackie on 2017/6/3 10:17.
 * QQ : 971060378
 * Used as : 空的Activity
 */
public class EmptyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setTransparentStatusBar(EmptyActivity.this);//设置状态栏为透明
        setContentView(R.layout.activity_empty);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String fragmentName = intent.getStringExtra(Constant.FRAGMENT_NAME);
        BaseFragment fragment = (BaseFragment) Fragment.instantiate(this, fragmentName);
        Bundle bundle = intent.getExtras();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getVisibleFragment();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        ft.add(R.id.fl_container, fragment, fragmentName);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        BaseApplication.setCurFragment(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getVisibleFragment() != null) {
                getVisibleFragment().goBack();
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
