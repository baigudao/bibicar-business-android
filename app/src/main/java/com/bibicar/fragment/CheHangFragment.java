package com.bibicar.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.adapter.RecyclerViewAdapter;
import com.bibicar.bean.CarInfoBean;
import com.bibicar.bean.UserInfoBean;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by jackie on 2017/6/17 11:38.
 * QQ : 971060378
 * Used as : 车行Fragment
 */
public class CheHangFragment extends BaseFragment {

    private ImageView iv_user;
    private TextView tv_title;
    private TextView tv_sale_num;
    private TextView tv_sold_num;
    private TextView tv_browse_num;
    private TextView tv_consult_num;
    private TextView tv_staff;
    private TextView tv_car_sum;

    private TextView tv_check_all;

    private LinearLayout ll_empty;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_che_hang;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setVisibility(View.GONE);
        iv_user = (ImageView) view.findViewById(R.id.iv_user);
        iv_user.setVisibility(View.VISIBLE);
        iv_user.setOnClickListener(this);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        view.findViewById(R.id.iv_add).setOnClickListener(this);

        view.findViewById(R.id.rl_car_sale).setOnClickListener(this);
        view.findViewById(R.id.rl_car_sold).setOnClickListener(this);
        view.findViewById(R.id.rl_staff_sum).setOnClickListener(this);
        view.findViewById(R.id.rl_car_sum).setOnClickListener(this);

        tv_sale_num = (TextView) view.findViewById(R.id.tv_sale_num);//在售车辆
        tv_sold_num = (TextView) view.findViewById(R.id.tv_sold_num);//已售车辆
        tv_browse_num = (TextView) view.findViewById(R.id.tv_browse_num);//车行总浏览次数
        tv_consult_num = (TextView) view.findViewById(R.id.tv_consult_num);//总接待咨询人数
        tv_staff = (TextView) view.findViewById(R.id.tv_staff);//员工总数
        tv_car_sum = (TextView) view.findViewById(R.id.tv_car_sum);//车辆总数

        tv_check_all = (TextView) view.findViewById(R.id.tv_check_all);
        tv_check_all.setOnClickListener(this);

        ll_empty = (LinearLayout) view.findViewById(R.id.ll_empty);
        view.findViewById(R.id.tv_start_post).setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        super.initData();
        OkHttpUtils.post()
                .url(Constant.homeUrl)
                .addParams(Constant.DEVICE_IDENTIFIER, "85e8c1b3a7e2b3a64296892bf56b3b42")//SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER)
                .addParams(Constant.SESSION_ID, "session578614120f571")//SPUtils.getInstance().getString(Constant.SESSION_ID)
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
                                    JSONObject jsonObjectUserInfo = jsonObjectData.optJSONObject("user_info");
                                    Gson gson = new Gson();
                                    UserInfoBean userInfoBean = gson.fromJson(jsonObjectUserInfo.toString(), UserInfoBean.class);
                                    if (EmptyUtils.isNotEmpty(userInfoBean)) {
                                        Glide.with(mContext)
                                                .load(userInfoBean.getAvatar())
                                                .placeholder(R.drawable.user_photo)
                                                .into(iv_user);
                                        tv_title.setText(userInfoBean.getNickname());
                                    }

                                    String selling_car_count = jsonObjectData.optString("selling_car_count");
                                    tv_sale_num.setText(selling_car_count);
                                    String selled_car_count = jsonObjectData.optString("selled_car_count");
                                    tv_sold_num.setText(selled_car_count);
                                    String company_visit_num = jsonObjectData.optString("company_visit_num");
                                    tv_browse_num.setText(company_visit_num);
                                    String company_receive_num = jsonObjectData.optString("company_receive_num");
                                    tv_consult_num.setText(company_receive_num);
                                    String company_staff_num = jsonObjectData.optString("company_staff_num");
                                    tv_staff.setText(company_staff_num + " 名员工");
                                    String company_car_num = jsonObjectData.optString("company_car_num");
                                    tv_car_sum.setText(company_car_num + " 辆车辆");

                                    JSONArray jsonArrayCarList = jsonObjectData.optJSONArray("car_list");
                                    handlerDataForCarList(jsonArrayCarList);//处理车辆列表数据
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                ToastUtils.showShort("发布");
                break;
            case R.id.iv_user:
                gotoPager(SettingFragment.class, null);
                break;
            case R.id.tv_start_post:
                ToastUtils.showShort("开始上传");
                break;
            case R.id.tv_check_all:
                ToastUtils.showShort("查看全部");
                break;
            case R.id.rl_car_sale:
                ToastUtils.showShort("在售车辆");
                break;
            case R.id.rl_car_sold:
                ToastUtils.showShort("已售车辆");
                break;
            case R.id.rl_staff_sum:
                ToastUtils.showShort("员工");
                break;
            case R.id.rl_car_sum:
                ToastUtils.showShort("车辆");
                break;
            default:
                break;
        }
    }

    private void handlerDataForCarList(JSONArray jsonArrayCarList) {
        int size = jsonArrayCarList.length();
        if (size != 0 && EmptyUtils.isNotEmpty(jsonArrayCarList)) {
            recyclerView.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
            tv_check_all.setVisibility(View.VISIBLE);

            ArrayList<CarInfoBean> carInfoBeanArrayList = getCarListData(jsonArrayCarList);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mContext);
            recyclerViewAdapter.setDataList(carInfoBeanArrayList);
            recyclerViewAdapter.setDataType(RecyclerViewAdapter.DATA_TYPE.TYPE_STAR_CAR);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(recyclerViewAdapter);

            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Object data, int position) {
                    CarInfoBean carInfoBean = (CarInfoBean) data;
                    ToastUtils.showShort("position= " + position + "name= " + carInfoBean.getCar_name());
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            ll_empty.setVisibility(View.VISIBLE);
            tv_check_all.setVisibility(View.INVISIBLE);
        }
    }

    private ArrayList<CarInfoBean> getCarListData(JSONArray jsonArrayCarList) {
        ArrayList<CarInfoBean> carInfoBeanArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArrayCarList.length(); i++) {
            JSONObject jsonObjectTotalCarInfo = jsonArrayCarList.optJSONObject(i).optJSONObject("car_info");
            JSONObject jsonObjectCarInfo = jsonObjectTotalCarInfo.optJSONObject("car_info");
            Gson gson = new Gson();
            CarInfoBean carInfoBean = gson.fromJson(jsonObjectCarInfo.toString(), CarInfoBean.class);
            carInfoBeanArrayList.add(carInfoBean);
        }
        return carInfoBeanArrayList;
    }
}
