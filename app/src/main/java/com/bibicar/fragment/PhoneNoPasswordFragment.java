package com.bibicar.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bibicar.R;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jackie on 2017/6/26 10:30.
 * QQ : 971060378
 * Used as : 手机免密登录的Fragment
 */
public class PhoneNoPasswordFragment extends BaseFragment {

    private Timer mTimer;
    private int mTotalTime;
    private TimerTask mTask;

    private EditText et_input_phone;
    private EditText et_input_verification_code;
    private Button btn_get_verification_code;
    private Button btn_login;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_no_password;
    }

    @Override
    protected void initView(View view) {
        et_input_phone = (EditText) view.findViewById(R.id.et_input_phone);
        et_input_phone.addTextChangedListener(new MyTextChangedListener());
        btn_get_verification_code = (Button) view.findViewById(R.id.btn_get_verification_code);
        btn_get_verification_code.setOnClickListener(this);
        et_input_verification_code = (EditText) view.findViewById(R.id.et_input_verification_code);
        et_input_verification_code.addTextChangedListener(new MyTextChangedListener());
        view.findViewById(R.id.tv_user_protocol).setOnClickListener(this);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    private String getPhone() {
        return et_input_phone.getText().toString().trim();
    }

    private String getVerificationCode() {
        return et_input_verification_code.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_verification_code:
                getVerificationCodeFromServer();
                break;
            case R.id.tv_user_protocol:
                gotoPager(UserProtocolFragment.class, null);
                break;
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        final String phone = getPhone();
        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        final String verification_code = getVerificationCode();
        if (verification_code.length() != 4) {
            ToastUtils.showShort("请输入正确的验证码");
            return;
        }
        //        OkHttpUtils.post()
        //                .url(Constant.loginUrl)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
        //                .addParams(Constant.MOBILE, phone)
        //                .addParams(Constant.CODE, verification_code)
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

    private void getVerificationCodeFromServer() {
        if (mTotalTime > 0) {
            return;
        }
        final String phone = getPhone();
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        mTotalTime = 60;
        mTimer = new Timer();
        initTimerTask();
        mTimer.schedule(mTask, 1000, 1000);
        //        OkHttpUtils.post()
        //                .url(Constant.getVerificationCode)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
        //                .addParams(Constant.TYPE, String.valueOf(2))
        //                .addParams(Constant.MOBILE, phone)
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
        //                            int status = jsonObject.optInt("status");
        //                            if (status == 0) {
        //                                String code = jsonObject.optString("code");
        //                                ToastUtils.showLong("请求数据失败,请检查网络:" + code);
        //                            }
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                    }
        //                });
    }

    /**
     * 初始化时间任务器
     */
    private void initTimerTask() {
        mTask = new TimerTask() {
            @Override
            public void run() {
                if (getView() == null) {
                    mTimer.cancel();
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        --mTotalTime;
                        if (getView() == null) {
                            mTimer.cancel();
                            return;
                        }
                        btn_get_verification_code.setText(String.valueOf(mTotalTime));
                        btn_get_verification_code.setTextSize(15);
                        if (mTotalTime <= 0) {
                            mTimer.cancel();
                            btn_get_verification_code.setText(getString(R.string.free_get));//免费获取
                            btn_get_verification_code.setTextSize(12);
                            btn_get_verification_code.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_login_register));
                        } else {
                            btn_get_verification_code.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_verification_code_shape));//倒计时时候的背景
                        }
                    }
                });
            }
        };
    }

    private class MyTextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            boolean phone = getPhone().length() > 0;
            boolean verificationCode = getVerificationCode().length() > 0;

            if (phone) {
                btn_get_verification_code.setEnabled(true);
            } else {
                btn_get_verification_code.setEnabled(false);
            }

            if (phone && verificationCode) {
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
