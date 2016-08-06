package com.byron.library.http;

/**
 * Created by sinyoo on 2016/7/21.
 */
public class TaskUrl {
    public static String getUrl(int taskId) {
        String tmp = "";
        switch (taskId) {
            case TaskId.ACTION_TEST: {
                return "account/login";
            }
            default: {
                return "";
            }
        }
    }

    public static String getUrl(int taskId, String urlParams) {
        switch (taskId) {
            case TaskId.WEATHER_DETAIL:
                // return "http://wthrcdn.etouch.cn/WeatherApi?city=" + urlParams;
                return "http://wthrcdn.etouch.cn/weather_mini?city=" + urlParams;
            default:
                return null;
        }
    }

    public static String getUrl(int taskId, String par1, String par2) {
        String tmp = "";
        switch (taskId) {
            default:
                return "";
//                tmp = String.format("study/%1s/patients?studyId=%2s", par1, par2);
        }
    }
}
