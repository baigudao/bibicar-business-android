package com.bibicar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.bibicar.R;
import com.bibicar.fragment.BaseFragment;
import com.bibicar.fragment.CheHangFragment;
import com.bibicar.fragment.PlatformFragment;
import com.bibicar.fragment.StaffFragment;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/6/3 9:17.
 * QQ : 971060378
 * Used as : MainActivity
 */
public class MainActivity extends BaseActivity {

    private RadioGroup mRg_main;
    private ArrayList<BaseFragment> mBaseFragment;
    private int position;//选中Fragment对应的位置
    private Fragment fromFragment;//上次的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中车行
        mRg_main.check(R.id.rb_che_hang);
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new CheHangFragment());//车行Fragment
        mBaseFragment.add(new StaffFragment());//员工Fragment
        mBaseFragment.add(new PlatformFragment());//平台Fragment
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_che_hang://车行
                    position = 0;
                    //                    tv_title.setText("车行");
                    break;
                case R.id.rb_staff://员工
                    position = 1;
                    //                    tv_title.setText("员工");
                    break;
                case R.id.rb_platform://平台
                    position = 2;
                    //                    tv_title.setText("平台");
                    break;
                default:
                    position = 0;
                    //                    tv_title.setText("车行");
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment toFragment = getFragment();
            //切换Fragment
            switchFragment(fromFragment, toFragment);
        }
    }


    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            fromFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                ft.add(R.id.fl_content, to).commit();
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                ft.show(to).commit();
            }
        }
    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }
}
