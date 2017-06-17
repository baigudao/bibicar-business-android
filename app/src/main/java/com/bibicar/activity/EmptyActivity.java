package com.bibicar.activity;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jackie on 2017/6/3 10:17.
 * QQ : 971060378
 * Used as : 空的Activity
 */
public class EmptyActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        String fragmentName = intent.getStringExtra(Constant.FRAGMENT_NAME);
//        BaseFragment fragment = (BaseFragment) Fragment.instantiate(this, fragmentName);
//        Bundle bundle = intent.getExtras();
//        fragment.setArguments(bundle);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment currentFragment = getVisibleFragment();
//        if (currentFragment != null) {
//            ft.hide(currentFragment);
//        }
//        ft.add(R.id.container, fragment, fragmentName);
//        ft.addToBackStack(null);
//        ft.commitAllowingStateLoss();
//        BaseApplication.setCurFragment(fragment);
    }
}
