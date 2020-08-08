/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.resource.StatusNetwork;
import com.hahalolo.android.wallet.data.entities.wallet.N300;
import com.hahalolo.android.wallet.databinding.WalletMainActBinding;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.WalletPayoutAccountAct;
import com.hahalolo.android.wallet.presentation.start.WalletStartAct;
import com.hahalolo.android.wallet.presentation.start.WalletType;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

import static com.hahalolo.android.wallet.data.common.resource.StatusNetwork.LOADING;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletMainAct extends AbsBackActivity {

    private static final String WALLET_TYPE_ARGS = "WalletMainAct-WALLET_TYPE_ARGS";
    private static final String WALLET_ID_ARGS = "WalletMainAct-WALLET_ID_ARGS";

    @Inject
    ViewModelProvider.Factory factory;

    WalletMainActBinding binding;
    WalletMainViewModel viewModel;

    @WalletType
    private int type;
    private String id;

    private N300 n300;

    public static Intent getIntent(Context context, @WalletType int type, String id) {
        Intent intent = new Intent(context, WalletMainAct.class);
        intent.putExtra(WALLET_TYPE_ARGS, type);
        intent.putExtra(WALLET_ID_ARGS, id);
        return intent;
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_main_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletMainViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        initIntent();
        initResult();
        initActions();
    }

    private void initActions() {
        binding.payoutAccountBt.setOnClickListener(v -> navigatePayoutAccount());
    }

    private void initIntent() {
        if (getIntent() != null) {
            type = getIntent().getIntExtra(WALLET_TYPE_ARGS, WalletType.ERROR);
            id = getIntent().getStringExtra(WALLET_ID_ARGS);
            if (HahaloloWalletApplication.getInstance().getToken() != null) {
                viewModel.setId(id);
                showProgress();
            }
        }
    }

    private void initResult() {
        viewModel.getWalletInfoResult().observe(this, resource -> {
            if (resource != null && resource.data != null) {
                if (resource.statusNetwork == LOADING) {
                    return;
                }
                dismissProgress();
                if (checkUnauthorized(resource.data.status)) {
                    if (checkSuccess(resource.data.status)) {
                        n300 = resource.data.getFirstElement();
                        binding.setN300(n300);
                    }
                }
            }
        });
    }

    private void navigatePayoutAccount() {
        ActivityUtils.startActivity(WalletPayoutAccountAct.getIntent(this));
    }
}
