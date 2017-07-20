package com.bibicar.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.adapter.StaffListAdapter;
import com.bibicar.bean.UserInfoBean;
import com.bibicar.util.Constant;
import com.bibicar.view.PullToRefreshListView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

import static com.bibicar.R.id.tv_title;

/**
 * Created by jackie on 2017/6/17 11:42.
 * QQ : 971060378
 * Used as : 员工页面
 */
public class StaffFragment extends BaseFragment implements PullToRefreshListView.PullToRefreshListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private PullToRefreshListView refresh_listView;
    private boolean mIsRefreshing;
    private boolean mIsLoadMore;
    private int mPage;
    private StaffListAdapter staffListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_staff;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setVisibility(View.GONE);
        ((TextView) view.findViewById(tv_title)).setText("员工列表");
        view.findViewById(R.id.iv_add).setOnClickListener(this);

        refresh_listView = (PullToRefreshListView) view.findViewById(R.id.refresh_listView);
        refresh_listView.setPullRefreshEnable(true);//设置刷新
        refresh_listView.setPullLoadEnable(true);//设置加载更多
        refresh_listView.setPullToRefreshListViewListener(this);
        refresh_listView.setOnItemClickListener(this);//设置item的点击监听，此页面运用adapter的监听
        refresh_listView.setOnItemLongClickListener(this);
        refresh_listView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);//设置子类的可聚焦性 Set the descendant focusability of this view group.
        mIsRefreshing = false;//是否刷新
        mIsLoadMore = false;
        mPage = 1;
    }

    @Override
    protected void initData() {
        super.initData();
        if (!mIsRefreshing) {
            getDataFromNet();
        }
    }

    private void getDataFromNet() {
        if (mIsRefreshing) {
            return;
        }
        mIsRefreshing = true;
        OkHttpUtils.post()
                .url(Constant.staffUrl)
                .addParams(Constant.DEVICE_IDENTIFIER, "85e8c1b3a7e2b3a64296892bf56b3b42")//SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER)
                .addParams(Constant.SESSION_ID, "session578614120f571")//SPUtils.getInstance().getString(Constant.SESSION_ID)
                .addParams(Constant.PAGE, String.valueOf(mPage))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            int code = jsonObject.optInt("code");
                            String message = jsonObject.optString("message");
                            String status = jsonObject.optString("status");
                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
                            if (status.equals("true")) {
                                if (code == 1) {
                                    JSONArray jsonArrayStaffList = jsonObjectData.optJSONArray("staff_list");
                                    if (!mIsLoadMore) {
                                        refresh_listView.stopRefresh();
                                        mIsRefreshing = false;
                                        mIsLoadMore = false;
                                        handlerDataForStaffList(jsonArrayStaffList);//处理员工列表的数据
                                    } else {
                                        refresh_listView.stopLoadMore();
                                        mIsRefreshing = false;
                                        mIsLoadMore = false;
                                        handlerMoreDataForStaffList(jsonArrayStaffList);//处理更多员工列表的数据
                                    }
                                } else if (code == 0) {
                                    ToastUtils.showLong("请求数据失败,请检查网络:" + message);
                                } else {
                                    ToastUtils.showLong("请求数据失败,请检查网络:" + code + " - " + message);
                                }
                            } else {
                                ToastUtils.showLong("请求数据失败,请检查网络");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void handlerMoreDataForStaffList(JSONArray jsonArrayStaffList) {
        ArrayList<UserInfoBean> userInfoBeanArrayList = getStaffListData(jsonArrayStaffList);
        if (userInfoBeanArrayList == null || userInfoBeanArrayList.size() == 0) {
            ToastUtils.showShort("没有更多了");
            return;
        }
        staffListAdapter.addDataList(userInfoBeanArrayList);
    }

    private void handlerDataForStaffList(JSONArray jsonArrayStaffList) {
        ArrayList<UserInfoBean> userInfoBeanArrayList = getStaffListData(jsonArrayStaffList);
        staffListAdapter = new StaffListAdapter(mContext);
        staffListAdapter.setDataList(userInfoBeanArrayList);
        refresh_listView.setAdapter(staffListAdapter);
    }

    private ArrayList<UserInfoBean> getStaffListData(JSONArray jsonArrayStaffList) {
        ArrayList<UserInfoBean> userInfoBeanArrayList = new ArrayList<>();
        int size = jsonArrayStaffList.length();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObjectUserInfo = jsonArrayStaffList.optJSONObject(i).optJSONObject("user_info");
            Gson gson = new Gson();
            UserInfoBean userInfoBean = gson.fromJson(jsonObjectUserInfo.toString(), UserInfoBean.class);
            userInfoBeanArrayList.add(userInfoBean);
        }
        return userInfoBeanArrayList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                gotoPager(AddStaffFragment.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!mIsRefreshing) {
            mPage = 1;
            getDataFromNet();
        }
    }

    @Override
    public void onLoadMore() {
        if (!mIsRefreshing) {
            mIsLoadMore = true;
            ++mPage;
            getDataFromNet();
        }
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
