/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.main;

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
public class WalletMainViewModel extends AbsTokenViewModel {

    private LiveData<Resource<ResponseN300>> walletInfoResult;
    private MutableLiveData<String> id = new MutableLiveData<>();

    @Inject
    WalletMainViewModel(OauthRepository oauthRepository,
                        WalletRepository walletRepository) {
        super(oauthRepository);
        walletInfoResult = Transformations.switchMap(id, input -> {
            if (input != null
                    && !TextUtils.isEmpty(getAccessToken())
                    && !TextUtils.isEmpty(getUserIdToken())) {
                return walletRepository.walletInfo(input, getUserIdToken(), getAccessToken());
            } else {
                return AbsentLiveData.create();
            }
        });
    }

    public LiveData<Resource<ResponseN300>> getWalletInfoResult() {
        return walletInfoResult;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
}
