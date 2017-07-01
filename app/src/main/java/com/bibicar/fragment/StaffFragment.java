package com.bibicar.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.adapter.StaffListAdapter;
import com.bibicar.util.Constant;
import com.bibicar.view.PullToRefreshListView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by jackie on 2017/6/17 11:42.
 * QQ : 971060378
 * Used as : 员工页面
 */
public class StaffFragment extends BaseFragment implements PullToRefreshListView.PullToRefreshListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private PullToRefreshListView refresh_listView;
    private boolean mIsRefreshing;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_staff;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("员工列表");
        view.findViewById(R.id.iv_add).setOnClickListener(this);

        refresh_listView = (PullToRefreshListView) view.findViewById(R.id.refresh_listView);
        refresh_listView.setPullRefreshEnable(true);//设置刷新
        refresh_listView.setPullLoadEnable(true);//设置加载更多
        refresh_listView.setPullToRefreshListViewListener(this);
        refresh_listView.setOnItemClickListener(this);//设置item的点击监听，此页面运用adapter的监听
        refresh_listView.setOnItemLongClickListener(this);
        refresh_listView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);//设置子类的可聚焦性 Set the descendant focusability of this view group.
        mIsRefreshing = false;//是否刷新


        //以下代码为测试
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            strings.add("hehe");
        }
        StaffListAdapter staffListAdapter = new StaffListAdapter(mContext);
        staffListAdapter.setDataList(strings);
        refresh_listView.setAdapter(staffListAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("员工列表的参数>>>", SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER) + "|||" + SPUtils.getInstance().getString(Constant.SESSION_ID));
        OkHttpUtils.post()
                .url(Constant.staffListUrl)
                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
                .addParams(Constant.SESSION_ID, SPUtils.getInstance().getString(Constant.SESSION_ID))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("员工列表的数据>>>", response);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.iv_add:
                gotoPager(AddStaffFragment.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refresh_listView.stopRefresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refresh_listView.stopLoadMore();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showShort("点击了第" + position + "个");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showShort("长按了第" + position + "个");
        return true;
    }
}
