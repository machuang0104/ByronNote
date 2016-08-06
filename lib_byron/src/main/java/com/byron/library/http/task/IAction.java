package com.byron.library.http.task;

import com.byron.library.bean.weather.WeatherStatusVo;
import com.byron.library.http.result.MCallBack;

public interface IAction {

    /**
     * 保留demo
     */
    void login(String phoneNumber, String verifycode, double latitude, double longitude, MCallBack<String> callBack);


    /**
     * 获取事件详情
     */
    void getWeather(String cityName, MCallBack<WeatherStatusVo> callBack);

}