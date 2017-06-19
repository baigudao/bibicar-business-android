package com.bibicar.util;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

/**
 * Created by jackie on 2017/6/19 11:34.
 * QQ : 971060378
 * Used as : 一般工具类
 */
public class CommonUtil {

    /**
     * 获取diviceId,在测试升级时可能用到，上线时可以不再获取，可重写此方法返回一个固定的字符串，如：android，
     * 这样就可以不添加读取手机状态的权限
     * 需要增加 权限     <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @param context
     * @return
     */
    static public String getDeviceId(Context context) {
        String deviceId = SPUtils.getInstance().getString(Constant.DEVICE_ID);
        if (TextUtils.isEmpty(deviceId)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
            if (deviceId == null) {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID); // *** use for tablets
                if (deviceId == null) {
                    deviceId = "deviceId";
                }
                return "deviceId";
            } else {
                SPUtils.getInstance().put(Constant.DEVICEID, deviceId);
                return deviceId;
            }
        } else {
            return deviceId;
        }
    }
}