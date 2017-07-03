package com.bibicar.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bibicar.R;
import com.bibicar.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/7/3 9:34.
 * QQ : 971060378
 * Used as : 汽车颜色选择的页面
 */
public class CarColorChooseFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_color_choose;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("选择颜色");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //准备数据

        //设置适配器
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(i + "项hehe");
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mContext);
        recyclerViewAdapter.setDataList(strings);
        recyclerViewAdapter.setDataType(RecyclerViewAdapter.DATA_TYPE.TYPE_CHOOSE_CAR_COLOR);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object data, int position) {

            }
        });

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
}
