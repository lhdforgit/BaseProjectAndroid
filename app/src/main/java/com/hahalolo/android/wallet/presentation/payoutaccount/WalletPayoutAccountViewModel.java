/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount;

import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletPayoutAccountViewModel extends AbsTokenViewModel {

    @Inject
    public WalletPayoutAccountViewModel(OauthRepository oauthRepository) {
        super(oauthRepository);
    }
}
