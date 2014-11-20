package com.smp.cutecatcontest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Steve on 11/13/14.
 */
public class GalleryClient
{
    private static final String BASE_URL = "http://dawdling-dog-2835.vagrantshare.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(String user, String pass, AsyncHttpResponseHandler responseHandler)
    {
        RequestParams params = new RequestParams();
        params.put("username", user);
        params.put("password", pass);
        GalleryClient.post("users/login", params, responseHandler);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl)
    {
        return BASE_URL + relativeUrl;
    }
}
