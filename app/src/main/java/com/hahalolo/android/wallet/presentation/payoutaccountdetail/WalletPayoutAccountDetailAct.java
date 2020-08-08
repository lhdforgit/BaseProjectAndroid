/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccountdetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.common.resource.StatusNetwork;
import com.hahalolo.android.wallet.data.entities.wallet.N300;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.databinding.WalletPayoutAccountDetailActBinding;
import com.hahalolo.android.wallet.databinding.WalletPayoutActBinding;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payout.WalletPayoutViewModel;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletPayoutAccountDetailAct extends AbsBackActivity {

    private static final String PAYOUT_ID = "WalletPayoutAccountDetailAct-payoutId";

    @Inject
    ViewModelProvider.Factory factory;

    WalletPayoutAccountDetailActBinding binding;
    WalletPayoutAccountDetailViewModel viewModel;

    public static Intent getIntent(@NonNull Context context, @NonNull String payoutId) {
        Intent intent = new Intent(context, WalletPayoutAccountDetailAct.class);
        intent.putExtra(PAYOUT_ID, payoutId);
        return intent;
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_payout_account_detail_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletPayoutAccountDetailViewModel.class);

    }

    @Override
    protected void initializeLayout() {
        bindActions();
        initIntent();
        initHandlePayoutDetail();
    }

    private void initHandlePayoutDetail() {
        viewModel.getResponseN300().observe(this, new Observer<Resource<ResponseN300>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseN300> resource) {
                if (resource != null) {
                    if (resource.statusNetwork == StatusNetwork.SUCCESS) {

                        if (resource.data != null
                                && checkSuccess(resource.data.status)
                                && checkUnauthorized(resource.data.status)) {
                            N300 n300 = resource.data.getFirstElement();
                            if (n300 != null) {
                                updatePayoutAccountDetail(n300);
                            }
                        }
                    } else if (resource.statusNetwork == StatusNetwork.LOADING) {

                    } else {
                        errorNetwork();
                    }
                }
            }
        });
    }

    private void updatePayoutAccountDetail(N300 n300) {


    }

    private void initIntent() {
        if (getIntent() != null) {
            viewModel.updatePayoutId(getIntent().getStringExtra(PAYOUT_ID));
        }
    }

    private void bindActions() {

    }
}
