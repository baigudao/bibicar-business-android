package com.bibicar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.bean.UserInfoBean;
import com.bibicar.view.CircleImageView;
import com.bumptech.glide.Glide;

/**
 * Created by jackie on 2017/6/29 15:40.
 * QQ : 971060378
 * Used as : 员工列表的adapter
 */
public class StaffListAdapter extends MyBaseAdapter {

    public StaffListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_staff_list, null);
        }
        UserInfoBean userInfoBean = (UserInfoBean) mDataList.get(position);
        CircleImageView iv_staff_avatar = ViewHolder.getViewById(convertView, R.id.iv_staff_avatar);
        Glide.with(mContext)
                .load(userInfoBean.getAvatar())
                .placeholder(R.drawable.user_photo)
                .into(iv_staff_avatar);
        TextView tv_staff_name = ViewHolder.getViewById(convertView, R.id.tv_staff_name);
        tv_staff_name.setText(userInfoBean.getNickname());
        TextView tv_consult_num = ViewHolder.getViewById(convertView, R.id.tv_consult_num);
        tv_consult_num.setText(userInfoBean.getConsult_num() + "");
        TextView tv_customer_num = ViewHolder.getViewById(convertView, R.id.tv_customer_num);
        tv_customer_num.setText(userInfoBean.getLant_custom_num() + "");
        return convertView;


        //        if (convertView == null) {
        //            convertView = LayoutInflater.from(mContext).inflate(R.layout.dream_car_item, null);
        //        }
        //        DreamCarInfoBean info = mDataList.get(position);
        //        ImageView ivCarIcon = ViewHolder.getViewById(convertView, R.id.ivCarIcon);
        //        TextView tvName = ViewHolder.getViewById(convertView, R.id.tvName);
        //        Utils.loadImage(BaseApplication.getAppContext(), R.drawable.default_bg_ratio_1, info.getBrand_info().getBrand_url(), ivCarIcon);
        //        tvName.setText(info.getBrand_info().getBrand_name() + (TextUtils.isEmpty(info.getSeries_info().getSeries_name()) ? "" : info.getSeries_info().getSeries_name()));
        //        View swipeLayout = convertView.findViewById(R.id.swipeLayout);
        //        swipeLayout.setTag(position);
        //        swipeLayout.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                int position = (int) v.getTag();
        //                if (mOnItemClickListener != null) {
        //                    mOnItemClickListener.onItemClick(MyDreamCarFragment.TYPE_TO_SELECT_DREAM_CAR, position);
        //                }
        //            }
        //        });
        //
        //        View tvSameStyleCar = convertView.findViewById(R.id.tvSameStyleCar);
        //        tvSameStyleCar.setTag(position);
        //        tvSameStyleCar.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                int position = (int) v.getTag();
        //                if (mOnItemClickListener != null) {
        //                    mOnItemClickListener.onItemClick(MyDreamCarFragment.TYPE_TO_SAME_CAR_STYLE, position);
        //                }
        //            }
        //        });
        //
        //        View tvSameStyleUser = convertView.findViewById(R.id.tvSameStyleUser);
        //        tvSameStyleUser.setTag(position);
        //        tvSameStyleUser.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                int position = (int) v.getTag();
        //                if (mOnItemClickListener != null) {
        //                    mOnItemClickListener.onItemClick(MyDreamCarFragment.TYPE_TO_SAME_STYLE_USER, position);
        //                }
        //            }
        //        });
        //        return convertView;
    }
}
