package com.bibicar.bean;

/**
 * Created by jackie on 2017/7/19 14:02.
 * QQ : 971060378
 * Used as : 模型信息
 */
public class ModelInfoBean extends BaseBean {

    /**
     * id : 9818
     * is_stop : 1
     * model_id : 5680
     * model_name : 2006 Carrera S AT 3.8L
     * model_year : 2006
     * name : 3.8L/261kW
     * series_id : 2045
     */
    private int id;
    private String is_stop;
    private int model_id;
    private String model_name;
    private String model_year;
    private String name;
    private int series_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(String is_stop) {
        this.is_stop = is_stop;
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

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries_id() {
        return series_id;
    }

    public void setSeries_id(int series_id) {
        this.series_id = series_id;
    }
}
