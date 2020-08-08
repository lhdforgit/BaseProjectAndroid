/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.owner;

import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;

import javax.inject.Inject;

public class CreatePayoutAccountOwnerViewModel extends AbsTokenViewModel {

    @Inject
    CreatePayoutAccountOwnerViewModel(OauthRepository oauthRepository) {
        super(oauthRepository);
    }
}
