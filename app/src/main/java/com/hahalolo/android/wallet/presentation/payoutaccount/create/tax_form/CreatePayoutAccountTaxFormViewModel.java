/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form;

import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.presentation.base.AbsTokenViewModel;

import javax.inject.Inject;

public class CreatePayoutAccountTaxFormViewModel extends AbsTokenViewModel {
    @Inject
    CreatePayoutAccountTaxFormViewModel(OauthRepository oauthRepository) {
        super(oauthRepository);
    }
}
