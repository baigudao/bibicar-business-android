package com.bibicar.util;

/**
 * Created by jackie on 2017/6/3 10:14.
 * QQ : 971060378
 * Used as : 常量类
 */
public class Constant {

    //一般常量
    public static final String FRAGMENT_NAME = "fragment_name";
    public static final String DEVICE_IDENTIFIER = "device_identifier";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_RESOLUTION = "device_resolution";
    public static final String DEVICE_SYS_VERSION = "device_sys_version";
    public static final String DEVICE_TYPE = "device_type";
    public static final int DEVICE_TYPE_ANDROID = 2;
    public static final String DEVICE_ANDROID = "android_";
    public static final String TYPE = "type";
    public static final String MOBILE = "mobile";
    public static final String COMPANY = "company";
    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String PASSWORD = "password";
    public static final String CARD_FILE = "card_file";
    public static final String TELENUMBER = "telenumber";
    public static final String ADDRESS = "address";
    public static final String CAR_FILE = "car_file";
    public static final String TOKEN = "token";

    //URL常量
    //    private static final String baseUrl = "http://testapi.bibicars.cn";
    private static final String baseUrl = "http://testapi.bibicars.cn";//测试环境
    public static final String registerApp = baseUrl + "/user/identifier";
    public static final String getVerificationCode = baseUrl + "/user/sendcode";//获取手机验证码
    public static final String register = baseUrl + "/user/signup";//商家注册
    public static final String postFileToken = baseUrl + "/user/token";//上传文件的token

}
