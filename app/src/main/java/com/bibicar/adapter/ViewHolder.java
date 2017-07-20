package com.bibicar.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by jackie on 2017/7/19 17:32.
 * QQ : 971060378
 * Used as : xxx
 */
public class ViewHolder {

    public static <T extends View> T getViewById(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (null == viewHolder) {
            viewHolder = new SparseArray<>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (null == childView) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
