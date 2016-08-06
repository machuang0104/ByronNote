package com.byron.library.http.result;

import com.byron.library.log.LogUtil;
import com.google.gson.JsonParseException;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Types;

import okhttp3.Response;

/**
 * 只能用于解析返回单个的实体类，不能存在List；List需单独定义一个ListCallBack类型
 * Created by JimGong on 2016/6/23.
 */

public abstract class MCallBack<T> extends Callback<T> {
    IResultResolver resolver;

    public MCallBack() {
    }

    private static int i = 1;

    @Override
    public T parseNetworkResponse(Response res, int id) throws IOException {
        String string = null;
        string = res.body().string();
        Type type1 = this.getClass().getGenericSuperclass();
        ParameterizedType type2 = (ParameterizedType) type1;
        Type[] temp = type2.getActualTypeArguments();
        Type trueType = temp[0];
        Class<T> cls = (Class<T>) temp[0];
        if (cls == String.class) {
            return (T) string;
        }
        T bean = new ResultResolver().transform(string, cls);

        return bean;
    }
}