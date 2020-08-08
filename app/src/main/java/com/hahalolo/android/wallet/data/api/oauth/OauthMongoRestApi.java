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

import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.entities.oauth.LogoutResponseEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 */
public interface OauthMongoRestApi {

    /**
     * Sign in with authorization created by username and password
     *
     * @param authorization base64(username:md5(password))
     * @return {@link TokenEntity}
     */
    LiveData<ApiResponse<TokenEntity>> signIn(@NonNull String authorization);

    /**
     * Logout service
     *
     * @param accessToken access token of user
     * @return {@link com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity}
     */
    LiveData<ApiResponse<LogoutResponseEntity>> logout(@NonNull String accessToken);
}
