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
import com.hahalolo.android.wallet.utils.DeviceUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.hahalolo.android.wallet.constant.HaloConfig.userAgent;

/**
 * @author ngannd
 * Create by ngannd on 06/12/2018
 */
public class
UserAgentInterceptor implements Interceptor {

    public UserAgentInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .header(HttpHeaders.USER_AGENT, userAgent() + DeviceUtils.getAndroidID())
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}