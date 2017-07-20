package com.bibicar.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.view.PullToRefreshListView;

import static com.bibicar.R.id.tv_title;


/**
 * Created by jackie on 2017/7/1 10:27.
 * QQ : 971060378
 * Used as : 汽车品牌选择的页面
 */
public class CarBrandChooseFragment extends BaseFragment implements PullToRefreshListView.PullToRefreshListener, AdapterView.OnItemClickListener {

    private PullToRefreshListView lv_pull_to_refresh;
    private boolean mIsRefreshing;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_brand_choose;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(tv_title)).setText("选择车系");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        lv_pull_to_refresh = (PullToRefreshListView) view.findViewById(R.id.lv_pull_to_refresh);
        lv_pull_to_refresh.setPullRefreshEnable(true);//设置刷新
        lv_pull_to_refresh.setPullLoadEnable(true);//设置加载更多
        lv_pull_to_refresh.setPullToRefreshListViewListener(this);
        lv_pull_to_refresh.setOnItemClickListener(this);//设置item的点击监听，此页面运用adapter的监听
        lv_pull_to_refresh.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);//设置子类的可聚焦性 Set the descendant focusability of this view group.
        mIsRefreshing = false;//是否刷新
    }

    @Override
    protected void initData() {
        super.initData();
        //        OkHttpUtils.post()
        //                .url(Constant.carBrandUrl)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
        //                .build()
        //                .execute(new StringCallback() {
        //                    @Override
        //                    public void onError(Call call, Exception e, int id) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onResponse(String response, int id) {
        //                        JSONObject jsonObject = null;
        //                        try {
        //                            jsonObject = new JSONObject(response);
        //                            int status = jsonObject.optInt("status");
        //                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
        //                            if (status == 1) {
        //                                JSONObject jsonObjectBrandList = jsonObjectData.optJSONObject("brand_list");
        //
        //                                String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        //                                for (String string : strings) {
        //                                    JSONArray jsonArray = jsonObjectBrandList.optJSONArray(string);
        //                                    Gson gson = new Gson();
        //                                    ArrayList<BrandBean> brandBeanArrayList = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<BrandBean>>() {
        //                                    }.getType());
        //                                    for (BrandBean brandBean : brandBeanArrayList) {
        //                                        brandBean.getBrand_name();
        //
        //
        //                                    }
        //                                }
        //                            } else {
        //                                String code = jsonObject.optString("code");
        //                                String msg = jsonObjectData.optString("msg");
        //                                ToastUtils.showLong("请求数据失败,请检查网络:" + code + "-" + msg);
        //                            }
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                    }
        //                });
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
