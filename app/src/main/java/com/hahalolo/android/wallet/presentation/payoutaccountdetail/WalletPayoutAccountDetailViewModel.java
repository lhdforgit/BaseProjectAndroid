/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccountdetail;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutDetailBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.data.repository.payout.PayoutAccountRepository;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepository;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;
import com.hahalolo.android.wallet.viewmodel.AbsentLiveData;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletPayoutAccountDetailViewModel extends AbsTokenViewModel {
    private LiveData<Resource<ResponseN300>> responseN300;
    private MutableLiveData<String> payoutId = new MutableLiveData<>();

    @Inject
    public WalletPayoutAccountDetailViewModel(OauthRepository oauthRepository, PayoutAccountRepository payoutAccountRepository) {
        super(oauthRepository);

        responseN300 = Transformations.switchMap(payoutId, input -> {
            if (!TextUtils.isEmpty(input)
                    && !TextUtils.isEmpty(HahaloloWalletApplication.getWalletId())
                    && !TextUtils.isEmpty(getAccessToken())
                    && !TextUtils.isEmpty(getUserIdToken())) {
                return payoutAccountRepository.payoutById(HahaloloWalletApplication.getWalletId(),
                        input,
                        getUserIdToken(),
                        getAccessToken());
            }
            return AbsentLiveData.create();
        });
    }

    public void updatePayoutId(String payoutId) {
        this.payoutId.setValue(payoutId);
    }

    public LiveData<Resource<ResponseN300>> getResponseN300() {
        return responseN300;
    }
}
