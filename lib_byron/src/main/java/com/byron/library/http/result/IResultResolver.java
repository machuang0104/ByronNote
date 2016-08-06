package com.byron.library.http.result;

/**
 * Created by JimGong on 2016/6/23.
 */
public interface IResultResolver {
    <T> T transform(String response, Class<T> classOfT);
}
