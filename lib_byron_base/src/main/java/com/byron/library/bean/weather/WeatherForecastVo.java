package com.byron.library.bean.weather;

import java.io.Serializable;

/**
 * 未来天气
 *
 * @author machuang
 */
@SuppressWarnings("serial")
public class WeatherForecastVo implements Serializable {


    private String fengxiang;
    /**
     * 3-4
     */
    private String fengli;
    /**
rain or not
     */
    private String type;
    /**
     *hight tempature
     */
    private String high;
    /**
     * low 19
     */
    private String low;
    /**

     */
    private String date;

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherItem [fengxiang=" + fengxiang + ", fengli=" + fengli
                + ", type=" + type + ", high=" + high + ", low=" + low
                + ", date=" + date + "]";
    }

}