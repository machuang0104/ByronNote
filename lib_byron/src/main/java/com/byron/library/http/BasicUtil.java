package com.byron.library.http;

import com.byron.library.log.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OtherRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by sinyoo on 2016/7/21.
 */
public class BasicUtil {

    public static void post(int taskId, HashMap<String, String> map, Callback callback) {
        String url = TaskUrl.getUrl(taskId);
        LogUtil.Http("url post: " + url);
        LogUtil.Http("params : " + map.toString());
        PostFormBuilder call = OkHttpUtils.post().url(TaskUrl.getUrl(taskId));
        if (map != null) {
            call.params(map);
        }
        call.build().execute(callback);
//        addBasicAuthHeaders(call).build().execute(callback);
    }

    public static void get(int taskId, Callback callback) {
        get(taskId, null, null, callback);
    }

    public static void get(int taskId, String urlParams, Callback callback) {
        get(taskId, urlParams, null, callback);
    }

    public static void get(int taskId, String urlParams1, String urlParams2, Callback callback) {
        String url = null;
        if (urlParams1 == null) {
            url = TaskUrl.getUrl(taskId);
        } else if (urlParams2 != null) {
            url = TaskUrl.getUrl(taskId, urlParams1, urlParams2);
        } else {
            url = TaskUrl.getUrl(taskId, urlParams1);
        }
        LogUtil.Http("url get: " + url);
        GetBuilder call = OkHttpUtils.get().url(TaskUrl.getUrl(taskId));
        call.build().execute(callback);
//        addBasicAuthHeaders(call).build().execute(callback);
    }

    public static void getWithParams(int taskId, HashMap<String, String> map, Callback callback) {
        String url = null;
        url = TaskUrl.getUrl(taskId);
        LogUtil.Http("url get: " + url);
        GetBuilder call = OkHttpUtils.get().url(TaskUrl.getUrl(taskId));
        if (map != null) {
            call.params(map);
        }
        call.build().execute(callback);
//        addBasicAuthHeaders(call).build().execute(callback);
    }

    public static void delete(int taskId, String urlParams, Callback callback) {
        String url = null;
        if (urlParams == null) {
            url = TaskUrl.getUrl(taskId);
        } else if (urlParams != null) {
            url = TaskUrl.getUrl(taskId, urlParams);
        }
        LogUtil.Http("url delete ：" + url);
        OtherRequestBuilder delete = OkHttpUtils.delete().url(TaskUrl.getUrl(taskId, urlParams));
//        addBasicAuthHeaders(delete).build().execute(callback);
        delete.build().execute(callback);
    }

    public static void uploadImg(String tag, File file, String url, Callback callback) {
        url = url.replaceAll("\"", "");
        OtherRequestBuilder builder = OkHttpUtils.put();
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        builder.requestBody(body);
        builder.url(url);
        builder.tag(tag);
        builder.build().execute(callback);
    }

    /**
     * 需要添加的请求头，用于认证
     */
    private static String guest_token;
    private static String guest_name;

//    private static OkHttpRequestBuilder addBasicAuthHeaders(OkHttpRequestBuilder request) {
//        guest_token = SharedUtil.getString(K.http.TOKEN);
//
//        if (TextUtils.isEmpty(guest_token)) {
//            guest_token = AppConfig.GUEST_TOKEN;
//            guest_name = AppConfig.GUEST_NAME;
//        } else {
//            guest_name = UserData.getIns().getUserName();
//        }
//
//        String credential = Credentials.basic(guest_name, guest_token);
//        return request.addHeader(K.http.HEAD_AUTH, credential);
//    }
}
