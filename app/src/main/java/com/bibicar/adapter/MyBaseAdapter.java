package com.bibicar.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.bibicar.util.Constant;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/6/29 15:22.
 * QQ : 971060378
 * Used as : 自定义基类adapter
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected ArrayList<T> mDataList;

    public MyBaseAdapter(Context context) {
        mContext = context;
    }

    /**
     * 设置数据集合
     *
     * @param dataList
     */
    public void setDataList(ArrayList<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 添加数据集合
     *
     * @param dataList
     */
    public void addDataList(ArrayList<T> dataList) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    /**
     * 删除某条数据
     *
     * @param data
     */
    public void removeData(T data) {
        if (mDataList != null) {
            mDataList.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 根据位置删除某条数据
     *
     * @param position
     */
    public void removeData(int position) {
        if (mDataList != null) {
            mDataList.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一条数据
     *
     * @param data
     */
    public void addData(T data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.add(data);
        notifyDataSetChanged();
    }

    /**
     * 得到数据集合
     *
     * @return
     */
    public ArrayList<T> getDataList() {
        return mDataList;
    }

    /**
     * 清除所有数据
     */
    public void clearDataList() {
        if (mDataList != null) {
            mDataList.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 是否能加载更多数据
     *
     * @return
     */
    public boolean isCanLoadMore() {
        int count = getCount();
        return (count != 0) && (count % Constant.PAGE_NUM == 0);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
