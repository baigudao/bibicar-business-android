package com.bibicar.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/26 10:28.
 * QQ : 971060378
 * Used as : 账号密码登录的Fragment
 */
public class AccountPasswordFragment extends BaseFragment {

    private EditText et_input_account;
    private EditText et_input_password;
    private Button btn_login;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_password;
    }

    @Override
    protected void initView(View view) {
        et_input_account = (EditText) view.findViewById(R.id.et_input_account);
        et_input_account.addTextChangedListener(new MyTextChangedListener());
        et_input_password = (EditText) view.findViewById(R.id.et_input_password);
        et_input_password.addTextChangedListener(new MyTextChangedListener());
        view.findViewById(R.id.tv_user_protocol).setOnClickListener(this);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        view.findViewById(R.id.tv_forget_password).setOnClickListener(this);
    }

    private String getAccount() {
        return et_input_account.getText().toString().trim();
    }

    private String getPassword() {
        return et_input_password.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_protocol:
                gotoPager(UserProtocolFragment.class, null);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                gotoPager(ForgetPasswordFragment.class, null);
                break;
            default:
                break;
        }
    }

    private void login() {
        //        OkHttpUtils.post()
        //                .url(Constant.loginUrl)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
        //                .addParams(Constant.MOBILE, getAccount())
        //                .addParams(Constant.PASSWORD, getPassword())
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
        //                                String session_id = jsonObjectData.optString("session");
        //                                if (EmptyUtils.isNotEmpty(session_id)) {
        //                                    SPUtils.getInstance().put(Constant.SESSION_ID, session_id);//存储session_id
        //                                }
        //                                startActivity(new Intent(mContext, MainActivity.class));
        //                                ((EmptyActivity) mContext).finish();
        //                            } else {
        //                                String code = jsonObject.optString("code");
        //                                String msg = jsonObjectData.optString("msg");
        //                                ToastUtils.showLong("请求数据失败,请检查网络:" + code + "-" + msg);
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
            boolean account = getAccount().length() > 0;
            boolean password = getPassword().length() > 0;

            if (account && password) {
                btn_login.setEnabled(true);
            } else {
                btn_login.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
