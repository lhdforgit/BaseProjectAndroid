/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository.oauth;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.oauth.LogoutResponseEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/14/18.
 * <p>
 * {@link OauthRepository} Oauth repository
 */
public interface OauthRepository {

    /**
     * Sign in with token format Base64<username:MD5<Password>>
     *
     * @param username username
     * @param password password
     * @return {@link TokenEntity}
     */
    LiveData<Resource<TokenEntity>> token(@NonNull String username, @NonNull String password);

    /**
     * Logout service
     *
     * @return {@link LogoutResponseEntity}
     */
    LiveData<Resource<LogoutResponseEntity>> logout(@NonNull String accessToken);
}
