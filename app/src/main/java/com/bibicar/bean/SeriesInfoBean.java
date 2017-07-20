package com.bibicar.bean;

/**
 * Created by jackie on 2017/7/19 14:05.
 * QQ : 971060378
 * Used as : 汽车系列信息
 */
public class SeriesInfoBean extends BaseBean {

    /**
     * baidu_series_id : 0
     * brand_id : 82
     * brand_series_id : 2045
     * brand_series_name : 911
     * brand_series_path :
     * brand_series_url : http://img1.bitautoimg.com/autoalbum/files/20120305/620/0502336200_{0}.jpg
     * dealerPrice : 116.70-261.10万
     * foreigns : 1
     * id : 1387
     * makename : 进口保时捷
     * saleStatus : 1
     * uv : 11989
     */
    private int baidu_series_id;
    private int brand_id;
    private int brand_series_id;
    private String brand_series_name;
    private String brand_series_path;
    private String brand_series_url;
    private String dealerPrice;
    private String foreigns;
    private int id;
    private String makename;
    private int saleStatus;
    private String uv;

    public int getBaidu_series_id() {
        return baidu_series_id;
    }

    public void setBaidu_series_id(int baidu_series_id) {
        this.baidu_series_id = baidu_series_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getBrand_series_id() {
        return brand_series_id;
    }

    public void setBrand_series_id(int brand_series_id) {
        this.brand_series_id = brand_series_id;
    }

    public String getBrand_series_name() {
        return brand_series_name;
    }

    public void setBrand_series_name(String brand_series_name) {
        this.brand_series_name = brand_series_name;
    }

    public String getBrand_series_path() {
        return brand_series_path;
    }

    public void setBrand_series_path(String brand_series_path) {
        this.brand_series_path = brand_series_path;
    }

    public String getBrand_series_url() {
        return brand_series_url;
    }

    public void setBrand_series_url(String brand_series_url) {
        this.brand_series_url = brand_series_url;
    }

    public String getDealerPrice() {
        return dealerPrice;
    }

    public void setDealerPrice(String dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public String getForeigns() {
        return foreigns;
    }

    public void setForeigns(String foreigns) {
        this.foreigns = foreigns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakename() {
        return makename;
    }

    public void setMakename(String makename) {
        this.makename = makename;
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
