/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.creat;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;
import com.hahalolo.android.wallet.viewmodel.AbsentLiveData;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletCreateViewModel extends AbsTokenViewModel {

    private LiveData<Resource<ResponseN300>> walletInfoResult;
    private MutableLiveData<WalletBody> body = new MutableLiveData<>();

    @Inject
    public WalletCreateViewModel(OauthRepository oauthRepository,
                                 WalletRepository walletRepository) {
        super(oauthRepository);
        walletInfoResult = Transformations.switchMap(body, input -> {
            if (input != null
                    && !TextUtils.isEmpty(getAccessToken())
                    && !TextUtils.isEmpty(getUserIdToken())) {
                return walletRepository.createWallet(input, getAccessToken(), getUserIdToken());
            }
            return AbsentLiveData.create();
        });
    }

    public LiveData<Resource<ResponseN300>> getWalletInfoResult() {
        return walletInfoResult;
    }

    public void setBody(WalletBody body) {
        this.body.setValue(body);
    }
}
