/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.start;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.resource.StatusNetwork;
import com.hahalolo.android.wallet.data.entities.wallet.N300;
import com.hahalolo.android.wallet.databinding.WalletStartActBinding;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.base.AbsActivity;
import com.hahalolo.android.wallet.presentation.creat.WalletCreateAct;
import com.hahalolo.android.wallet.presentation.main.WalletMainAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

import static com.hahalolo.android.wallet.data.common.resource.StatusNetwork.LOADING;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletStartAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    WalletStartActBinding binding;
    WalletStartViewModel viewModel;

    private N300 personal;
    private N300 business;

    public static Intent getIntent(Context context) {
        return new Intent(context, WalletStartAct.class);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_start_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletStartViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        resetLayout();
        if (HahaloloWalletApplication.getInstance().getToken() != null) {
            viewModel.setToken(HahaloloWalletApplication.getInstance().getToken());
            showProgress();
        }
        initResult();
        initActions();
    }

    private void resetLayout() {
        binding.setStatus(StatusNetwork.ERROR);
        binding.setPersonal(false);
        binding.setBusiness(false);
    }

    private void initActions() {
        binding.createBusinessBt.setOnClickListener(v -> navigateCreateWallet(WalletType.BUSINESS));
        binding.createPersonalBt.setOnClickListener(v -> navigateCreateWallet(WalletType.PERSONAL));
        binding.navigatePersonalBt.setOnClickListener(v -> navigateMainWallet(WalletType.PERSONAL, personal));
        binding.navigateBusinessBt.setOnClickListener(v -> navigateMainWallet(WalletType.BUSINESS, business));
    }

    private void initResult() {
        viewModel.getWalletInfoResult().observe(this, resource -> {
            if (resource != null && resource.data != null) {
                if (resource.statusNetwork == LOADING) {
                    return;
                }
                dismissProgress();
                binding.setStatus(resource.statusNetwork);
                if (checkUnauthorized(resource.data.status)) {
                    if (checkSuccess(resource.data.status)) {
                        List<N300> n300s = resource.data.getElementsEntity();
                        if (n300s != null && !n300s.isEmpty()) {
                            for (N300 n300 : n300s) {
                                // (0: ví cá nhân, 1: ví doanh nghiệp)
                                if (n300.getNn317() == 0) {
                                    personal = n300;
                                    binding.setPersonal(true);
                                } else if (n300.getNn317() == 1) {
                                    business = n300;
                                    binding.setBusiness(true);
                                }
                            }
                            binding.setStatus(StatusNetwork.SUCCESS);
                            return;
                        }
                    }
                }
                binding.setStatus(StatusNetwork.ERROR);
            } else {
                binding.setStatus(StatusNetwork.ERROR);
            }
        });
    }

    private void navigateCreateWallet(@WalletType int type) {
        ActivityUtils.startActivity(WalletCreateAct.getIntent(this, type));
    }

    private void navigateMainWallet(@WalletType int type, N300 n300) {
        if (n300 != null) {
            updateWalletId(n300.getId());
            ActivityUtils.startActivity(WalletMainAct.getIntent(this, type, n300.getId()));
        }
    }

    private void updateWalletId(String id) {
        HahaloloWalletApplication.updateWalletId(id);
    }
}
