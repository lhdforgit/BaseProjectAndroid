/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api.utils;

import android.support.annotation.NonNull;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hahalolo.android.wallet.BuildConfig;
import com.hahalolo.android.wallet.constant.HaloConfig;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.hahalolo.android.wallet.constant.HaloConfig.API_APP_KEY;
import static com.hahalolo.android.wallet.constant.HaloConfig.APP_KEY;
import static com.hahalolo.android.wallet.constant.HaloConfig.DEVICE_NAME;
import static com.hahalolo.android.wallet.constant.HaloConfig.DEVICE_NAME_VALUE;
import static com.hahalolo.android.wallet.constant.HaloConfig.MANUFACTURER;
import static com.hahalolo.android.wallet.constant.HaloConfig.MANUFACTURER_VALUE;
import static com.hahalolo.android.wallet.constant.HaloConfig.MODEL;
import static com.hahalolo.android.wallet.constant.HaloConfig.MODEL_VALUE;
import static com.hahalolo.android.wallet.constant.HaloConfig.VERSION_CODE;
import static com.hahalolo.android.wallet.constant.HaloConfig.VERSION_NAME;

/**
 * @author ndn
 * Create by ndn
 * Create on 15.05.2018
 */

public class ServiceGenerator {

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(HaloConfig.MONGO_HOST)
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit;

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS);

    private ServiceGenerator() {

    }

    /**
     * Create default service with authorization
     *
     * @param serviceClass  {@link Class} service class type
     * @param <S>           Service Class
     * @param authorization Authorization header
     * @return Service class
     */
    public static <S> S createService(Class<S> serviceClass, @NonNull String authorization) {
        checkNotNull(authorization);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, authorization)
                    .header(DEVICE_NAME, DEVICE_NAME_VALUE)
                    .header(MODEL, MODEL_VALUE)
                    .header(MANUFACTURER, MANUFACTURER_VALUE)
                    .header(VERSION_CODE, String.valueOf(BuildConfig.VERSION_CODE))
                    .header(VERSION_NAME, BuildConfig.VERSION_NAME)
                    .header(API_APP_KEY, APP_KEY)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).addInterceptor(new UserAgentInterceptor());
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        builder.baseUrl(HaloConfig.MONGO_HOST);
        return retrofit.create(serviceClass);
    }

    /**
     * Create default service
     *
     * @param serviceClass {@link Class} service class type
     * @param <S>          Service Class
     * @return Service class
     */
    public static <S> S createService(Class<S> serviceClass) {
        builder.baseUrl(HaloConfig.MONGO_HOST);
        return createService(serviceClass, "");
    }
}
