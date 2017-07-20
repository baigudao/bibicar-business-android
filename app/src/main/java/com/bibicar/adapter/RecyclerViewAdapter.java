package com.bibicar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.bean.CarInfoBean;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by jackie on 2017/7/3 10:01.
 * QQ : 971060378
 * Used as : RecyclerView的适配器
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter<T>.ViewHolder> {

    private Context mContext;
    private List<T> tList;
    private DATA_TYPE dataType;
    private int currentItem;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<T> tList) {
        this.tList = tList;
        notifyDataSetChanged();
    }

    public void setDataType(DATA_TYPE dataType) {
        this.dataType = dataType;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
        notifyDataSetChanged();
    }

    public enum DATA_TYPE {
        TYPE_SAME_BRAND_USER(10),
        TYPE_CHOOSE_CAR_COLOR(0),
        TYPE_STAR_CAR(2);//明星车型

        DATA_TYPE(int value) {
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (dataType == DATA_TYPE.TYPE_CHOOSE_CAR_COLOR) {
            itemView = View.inflate(mContext, R.layout.item_car_color_choose, null);
            //            itemView = View.inflate(mContext, R.layout.item_friend_img, null);
        } else if (dataType == DATA_TYPE.TYPE_STAR_CAR) {
            itemView = View.inflate(mContext, R.layout.item_star_car, null);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPosition(position);
        holder.setViewByType(tList.get(position), dataType);
    }

    @Override
    public int getItemCount() {
        return tList == null ? 0 : tList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private int mPosition;
        private T data;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(data, mPosition);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        mOnItemLongClickListener.onItemLongClick(data, mPosition);
                    }
                    return true;
                }
            });
        }

        private void setPosition(int position) {
            mPosition = position;
        }

        private void setViewByType(T data, DATA_TYPE dataType) {
            this.data = data;
            if (dataType == DATA_TYPE.TYPE_SAME_BRAND_USER) {
                TextView textView = (TextView) mView.findViewById(R.id.tv);
                if (data instanceof ArrayList) {
                    textView.setText((CharSequence) ((ArrayList) data).get(mPosition));
                }
            } else if (dataType == DATA_TYPE.TYPE_STAR_CAR) {
                ImageView iv_car = (ImageView) mView.findViewById(R.id.iv_car);
                TextView tv_car_name = (TextView) mView.findViewById(R.id.tv_car_name);
                TextView tv_car_check_num = (TextView) mView.findViewById(R.id.tv_car_check_num);
                if (data instanceof CarInfoBean) {
                    CarInfoBean carInfoBean = (CarInfoBean) data;
                    Glide.with(mContext)
                            .load("http://img.bibicar.cn/" + carInfoBean.getFiles().get(0).getHash())
                            .placeholder(R.drawable.bibi_default)
                            .bitmapTransform(new RoundedCornersTransformation(mContext, SizeUtils.dp2px(8), 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .into(iv_car);
                    tv_car_name.setText(carInfoBean.getCar_name());
                    tv_car_check_num.setText(carInfoBean.getVisit_num() + "次浏览");
                }
            }
        }
    }


    /**
     * 接口回调三部曲
     * 1，定义接口
     * 2，声明接口变量
     * 3，Setter接口变量
     */
    public interface OnItemClickListener {
        void onItemClick(Object data, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 长按接口回调
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(Object data, int position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setmOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }
}
