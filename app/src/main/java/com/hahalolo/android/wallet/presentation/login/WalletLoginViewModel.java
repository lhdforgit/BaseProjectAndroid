/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.oauth.LoginEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;
import com.hahalolo.android.wallet.viewmodel.AbsentLiveData;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletLoginViewModel extends AbsTokenViewModel {

    private MutableLiveData<LoginEntity> signIn = new MutableLiveData<>();
    private LiveData<Resource<TokenEntity>> signInResponse;

    @Inject
    public WalletLoginViewModel(OauthRepository oauthRepository) {
        super(oauthRepository);
        signInResponse = Transformations.switchMap(signIn, input -> {
            if (input == null || TextUtils.isEmpty(input.getUsername())
                    || TextUtils.isEmpty(input.getPassword())) {
                return AbsentLiveData.create();
            } else {
                return oauthRepository.token(input.getUsername(), input.getPassword());
            }
        });
    }

    public LiveData<Resource<TokenEntity>> getSignInResponse() {
        return signInResponse;
    }

    public void setSignIn(LoginEntity signIn) {
        this.signIn.setValue(signIn);
    }
}