package com.bibicar.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.bibicar.R;
import com.bibicar.util.CommonUtil;
import com.bibicar.util.Constant;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;

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
        setContentView(R.layout.activity_splash);

        //如果用户没有登录，就进入登录注册页面
        //        Intent intent = new Intent(SplashActivity.this, EmptyActivity.class);
        //        intent.putExtra(Constant.FRAGMENT_NAME, LoginAndRegisterFragment.class.getName());
        //        startActivity(intent);
        //        finish();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //SDK版本大于等于23，也就是Android 6.0
            requestPermission();//请求权限
        } else {
            //SDK版本小于23的走这
            afterRequestPermission();//请求权限之后
        }

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
        if (TextUtils.isEmpty(device_identifier)) {
            ToastUtils.showLong(CommonUtil.getDeviceId(this));
            String device_id = "";//设备id
            String device_resolution = ScreenUtils.getScreenWidth() + "*" + ScreenUtils.getScreenHeight();//设备分辨率
            String device_sys_version = "";//版本号
            String device_type = "";//设备类型Apple/Android
            //            ToastUtils.showLong("获取设备系统版本号:" + DeviceUtils.getSDKVersion() + "AndroidID:" + DeviceUtils.getAndroidID() +
            //                    "设备厂商:" + DeviceUtils.getManufacturer() + "设备型号:" + DeviceUtils.getModel() + "设备id：" + "空");
        }


        //        Retrofit retrofit = new Retrofit.Builder()
        //                //使用自定义的mGsonConverterFactory
        //                .addConverterFactory(GsonConverterFactory.create())
        //                .baseUrl("http://apis.baidu.com/txapi/")
        //                .build();
        //        mApi = retrofit.create(APi.class);
        //
        //        mApi = retrofit.create(APi.class);
        //        Call<News> news = mApi.getNews("1", "10");
        //        news.enqueue(new Callback<News>() {
        //            @Override
        //            public void onResponse(Call<News> call, Response<News> response) {
        //
        //            }
        //
        //            @Override
        //            public void onFailure(Call<News> call, Throwable t) {
        //
        //            }
        //        });
        //
        //
        //
        //        GBExecutionPool.getExecutor().execute(new Runnable() {
        //            @Override
        //            public void run() {
        //                String device_identifier = Constants.getDeviceIdentifier(SplashActivity.this);
        //                if (TextUtils.isEmpty(device_identifier)) {
        //                    //如果用户的设备标识为空
        //                    DisplayMetrics displaymetrics = new DisplayMetrics();
        //                    ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        //                    //app注册，返回设备唯一标识
        //                    ServerResultBean<ResponseObject> result = DataManger.getInstance().
        //                            appRegister(Constants.getAppRegisterData(DeviceUtil.getDeviceId(BaseApplication.getAppContext()), (displaymetrics.widthPixels + "*" + displaymetrics.heightPixels),
        //                                    (Constants.DEVICE_ANDROID + Build.VERSION.SDK_INT), Constants.DEVICE_TYPE_ANDROID));
        //                    if (result.isSuccess() && result.getData() != null) {
        //                        if (BaseApplication.getAppContext() != null) {
        //                            PreferencesWrapper.getInstanse(BaseApplication.getAppContext()).setPreferenceStringValue(Constants.DEVICE_IDENTIFIER, result.getData().getDevice_identifier());
        //                        }
        //                    }
        //                }
        //
        //                //                else {
        //                //如果用户设备标识不为空
        //                //                    try {
        //                //                        Thread.sleep(3 * 1000);
        //                //                    } catch (InterruptedException e) {
        //                //                        e.printStackTrace();
        //                //                    }
        //
        //                //如果用户设备标识不为空
        //                runOnUiThread(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        Intent intent;
        //                        if (!Constants.isShowGuildView(SplashActivity.this)) {
        //                            //如果没有进入引导页面，就进入引导页面
        //                            intent = new Intent(SplashActivity.this, EmptyActivity.class);
        //                            intent.putExtra(Constants.FRAGMENT_NAME, GuildFragment.class.getName());
        //                        } else {
        //                            //如果进入了引导页面
        //                            if (DataManger.getInstance().isUserLogin()) {
        //                                //如果用户登录过来，就直接进入主页面
        //                                intent = new Intent(SplashActivity.this, HomeActivity.class);
        //                            } else {
        //                                //如果用户没有登录，就进入登录注册页面
        //                                intent = new Intent(SplashActivity.this, EmptyActivity.class);
        //                                intent.putExtra(Constants.FRAGMENT_NAME, LoginFragment.class.getName());
        //                            }
        //                        }
        //                        startActivity(intent);
        //                        finish();
        //                    }
        //                });
        //            }
        //        });
    }
}
