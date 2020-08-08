/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payout;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.WalletPayoutActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletPayoutAct extends AbsBackActivity {

    @Inject
    ViewModelProvider.Factory factory;

    WalletPayoutActBinding binding;
    WalletPayoutViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, WalletPayoutAct.class);
    }

    @Override
    protected void initActionBar() {

    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_payout_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletPayoutViewModel.class);
    }

    @Override
    protected void initializeLayout() {

    }
}
