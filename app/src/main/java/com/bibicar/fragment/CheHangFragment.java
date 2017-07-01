package com.bibicar.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
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

    private LinearLayout ll_publish_choose;

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
        tv_sale_num = (TextView) view.findViewById(R.id.tv_sale_num);//在售车辆
        tv_sold_num = (TextView) view.findViewById(R.id.tv_sold_num);//已售车辆
        tv_browse_num = (TextView) view.findViewById(R.id.tv_browse_num);//车行总浏览次数
        tv_consult_num = (TextView) view.findViewById(R.id.tv_consult_num);//总接待咨询人数
        tv_staff = (TextView) view.findViewById(R.id.tv_staff);//员工总数
        tv_car_sum = (TextView) view.findViewById(R.id.tv_car_sum);//车辆总数

        ll_publish_choose = (LinearLayout) view.findViewById(R.id.ll_publish_choose);
        view.findViewById(R.id.iv_new_car_publish).setOnClickListener(this);
        view.findViewById(R.id.iv_second_hand_car_publish).setOnClickListener(this);
        view.findViewById(R.id.iv_cancel_publish).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        OkHttpUtils.post()
                .url(Constant.cheHangInfoUrl)
                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
                .addParams(Constant.SESSION_ID, SPUtils.getInstance().getString(Constant.SESSION_ID))
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
                            int status = jsonObject.optInt("status");
                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
                            if (status == 1) {
                                Glide.with(mContext)
                                        .load(R.drawable.user_photo)
                                        .bitmapTransform(new CropCircleTransformation(mContext))
                                        .placeholder(R.drawable.user_photo)
                                        .into(iv_user);
                                String name = jsonObjectData.optString("name");
                                tv_title.setText(name);
                                String onSale = jsonObjectData.optString("onSale");
                                tv_sale_num.setText(onSale);
                                String sold = jsonObjectData.optString("sold");
                                tv_sold_num.setText(sold);
                                String pv = jsonObjectData.optString("pv");
                                tv_browse_num.setText(pv);
                                String uv = jsonObjectData.optString("uv");
                                tv_consult_num.setText(uv);
                                String employees = jsonObjectData.optString("employees");
                                tv_staff.setText(employees + " 名员工");
                                String cars = jsonObjectData.optString("cars");
                                tv_car_sum.setText(cars + " 辆车辆");
                            } else {
                                String code = jsonObject.optString("code");
                                ToastUtils.showLong("请求数据失败,请检查网络:" + code);
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
                ll_publish_choose.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_new_car_publish:
                ToastUtils.showShort("新车");
                ll_publish_choose.setVisibility(View.GONE);
                break;
            case R.id.iv_second_hand_car_publish:
                ToastUtils.showShort("二手车");
                ll_publish_choose.setVisibility(View.GONE);
                break;
            case R.id.iv_cancel_publish:
                ll_publish_choose.setVisibility(View.GONE);
                break;
            case R.id.iv_user:
                gotoPager(SettingFragment.class, null);
                break;
            default:
                break;
        }
    }
}
