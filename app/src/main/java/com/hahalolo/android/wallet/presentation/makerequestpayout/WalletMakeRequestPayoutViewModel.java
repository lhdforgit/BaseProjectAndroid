/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.makerequestpayout;

import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletMakeRequestPayoutViewModel extends AbsTokenViewModel {

    @Inject
    public WalletMakeRequestPayoutViewModel(OauthRepository oauthRepository) {
        super(oauthRepository);
    }
}
