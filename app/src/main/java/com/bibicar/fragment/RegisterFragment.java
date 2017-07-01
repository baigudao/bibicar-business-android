package com.bibicar.fragment;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibicar.R;
import com.bibicar.util.Constant;
import com.bibicar.view.MorePopupWindow;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Call;

/**
 * Created by jackie on 2017/6/19 10:20.
 * QQ : 971060378
 * Used as : 注册页面
 */
public class RegisterFragment extends BaseFragment {

    private Timer mTimer;
    private int mTotalTime;
    private TimerTask mTask;

    private EditText et_company_name;
    private EditText et_account_name;
    private EditText et_phone;
    private Button btn_get_verification_code;
    private EditText et_verification_code;
    private EditText et_password;
    private ImageView iv_add_card;
    private TextView tv_add_card;
    private ImageView iv_che_hang;
    private TextView tv_che_hang;
    private boolean cardOrCheHang;

    private EditText et_che_hang_phone;
    private EditText et_che_hang_address;

    private Button btn_register;

    private ArrayList<File> files;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("注册");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        et_company_name = (EditText) view.findViewById(R.id.et_company_name);//公司名称
        et_company_name.addTextChangedListener(new MyTextChangedListener());
        et_account_name = (EditText) view.findViewById(R.id.et_account_name);//账户名称
        et_account_name.addTextChangedListener(new MyTextChangedListener());
        et_phone = (EditText) view.findViewById(R.id.et_phone);//电话
        et_phone.addTextChangedListener(new MyTextChangedListener());
        btn_get_verification_code = (Button) view.findViewById(R.id.btn_get_verification_code);//获取验证码
        btn_get_verification_code.setOnClickListener(this);
        et_verification_code = (EditText) view.findViewById(R.id.et_verification_code);//输入验证码
        et_verification_code.addTextChangedListener(new MyTextChangedListener());
        et_password = (EditText) view.findViewById(R.id.et_password);//密码
        et_password.addTextChangedListener(new MyTextChangedListener());
        iv_add_card = (ImageView) view.findViewById(R.id.iv_add_card);
        iv_add_card.setOnClickListener(this);//个人名片
        tv_add_card = (TextView) view.findViewById(R.id.tv_add_card);
        tv_add_card.setOnClickListener(this);//个人名片文字
        et_che_hang_phone = (EditText) view.findViewById(R.id.et_che_hang_phone);//车行电话
        et_che_hang_phone.addTextChangedListener(new MyTextChangedListener());
        et_che_hang_address = (EditText) view.findViewById(R.id.et_che_hang_address);//车行地址
        et_che_hang_address.addTextChangedListener(new MyTextChangedListener());
        iv_che_hang = (ImageView) view.findViewById(R.id.iv_che_hang);//车行图片
        iv_che_hang.setOnClickListener(this);
        tv_che_hang = (TextView) view.findViewById(R.id.tv_che_hang);//车行文字
        tv_che_hang.setOnClickListener(this);
        btn_register = (Button) view.findViewById(R.id.btn_register);//注册
        btn_register.setOnClickListener(this);

