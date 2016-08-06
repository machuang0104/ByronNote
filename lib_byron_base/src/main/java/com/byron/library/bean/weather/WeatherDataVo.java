package com.byron.library.bean.weather;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class WeatherDataVo implements Serializable {

    private String wendu;

    private String ganmao;

    private WeatherYesterdayVo yesterday;

    private ArrayList<WeatherForecastVo> forecast;


    private String aqi;


    private String city;

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public WeatherYesterdayVo getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherYesterdayVo yesterday) {
        this.yesterday = yesterday;
    }

    public ArrayList<WeatherForecastVo> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<WeatherForecastVo> forecast) {
        this.forecast = forecast;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}