package com.bibicar.bean;

import java.util.List;

/**
 * Created by jackie on 2017/7/19 13:59.
 * QQ : 971060378
 * Used as : 汽车信息
 */
public class CarInfoBean extends BaseBean {

    /**
     * baidu_brand_id : 0
     * baidu_series_id : 0
     * board_time : 2016
     * brand_id : 82
     * brand_name :
     * car_color : 0
     * car_intro : 06款 Carrera S 3.8L。  另有红色
     * car_name : 保时捷 911 2006 Carrera S AT 3.8L
     * car_no : 000000
     * car_status : 2
     * car_type : 1
     * check_expiration_time : 16-09-30
     * city_id : 0
     * city_name :
     * contact_address : 南山
     * contact_name : 陈生
     * contact_phone : 400-113-8778
     * created : 1472631331
     * displacement :
     * engine_no : 1
     * exchange_time : 0
     * fav_num : 5
     * files : [{"hash":"Ft6B4tXS0HBarmp8KtXgmmT59rxB","key":"Ft6B4tXS0HBarmp8KtXgmmT59rxB","type":"1"},{"hash":"FtfuOf_KBpgS-XG8pmYcnDSLIs7_","key":"FtfuOf_KBpgS-XG8pmYcnDSLIs7_","type":"3"},{"hash":"Ft5NXxRhXhYp-8P47KKNOUh6ZcZw","key":"Ft5NXxRhXhYp-8P47KKNOUh6ZcZw","type":"4"},{"hash":"FjvxpCstdmnvQZdQfEW3ETkPj2vA","key":"FjvxpCstdmnvQZdQfEW3ETkPj2vA","type":"5"},{"hash":"FjlVzXpOTPlAg7loJ9_6xKnaOTq0","key":"FjlVzXpOTPlAg7loJ9_6xKnaOTq0","type":"6"},{"hash":"Fq4XZALauQX3G--PjURA09HYioeW","key":"Fq4XZALauQX3G--PjURA09HYioeW","type":"7"},{"hash":"FjGXwmdw2mApI0T5nMBvLWpNNW7f","key":"FjGXwmdw2mApI0T5nMBvLWpNNW7f","type":"8"},{"hash":"Fv3Eft4bZMDQ8bqwt9ymDuwPsjkj","key":"Fv3Eft4bZMDQ8bqwt9ymDuwPsjkj","type":"9"},{"hash":"FpWp6i_idPcY6mD3vQ6GJMYdwiOM","key":"FpWp6i_idPcY6mD3vQ6GJMYdwiOM","type":"13"},{"hash":"FhYAbgb0w4gybDmQQTtWBSQjGIsw","key":"FhYAbgb0w4gybDmQQTtWBSQjGIsw","type":"14"},{"hash":"FqUdtGiDUlLTx30fD0XmM29FBLZa","key":"FqUdtGiDUlLTx30fD0XmM29FBLZa","type":"15"},{"hash":"FjgmhkuQOmZlIYNU449mSAGqvaWn","key":"FjgmhkuQOmZlIYNU449mSAGqvaWn","type":"16"}]
     * gearbox :
     * guide_price : 0
     * hash : 57c692231ae26
     * id : 4748
     * image :
     * insurance_due_time : 16-03-12
     * is_pacted : 2
     * is_transfer : 2
     * maintain : 1
     * mileage : 5
     * model_id : 5680
     * model_name :
     * platform_id : 0
     * platform_location :
     * platform_name :
     * platform_url :
     * price : 49
     * sales_volume : 0
     * series_id : 2045
     * series_name :
     * style : 0
     * thumbnail :
     * updated : 1473477103
     * user_id : 389
     * verify_status : 2
     * vin_file :
     * vin_no : 1
     * visit_num : 49
     * vr_url : http://vr.bibicar.cn/Porsche/911/
     */
    private int baidu_brand_id;
    private int baidu_series_id;
    private String board_time;
    private int brand_id;
    private String brand_name;
    private int car_color;
    private String car_intro;
    private String car_name;
    private String car_no;
    private int car_status;
    private int car_type;
    private String check_expiration_time;
    private int city_id;
    private String city_name;
    private String contact_address;
    private String contact_name;
    private String contact_phone;
    private int created;
    private String displacement;
    private String engine_no;
    private int exchange_time;
    private int fav_num;
    private String gearbox;
    private int guide_price;
    private String hash;
    private int id;
    private String image;
    private String insurance_due_time;
    private int is_pacted;
    private int is_transfer;
    private int maintain;
    private String mileage;
    private int model_id;
    private String model_name;
    private int platform_id;
    private String platform_location;
    private String platform_name;
    private String platform_url;
    private String price;
    private int sales_volume;
    private int series_id;
    private String series_name;
    private int style;
    private String thumbnail;
    private int updated;
    private int user_id;
    private int verify_status;
    private String vin_file;
    private String vin_no;
    private int visit_num;
    private String vr_url;
    private List<FilesBean> files;

