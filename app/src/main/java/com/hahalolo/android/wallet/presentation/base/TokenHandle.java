/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hahalolo.android.wallet.data.entities.token.TokenEntity;

/**
 * @author ngannd
 * Create by ngannd on 24/11/2018
 */
public interface TokenHandle {

    /**
     * Get token login
     * When token is null, navigate login with notification sign-in has expired
     *
     * @return {@link TokenEntity}
     */
    @Nullable
    TokenEntity getToken();

    /**
     * Get id of user login from {@link TokenEntity}
     * When id is empty, navigate login with notification sign-in has expired
     *
     * @return id of user
     */
    @NonNull
    String getUserIdToken();

    /**
     * Get access token of user login from {@link TokenEntity}
     * When access token is empty, navigate login with notification sign-in has expired
     *
     * @return id of user
     */
    @NonNull
    String getAccessToken();
}