package com.bibicar.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.bibicar.R;
import com.bibicar.fragment.LoginAndRegisterFragment;
import com.bibicar.util.CommonUtil;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by jackie on 2017/6/17 14:18.
 * QQ : 971060378
 * Used as : 启动页面
 */
public class SplashActivity extends Activity {

    private static final String[] APP_NEED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,//定位
            Manifest.permission.RECORD_AUDIO, //录音
            Manifest.permission.CALL_PHONE,//打电话
            Manifest.permission.WRITE_EXTERNAL_STORAGE, //读写
            Manifest.permission.CAMERA};//照相

    private static final int EXTERNAL_STORAGE_REQ_CODE = 10;//权限请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.hideStatusBar(SplashActivity.this);
        setContentView(R.layout.activity_splash);

        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //            //SDK版本大于等于23，也就是Android 6.0
        //            requestPermission();//请求权限  XiaoMi6.0.1
        //        } else {
        //            //SDK版本小于23的走这
        //            afterRequestPermission();//请求权限之后 MeiZu5.1
        //        }

        //如果用户登录过，就直接进入主页面
        Intent intent = null;
        intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void requestPermission() {
        ArrayList<String> unCheckPermissions = null;
        String[] appNeedPermission = APP_NEED_PERMISSIONS;
        for (String permission : appNeedPermission) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (unCheckPermissions == null) {
                    unCheckPermissions = new ArrayList<>();
                }
                unCheckPermissions.add(permission);
            }
        }
        if (unCheckPermissions != null && !unCheckPermissions.isEmpty()) {
            String[] array = new String[unCheckPermissions.size()];
            ActivityCompat.requestPermissions(this, unCheckPermissions.toArray(array), EXTERNAL_STORAGE_REQ_CODE);
        } else {
            afterRequestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQ_CODE: {
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            finish();
                            return;
                        }
                    }
                    afterRequestPermission();
                } else {
                    afterRequestPermission();
                }
            }
            default:
                break;
        }
    }

    private void afterRequestPermission() {
        registerApp();//注册App
    }

    private void registerApp() {
        String device_identifier = SPUtils.getInstance().getString(Constant.DEVICE_IDENTIFIER);
        if (EmptyUtils.isEmpty(device_identifier)) {
            String device_id = CommonUtil.getDeviceId(this);//设备id
            String device_resolution = ScreenUtils.getScreenWidth() + "*" + ScreenUtils.getScreenHeight();//设备分辨率
            String device_sys_version = Constant.DEVICE_ANDROID + Build.VERSION.SDK_INT;//版本号
            String device_type = String.valueOf(Constant.DEVICE_TYPE_ANDROID);//设备类型Apple/Android

            //            OkHttpUtils.post()
            //                    .url(Constant.registerApp)
            //                    .addParams(Constant.DEVICE_ID, device_id)
            //                    .addParams(Constant.DEVICE_RESOLUTION, device_resolution)
            //                    .addParams(Constant.DEVICE_SYS_VERSION, device_sys_version)
            //                    .addParams(Constant.DEVICE_TYPE, device_type)
            //                    .build()
            //                    .execute(new StringCallback() {
            //                        @Override
            //                        public void onError(Call call, Exception e, int id) {
            //
            //                        }
            //
            //                        @Override
            //                        public void onResponse(String response, int id) {
            //                            JSONObject jsonObject = null;
            //                            try {
            //                                jsonObject = new JSONObject(response);
            //                                int status = jsonObject.optInt("status");
            //                                JSONObject jsonObjectData = jsonObject.optJSONObject("data");
            //                                if (status == 1) {
            //                                    //app注册，返回设备唯一标识，并保存
            //                                    String device_identifier = jsonObjectData.optString(Constant.DEVICE_IDENTIFIER);
            //                                    SPUtils.getInstance().put(Constant.DEVICE_IDENTIFIER, device_identifier);
            //                                } else {
            //                                    String code = jsonObject.optString("code");
            //                                    ToastUtils.showLong("请求数据失败,请检查网络:" + code);
            //                                }
            //                            } catch (JSONException e) {
            //                                e.printStackTrace();
            //                            }
            //                        }
            //                    });
        }
        Intent intent;
        boolean is_user_login = SPUtils.getInstance().getBoolean(Constant.IS_USER_LOGIN);
        if (is_user_login) {
            //如果用户登录过，就直接进入主页面
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            //如果用户没有登录过，就进入登录注册页面
            intent = new Intent(SplashActivity.this, EmptyActivity.class);
            intent.putExtra(Constant.FRAGMENT_NAME, LoginAndRegisterFragment.class.getName());
        }
        startActivity(intent);
        finish();
    }
}
