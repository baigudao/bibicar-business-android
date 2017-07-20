package com.bibicar.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bibicar.R;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/6/27 16:51.
 * QQ : 971060378
 * Used as : 添加员工页面
 */
public class AddStaffFragment extends BaseFragment {

    private EditText et_staff_name;
    private EditText et_staff_phone;
    private String spinner_item_string;
    private Button btn_add_staff;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_staff;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("添加员工");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        MyTextChangedListener myTextChangedListener = new MyTextChangedListener();
        et_staff_name = (EditText) view.findViewById(R.id.et_staff_name);
        et_staff_name.addTextChangedListener(myTextChangedListener);
        et_staff_phone = (EditText) view.findViewById(R.id.et_staff_phone);
        et_staff_phone.addTextChangedListener(myTextChangedListener);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("销售");
        strings.add("财务");
        strings.add("文案");
        strings.add("文员");
        strings.add("客服");
        strings.add("人事");
        strings.add("工程师");
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, strings);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_item_string = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_add_staff = (Button) view.findViewById(R.id.btn_add_staff);
        btn_add_staff.setOnClickListener(this);
    }

    private String getStaffName() {
        return et_staff_name.getText().toString().trim();
    }

    private String getStaffPhone() {
        return et_staff_phone.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.btn_add_staff:
                addMakeSure();
                break;
            default:
                break;
        }
    }

    private void addMakeSure() {
        if (!RegexUtils.isMobileExact(getStaffPhone())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        //        OkHttpUtils.post()
        //                .url(Constant.addStaffUrl)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
        //                .addParams(Constant.SESSION_ID, SPUtils.getInstance().getString(Constant.SESSION_ID))
        //                .addParams(Constant.NICKNAME, getStaffName())
        //                .addParams(Constant.MOBILE, getStaffPhone())
        //                .addParams(Constant.POSITION, spinner_item_string)
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
        //                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
        //                            int status = jsonObject.optInt("status");
        //                            if (status == 1) {
        //                                String msg = jsonObjectData.optString("msg");
        //                                if (EmptyUtils.isNotEmpty(msg) && msg.equals("Success")) {
        //                                    ToastUtils.showShort("添加成功");
        //                                    goBack();//返回上一个页面
        //                                }
        //                            } else {
        //                                String code = jsonObject.optString("code");
        //                                ToastUtils.showLong("请求数据失败,请检查网络:" + code);
        //                            }
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                    }
        //                });
    }

    private class MyTextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean staff_name = getStaffName().length() > 0;
            boolean staff_phone = getStaffPhone().length() > 0;
            boolean staff_position = spinner_item_string.length() > 0;

            if (staff_name && staff_phone && staff_position) {
                btn_add_staff.setEnabled(true);
            } else {
                btn_add_staff.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
