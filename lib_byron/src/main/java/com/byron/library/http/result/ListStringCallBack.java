package com.byron.library.http.result;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

/**
 * 只能用于解析返回单个的实体类，不能存在List；List需单独定义一个ListCallBack类型
 * Created by JimGong on 2016/6/23.
 */

public abstract class ListStringCallBack extends Callback<List<String>> {
    IResultResolver resolver;

    public ListStringCallBack() {
    }

    private static int i = 1;

    @Override
    public List<String> parseNetworkResponse(Response res, int id) throws IOException {
        String json = res.body().string();
        List<String> result = new Gson().fromJson(json, List.class);
        return result;
    }
}