    public int getBaidu_brand_id() {
        return baidu_brand_id;
    }

    public void setBaidu_brand_id(int baidu_brand_id) {
        this.baidu_brand_id = baidu_brand_id;
    }

    public int getBaidu_series_id() {
        return baidu_series_id;
    }

    public void setBaidu_series_id(int baidu_series_id) {
        this.baidu_series_id = baidu_series_id;
    }

    public String getBoard_time() {
        return board_time;
    }

    public void setBoard_time(String board_time) {
        this.board_time = board_time;
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

    public int getCar_color() {
        return car_color;
    }

    public void setCar_color(int car_color) {
        this.car_color = car_color;
    }

    public String getCar_intro() {
        return car_intro;
    }

    public void setCar_intro(String car_intro) {
        this.car_intro = car_intro;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public int getCar_status() {
        return car_status;
    }

    public void setCar_status(int car_status) {
        this.car_status = car_status;
    }

    public int getCar_type() {
        return car_type;
    }

    public void setCar_type(int car_type) {
        this.car_type = car_type;
    }

    public String getCheck_expiration_time() {
        return check_expiration_time;
    }

    public void setCheck_expiration_time(String check_expiration_time) {
        this.check_expiration_time = check_expiration_time;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public int getExchange_time() {
        return exchange_time;
    }

    public void setExchange_time(int exchange_time) {
        this.exchange_time = exchange_time;
    }

    public int getFav_num() {
        return fav_num;
    }

    public void setFav_num(int fav_num) {
        this.fav_num = fav_num;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(int guide_price) {
        this.guide_price = guide_price;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInsurance_due_time() {
        return insurance_due_time;
    }

    public void setInsurance_due_time(String insurance_due_time) {
        this.insurance_due_time = insurance_due_time;
    }

    public int getIs_pacted() {
        return is_pacted;
    }

    public void setIs_pacted(int is_pacted) {
        this.is_pacted = is_pacted;
    }

    public int getIs_transfer() {
        return is_transfer;
    }

    public void setIs_transfer(int is_transfer) {
        this.is_transfer = is_transfer;
    }

    public int getMaintain() {
        return maintain;
    }

    public void setMaintain(int maintain) {
        this.maintain = maintain;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_location() {
        return platform_location;
    }

    public void setPlatform_location(String platform_location) {
        this.platform_location = platform_location;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_url() {
        return platform_url;
    }

    public void setPlatform_url(String platform_url) {
        this.platform_url = platform_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public int getSeries_id() {
        return series_id;
    }

    public void setSeries_id(int series_id) {
        this.series_id = series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(int verify_status) {
        this.verify_status = verify_status;
    }

    public String getVin_file() {
        return vin_file;
    }

    public void setVin_file(String vin_file) {
        this.vin_file = vin_file;
    }

    public String getVin_no() {
        return vin_no;
    }

    public void setVin_no(String vin_no) {
        this.vin_no = vin_no;
    }

    public int getVisit_num() {
        return visit_num;
    }

    public void setVisit_num(int visit_num) {
        this.visit_num = visit_num;
    }

    public String getVr_url() {
        return vr_url;
    }

    public void setVr_url(String vr_url) {
        this.vr_url = vr_url;
    }

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public static class FilesBean {
        /**
         * hash : Ft6B4tXS0HBarmp8KtXgmmT59rxB
         * key : Ft6B4tXS0HBarmp8KtXgmmT59rxB
         * type : 1
         */

        private String hash;
        private String key;
        private String type;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
