/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepository;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 24/11/2018
 */
public class AbsTokenViewModel extends ViewModel {

    private final OauthRepository oauthRepository;

    @Inject
    public AbsTokenViewModel(OauthRepository oauthRepository) {
        this.oauthRepository = oauthRepository;
    }

    @Nullable
    public TokenEntity getToken() {
        return HahaloloWalletApplication.getInstance().getToken();
    }

    @NonNull
    public String getUserIdToken() {
        return getToken() != null ? getToken().getUserId() : "";
    }

    @NonNull
    public String getAccessToken() {
        return getToken() != null ? getToken().getAccessToken() : "";
    }
}