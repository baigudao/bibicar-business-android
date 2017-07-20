package com.bibicar.bean;

/**
 * Created by jackie on 2017/7/19 13:58.
 * QQ : 971060378
 * Used as : 汽车品牌信息
 */
public class BrandInfoBean extends BaseBean {

    /**
     * abbre : B
     * baidu_brand_id : 0
     * brand_id : 82
     * brand_name : 保时捷
     * brand_path :
     * brand_url : http://image.bitautoimg.com/bt/car/default/images/logo/masterbrand/png/100/m_82_100.png
     * id : 919
     * is_hot : 1
     * masterId : 82
     * saleStatus : 1
     * uv : 681905
     */
    private String abbre;
    private int baidu_brand_id;
    private int brand_id;
    private String brand_name;
    private String brand_path;
    private String brand_url;
    private int id;
    private int is_hot;
    private int masterId;
    private int saleStatus;
    private String uv;

    public String getAbbre() {
        return abbre;
    }

    public void setAbbre(String abbre) {
        this.abbre = abbre;
    }

    public int getBaidu_brand_id() {
        return baidu_brand_id;
    }

    public void setBaidu_brand_id(int baidu_brand_id) {
        this.baidu_brand_id = baidu_brand_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_path() {
        return brand_path;
    }

    public void setBrand_path(String brand_path) {
        this.brand_path = brand_path;
    }

    public String getBrand_url() {
        return brand_url;
    }

    public void setBrand_url(String brand_url) {
        this.brand_url = brand_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(int saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }
}
