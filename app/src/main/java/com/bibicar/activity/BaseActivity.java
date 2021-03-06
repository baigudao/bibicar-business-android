package com.bibicar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bibicar.BaseApplication;
import com.bibicar.fragment.BaseFragment;
import com.bibicar.util.Constant;

import java.util.List;

/**
 * Created by jackie on 2017/6/3 10:11.
 * QQ : 971060378
 * Used as : Activity的基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 页面跳转，如果返回true,则基类已经处理，否则没有处理
     *
     * @param pagerClass
     * @param bundle
     * @return
     */
    public boolean gotoPager(Class<?> pagerClass, Bundle bundle) {
        if (Activity.class.isAssignableFrom(pagerClass)) { //Activity的情况
            Intent intent = new Intent(this, pagerClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
            return true;
        } else {
            if (BaseFragment.class.isAssignableFrom(pagerClass)) { //Fragment的情况
                String name = pagerClass.getName();
                Intent intent = new Intent(this, EmptyActivity.class);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                intent.putExtra(Constant.FRAGMENT_NAME, name);
                startActivity(intent);
                return true;
            }
            return false;
        }
    }

    /**
     * 返回，如果stack中还有Fragment的话，则返回stack中的fragment，否则 finish当前的Activity
     */
    public void goBack() {
        getSupportFragmentManager().executePendingTransactions();
        int nSize = getSupportFragmentManager().getBackStackEntryCount();
        if (nSize > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 获取当前可见Fragment
     *
     * @return
     */
    public BaseFragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return (BaseFragment) fragment;
        }
        return BaseApplication.getCurFragment();
    }

}
