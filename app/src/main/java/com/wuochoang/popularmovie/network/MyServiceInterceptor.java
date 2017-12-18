package com.wuochoang.popularmovie.network;

import android.util.Base64;

import com.wuochoang.popularmovie.App;
import com.wuochoang.popularmovie.common.Config;
import com.wuochoang.popularmovie.common.Constant;
import com.wuochoang.popularmovie.common.utils.LogUtils;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import com.wuochoang.popularmovie.BuildConfig;

/**
 * Created by quyenlx on 8/9/2017.
 */

public class MyServiceInterceptor implements Interceptor {
    private static final String NO_AUTH_HEADER_KEY = "No-Authentication";
    private final String CONTENT_TYPE = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "application/json";
    private final String API_KEY = "apikey";
    private final String API_KEY_VALUE = "0bac85d8945140b3bc8dde8aff16e329";

    /*@Headers(No-Authentication: true)*/
    private String sessionToken;

    @Inject
    public MyServiceInterceptor() {

    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttlUrl = original.url();
        HttpUrl url = originalHttlUrl.newBuilder()
                .addQueryParameter("api_key", Constant.API_KEY)
                .build();
        Request.Builder requestBuilder = original.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public static String encodeCredentialsForBasicAuthorization() {
        final String userAndPassword = Config.BASIC_AUTHOR_USERNAME + ":" + Config.BASIC_AUTHOR_PASSWORD;
        LogUtils.i("Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP));
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }
}