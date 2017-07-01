package com.bibicar.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/28 16:47.
 * QQ : 971060378
 * Used as : Car列表的页面
 */
public class CarListFragment extends BaseFragment implements SearchView.OnQueryTextListener {

    private SearchView sv;
    private ListView lv;
    // 自动完成的列表
    private final String[] mStrings = {"aaaaa", "bbbbbb", "cccccc", "ddddddd"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_list;//item_car_list:item的布局
    }

    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mStrings));
        lv.setTextFilterEnabled(true);//设置lv可以被过虑
        sv = (SearchView) view.findViewById(R.id.sv);
        // 设置该SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        sv.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        sv.setQueryHint("查找");
    }

    // 用户输入字符时激发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(mContext, "textChange--->" + newText, Toast.LENGTH_LONG).show();
        if (TextUtils.isEmpty(newText)) {
            // 清除ListView的过滤
            lv.clearTextFilter();
        } else {
            // 使用用户输入的内容对ListView的列表项进行过滤
            lv.setFilterText(newText);
        }
        return true;
    }

    // 单击搜索按钮时激发该方法
    @Override
    public boolean onQueryTextSubmit(String query) {
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户输入的查询内容
        Toast.makeText(mContext, "您的选择是:" + query, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}