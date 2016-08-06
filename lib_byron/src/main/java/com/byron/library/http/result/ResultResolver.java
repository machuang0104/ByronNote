package com.byron.library.http.result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by sinyoo on 2016/7/21.
 */
public class ResultResolver implements IResultResolver {
    Gson mGson = new Gson();

    @Override
    public <T> T transform(String response, Class<T> classOfT) {
//        Gson gson = new Gson();
//        java.lang.reflect.Type type = new TypeToken<T>(){}.getType();
//        T jsonBean = gson.fromJson(response, type);
        return mGson.fromJson(response, classOfT);
    }
}
