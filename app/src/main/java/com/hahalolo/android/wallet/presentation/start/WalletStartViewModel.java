/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.start;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;
import com.hahalolo.android.wallet.viewmodel.AbsentLiveData;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletStartViewModel extends AbsTokenViewModel {

    private LiveData<Resource<ResponseN300>> walletInfoResult;
    private MutableLiveData<TokenEntity> token = new MutableLiveData<>();

    @Inject
    WalletStartViewModel(OauthRepository oauthRepository,
                         WalletRepository walletRepository) {
        super(oauthRepository);
        walletInfoResult = Transformations.switchMap(token, input -> {
            if (input != null
                    && !TextUtils.isEmpty(input.getAccessToken())
                    && !TextUtils.isEmpty(input.getUserId())) {
                return walletRepository.walletInfo(null, input.getUserId(), input.getAccessToken());
            } else {
                return AbsentLiveData.create();
            }
        });
    }

    public LiveData<Resource<ResponseN300>> getWalletInfoResult() {
        return walletInfoResult;
    }

    public void setToken(TokenEntity token) {
        this.token.setValue(token);
    }
}
