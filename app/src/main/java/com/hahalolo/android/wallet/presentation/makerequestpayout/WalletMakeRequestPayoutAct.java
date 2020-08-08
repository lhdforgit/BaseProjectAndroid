/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.makerequestpayout;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.WalletMakeRequestPayoutActBinding;
import com.hahalolo.android.wallet.databinding.WalletPayoutActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payout.WalletPayoutViewModel;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletMakeRequestPayoutAct extends AbsBackActivity {

    @Inject
    ViewModelProvider.Factory factory;

    WalletMakeRequestPayoutActBinding binding;
    WalletMakeRequestPayoutViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, WalletMakeRequestPayoutAct.class);
    }

    @Override
    protected void initActionBar() {
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_make_request_payout_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletMakeRequestPayoutViewModel.class);
    }

    @Override
    protected void initializeLayout() {

    }
}
