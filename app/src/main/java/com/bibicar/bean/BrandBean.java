package com.bibicar.bean;

/**
 * Created by jackie on 2017/7/1 11:23.
 * QQ : 971060378
 * Used as : 品牌bean
 */
public class BrandBean extends BaseBean{

    /**
     * brand_id : 9
     * brand_name : 奥迪
     * brand_url : http://image.bitautoimg.com/bt/car/default/images/logo/masterbrand/png/100/m_9_100.png
     */

    private int brand_id;
    private String brand_name;
    private String brand_url;

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

    public String getBrand_url() {
        return brand_url;
    }

    public void setBrand_url(String brand_url) {
        this.brand_url = brand_url;
    }
}
