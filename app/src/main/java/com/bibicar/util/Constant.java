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
    public static final String[] TAB_TITLE = new String[]{"账号密码登录", "手机免密登录"};
    public static final String SESSION_ID = "session_id";
    public static final String PASSWORD1 = "password1";
    public static final String PASSWORD2 = "password2";
    public static final String NICKNAME = "nickname";
    public static final String POSITION = "position";
    public static final int PAGE_NUM = 10;//刷新一页的数据
    public static final String IS_USER_LOGIN = "is_user_login";

    //URL常量
    //    private static final String baseUrl = "http://testapi.bibicars.cn";
    private static final String baseUrl = "http://testapi.bibicars.cn";//商家版测试环境
    //    private final static String hostUrl = "https://api.bibicar.cn/";
    private final static String hostUrl = "https://testapi.bibicar.cn/";//个人版测试环境
    public static final String registerApp = baseUrl + "/user/identifier";
    public static final String getVerificationCode = baseUrl + "/user/sendcode";//获取手机验证码
    public static final String register = baseUrl + "/user/signup";//商家注册
    public static final String postFileToken = baseUrl + "/user/token";//上传文件的token
    public static final String loginUrl = baseUrl + "/user/login";//商家登录
    public static final String correctPasswordUrl = baseUrl + "/user/password";//修改密码
    public static final String cheHangInfoUrl = baseUrl + "/car/company";//车行信息
    public static final String addStaffUrl = baseUrl + "/user/create";//添加员工
    public static final String staffListUrl = baseUrl + "/user/employeelist";//员工列表
    public static final String carBrandUrl = hostUrl + "car/brand";//汽车品牌
}
