package com.bibicar.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.util.Constant;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/6/22 9:08.
 * QQ : 971060378
 * Used as : 登录页面
 */
public class LoginFragment extends BaseFragment {

    private ArrayList<BaseFragment> mBaseFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("登录");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        initFragment();
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new MyLoginPagerAdapter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new AccountPasswordFragment());//账号密码登录的Fragment
        mBaseFragment.add(new PhoneNoPasswordFragment());//手机免密登录的Fragment
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            default:
                break;
        }
    }

    private class MyLoginPagerAdapter extends FragmentPagerAdapter {

        MyLoginPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Constant.TAB_TITLE[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mBaseFragment.get(position);
        }

        @Override
        public int getCount() {
            return mBaseFragment.size();
        }
    }
}
