package com.byron.library.http.taskiml;

import com.byron.library.bean.weather.WeatherStatusVo;
import com.byron.library.http.BasicUtil;
import com.byron.library.http.TaskId;
import com.byron.library.http.result.MCallBack;
import com.byron.library.http.task.IAction;

import java.util.HashMap;

/**
 * Created by sinyoo on 2016/7/21.
 */
public class ActionImpl implements IAction {
    private static ActionImpl instance;

    private ActionImpl() {
    }

    public static ActionImpl getInstance() {
        if (instance == null) {
            instance = new ActionImpl();
        }
        return instance;
    }

    @Override
    public void login(String phoneNumber, String verifycode, double latitude,
                      double longitude, MCallBack<String> callback) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phone", phoneNumber);
        map.put("verifyCode", verifycode);
        map.put("lat", String.valueOf(latitude));
        map.put("lon", String.valueOf(longitude));
        BasicUtil.post(TaskId.ACTION_TEST, map, callback);
    }

    @Override
    public void getWeather(String cityName, MCallBack<WeatherStatusVo> callBack) {
        BasicUtil.get(TaskId.WEATHER_DETAIL, cityName, callBack);
    }
}
