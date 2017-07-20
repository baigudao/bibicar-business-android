package com.bibicar.bean;

/**
 * Created by jackie on 2017/7/19 11:33.
 * QQ : 971060378
 * Used as : 用户信息
 */
public class UserInfoBean extends BaseBean {

    /**
     * avatar : http://img.bibicar.cn/FmDMHALgJxKY9Rw3APp5QSsnajuF
     * company : 0
     * nickname : BiBi Car
     * type : 2
     * user_id : 389
     */
    private String avatar;
    private int company;
    private String nickname;
    private int type;
    private int user_id;
    private int consult_num;//咨询数
    private int lant_custom_num;//潜客数

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLant_custom_num() {
        return lant_custom_num;
    }

    public void setLant_custom_num(int lant_custom_num) {
        this.lant_custom_num = lant_custom_num;
    }

    public int getConsult_num() {
        return consult_num;
    }

    public void setConsult_num(int consult_num) {
        this.consult_num = consult_num;
    }
}
