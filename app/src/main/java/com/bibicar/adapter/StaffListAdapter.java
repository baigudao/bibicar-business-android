package com.bibicar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibicar.R;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_staff_list, null);
        return view;
    }
}
