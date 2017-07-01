package com.bibicar.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bibicar.R;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

/**
 * Created by jackie on 2017/6/26 17:18.
 * QQ : 971060378
 * Used as : 忘记密码页面
 */
public class ForgetPasswordFragment extends BaseFragment {

    private Timer mTimer;
    private int mTotalTime;
    private TimerTask mTask;

    private EditText et_input_phone;
    private Button btn_get_verification_code;
    private EditText et_input_verification_code;
    private EditText et_input_password;
    private Button btn_make_sure;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forget_password;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("重置密码");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);
        MyTextChangedListener myTextChangedListener = new MyTextChangedListener();
        et_input_phone = (EditText) view.findViewById(R.id.et_input_phone);
        et_input_phone.addTextChangedListener(myTextChangedListener);
        btn_get_verification_code = (Button) view.findViewById(R.id.btn_get_verification_code);
        btn_get_verification_code.setOnClickListener(this);
        et_input_verification_code = (EditText) view.findViewById(R.id.et_input_verification_code);
        et_input_verification_code.addTextChangedListener(myTextChangedListener);
        et_input_password = (EditText) view.findViewById(R.id.et_input_password);
        et_input_password.addTextChangedListener(myTextChangedListener);
        btn_make_sure = (Button) view.findViewById(R.id.btn_make_sure);
        btn_make_sure.setOnClickListener(this);
    }

    private String getPhone() {
        return et_input_phone.getText().toString().trim();
    }

    private String getVerificationCode() {
        return et_input_verification_code.getText().toString().trim();
    }

    private String getPassword() {
        return et_input_password.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.btn_get_verification_code:
                getVerificationCodeFromServer();
                break;
            case R.id.btn_make_sure:
                makeSure();
                break;
            default:
                break;
        }
    }

    private void makeSure() {
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
        final String session_id = SPUtils.getInstance().getString(Constant.SESSION_ID);
        if (EmptyUtils.isEmpty(session_id)) {
            ToastUtils.showShort("请先注册登录");
            return;
        }
        Log.e("修改密码的参数>>>", SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER) + "@@" + session_id + "@@" + phone + "@@" + verification_code + "@@" + getPassword() + "@@" + getPassword());
        //35056b5924eac11c58103d678a8e7b0b
        // session5950cf057d0ea
        // 13751036879
        // 1111
        // 123456
        // 123456
        OkHttpUtils.post()
                .url(Constant.correctPasswordUrl)
                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
                .addParams(Constant.SESSION_ID, session_id)
                .addParams(Constant.MOBILE, phone)
                .addParams(Constant.CODE, verification_code)
                .addParams(Constant.PASSWORD1, getPassword())
                .addParams(Constant.PASSWORD2, getPassword())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("修改密码数据>>>", response);
                        ToastUtils.showLong("hehe");
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
                    }
                });
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
        OkHttpUtils.post()
                .url(Constant.getVerificationCode)
                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
                .addParams(Constant.TYPE, String.valueOf(2))
                .addParams(Constant.MOBILE, phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            int status = jsonObject.optInt("status");
                            if (status == 0) {
                                String code = jsonObject.optString("code");
                                ToastUtils.showLong("请求数据失败,请检查网络:" + code);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
            boolean verification_code = getVerificationCode().length() > 0;
            boolean password = getPassword().length() > 0;

            if (phone) {
                btn_get_verification_code.setEnabled(true);
            } else {
                btn_get_verification_code.setEnabled(false);
            }

            if (phone && password && verification_code) {
                btn_make_sure.setEnabled(true);
            } else {
                btn_make_sure.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
