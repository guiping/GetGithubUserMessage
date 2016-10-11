package com.github.guiping.getgithubusermessage.apiutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guiping on 16/10/10.
 */

public class ApiClient {
    private static final String BASEURL = "https://api.github.com/";
    private static Retrofit mRetrofit;
    private static final Object monity = new Object();
    private static  ApiDataRetrofit mApiDataRetrofit;

    private ApiClient() {
    }

    static {
        Gson mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

    }

    public static ApiDataRetrofit getAPiDataRetrofit() {

            synchronized (monity) {
                if (mApiDataRetrofit == null) {
                    mApiDataRetrofit = mRetrofit.create(ApiDataRetrofit.class);
                }
            }

        return mApiDataRetrofit;
    }

}