        cardOrCheHang = false;//false：名片；true：车行图片
        files = new ArrayList<>(2);
    }

    private String getCompanyName() {
        return et_company_name.getText().toString().trim();
    }

    private String getAccountName() {
        return et_account_name.getText().toString().trim();
    }

    private String getPhone() {
        return et_phone.getText().toString().trim();
    }

    private String getVerificationCode() {
        return et_verification_code.getText().toString().trim();
    }

    private String getPassword() {
        return et_password.getText().toString().trim();
    }

    private String getCheHangTel() {
        return et_che_hang_phone.getText().toString().trim();
    }

    private String getCheHangAddress() {
        return et_che_hang_address.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //                goBack();
                registerCode();
                break;
            case R.id.iv_add_card:
            case R.id.tv_add_card:
                cardOrCheHang = false;
                showSelectWindow();
                break;
            case R.id.iv_che_hang:
            case R.id.tv_che_hang:
                cardOrCheHang = true;
                showSelectWindow();
                break;
            case R.id.btn_get_verification_code:
                getVerificationCodeFromServer();
                break;
            case R.id.btn_register:
                registerCode();
                break;
            default:
                break;
        }
    }


    private void registerCode() {
        //注册之前先判断
        if (EmptyUtils.isEmpty(files)) {
            ToastUtils.showLong("请上传名片或车行照片");
        } else {
            if (EmptyUtils.isEmpty(files.get(0))) {
                ToastUtils.showLong("请上传个人名片");
                return;
            }
            if (EmptyUtils.isEmpty(files.get(1))) {
                ToastUtils.showLong("请上传车行照片");
                return;
            }
            ToastUtils.showLong("成功");
        }

        //        OkHttpUtils.post()
        //                .url(Constant.postFileToken)
        //                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
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
        //                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
        //                            if (status == 1) {
        //                                String token = jsonObjectData.optString(Constant.TOKEN);
        //                                postImageFile(token);//上传图片到七牛
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

    private void postImageFile(String token) {
        UploadManager uploadManager = new UploadManager();
        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            uploadManager.put(files.get(i), UUID.randomUUID().toString(), token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    if (info.isOK()) {
                        String hash = response.optString("hash");
                        strings.add(hash);
                        if (strings.size() == 2) {
                            //                            register(strings);
                        }
                    }
                }
            }, null);
        }
    }

    private void register(ArrayList<String> strings) {
        OkHttpUtils.post()
                .url(Constant.register)
                .addParams(Constant.DEVICE_IDENTIFIER, SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER))
                .addParams(Constant.COMPANY, getCompanyName())
                .addParams(Constant.NAME, getAccountName())
                .addParams(Constant.MOBILE, getPhone())
                .addParams(Constant.CODE, getVerificationCode())
                .addParams(Constant.PASSWORD, getPassword())
                .addParams(Constant.CARD_FILE, strings.get(0))
                .addParams(Constant.TELENUMBER, getCheHangTel())
                .addParams(Constant.ADDRESS, getCheHangAddress())
                .addParams(Constant.CAR_FILE, strings.get(1))
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
                            JSONObject jsonObjectData = jsonObject.optJSONObject("data");
                            if (status == 1) {
                                //商家注册成功，返回mobile
                                String mobile = jsonObjectData.optString("mobile");//
                                //返回到上一个界面
                                goBack();
                            } else {
                                String code = jsonObject.optString("code");
                                String mobile = jsonObjectData.optString("mobile");
                                if (mobile.equals("手机号已注册")) {
                                    ToastUtils.showLong("手机号已注册");
                                    return;
                                }
                                ToastUtils.showLong("请求数据失败,请检查网络:" + code);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void showSelectWindow() {
        MorePopupWindow morePopupWindow = new MorePopupWindow(mContext, new MorePopupWindow.MorePopupWindowClickListener() {

            @Override
            public void onFirstBtnClicked() {
                //拍照
                if (SDCardUtils.isSDCardEnable()) {
                    File file = new File(SDCardUtils.getSDCardPath(), "/bibi_business/" + System.currentTimeMillis() + ".jpg");
                    if (!file.getParentFile().exists())
                        file.getParentFile().mkdirs();
                    Uri imageUri = Uri.fromFile(file);
                    //                    getTakePhoto().onPickFromCaptureWithCrop(imageUri, getCropOptions());
                    getTakePhoto().onPickFromCapture(imageUri);
                } else {
                    ToastUtils.showLong("SD卡不可用");
                }
            }

            @Override
            public void onSecondBtnClicked() {
                //从相册中选择
                int limit = Integer.parseInt("1");//设置选择几张图片
                getTakePhoto().onPickMultiple(limit);
                //                getTakePhoto().onPickMultipleWithCrop(limit, getCropOptions());
            }

            @Override
            public void onThirdBtnClicked() {

            }

            @Override
            public void onFourthBtnClicked() {

            }

            @Override
            public void onCancelBtnClicked() {

            }
        }, MorePopupWindow.MORE_POPUP_WINDOW_TYPE.TYPE_ADD_PERSONAL_CARD);
        morePopupWindow.initView();
        morePopupWindow.showAtLocation(getView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (cardOrCheHang) {//车行图片
            tv_che_hang.setVisibility(View.INVISIBLE);
            Glide.with(mContext)
                    .load(new File(result.getImage().getOriginalPath()))
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 8, 0,
                            RoundedCornersTransformation.CornerType.ALL))
                    .into((iv_che_hang));
            files.add(0, null);
            files.add(1, new File(result.getImage().getOriginalPath()));
        } else {//个人名片
            tv_add_card.setVisibility(View.INVISIBLE);
            Glide.with(mContext)
                    .load(new File(result.getImage().getOriginalPath()))
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 8, 0,
                            RoundedCornersTransformation.CornerType.ALL))
                    .into((iv_add_card));
            files.add(0, new File(result.getImage().getOriginalPath()));
            files.add(1, null);
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        ToastUtils.showLong("操作失败");
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    private CropOptions getCropOptions() {

        int width = SizeUtils.dp2px(400);
        int height = SizeUtils.dp2px(300);

        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(false);//第三方裁剪工具
        return builder.create();
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

            boolean companyName = getCompanyName().length() > 0;
            boolean accountName = getAccountName().length() > 0;
            boolean phone = getPhone().length() > 0;
            boolean verificationCode = getVerificationCode().length() > 0;
            boolean password = getPassword().length() > 0;
            boolean cheHangTel = getCheHangTel().length() > 0;
            boolean cheHangAddress = getCheHangAddress().length() > 0;

            if (phone) {
                btn_get_verification_code.setEnabled(true);
            } else {
                btn_get_verification_code.setEnabled(false);
            }

            if (companyName && accountName && phone && verificationCode && password && cheHangTel && cheHangAddress) {
                btn_register.setEnabled(true);
            } else {
                btn_register.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
