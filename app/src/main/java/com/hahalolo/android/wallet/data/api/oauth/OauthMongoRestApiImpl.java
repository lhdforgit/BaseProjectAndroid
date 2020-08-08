/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api.oauth;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hahalolo.android.wallet.BuildConfig;
import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.api.utils.ServiceGenerator;
import com.hahalolo.android.wallet.data.entities.oauth.LogoutResponseEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 */
@Singleton
public class OauthMongoRestApiImpl implements OauthMongoRestApi {

    @Inject
    OauthMongoRestApiImpl() {
    }


    @Override
    public LiveData<ApiResponse<TokenEntity>> signIn(@NonNull String authorization) {
        checkNotNull(authorization);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), authorization);
        }
        OauthMongoService service = ServiceGenerator.createService(OauthMongoService.class, checkNotNull(authorization));
        return service.signIn();
    }

    @Override
    public LiveData<ApiResponse<LogoutResponseEntity>> logout(@NonNull String accessToken) {
        checkNotNull(accessToken);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), accessToken);
        }
        OauthMongoService service = ServiceGenerator.createService(OauthMongoService.class);
        return service.logout(checkNotNull(accessToken));
    }
}
