package com.bibicar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibicar.activity.BaseActivity;
import com.jph.takephoto.app.TakePhotoFragment;

/**
 * Created by jackie on 2017/6/3 10:15.
 * QQ : 971060378
 * Used as : Fragment的基类
 */
public abstract class BaseFragment extends TakePhotoFragment implements View.OnClickListener {

    /**
     * 上下文
     */
    protected Context mContext;//其实就是MainActivity

    /**
     * 当该Fragment被创建的时候调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * 当创建视图时被调用
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getLayoutId(), null);
    }

    /**
     * 强制子类重写，得到布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 视图创建好之后调用
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    /**
     * 强制子类重写，初始化试图
     *
     * @return
     */
    protected abstract void initView(View view);

    /**
     * 当界面被创建时调用
     * 通常用来加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected void initData() {
    }

    /**
     * 跳转到新的页面
     *
     * @param pagerClass
     * @param bundle
     */
    public void gotoPager(final Class<?> pagerClass, final Bundle bundle) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).gotoPager(pagerClass, bundle);
        }
    }

    /**
     * 返回，如果stack中还有Fragment的话，则返回stack中的fragment，否则 finish当前的Activity
     */
    public void goBack() {
        ((BaseActivity) getActivity()).goBack();
    }

}
