/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.infor;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountInforActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import javax.inject.Inject;

public class CreatePayoutAccountInforAct extends AbsBackActivity {
    @Inject
    ViewModelProvider.Factory factory;

    CreatePayoutAccountInforActBinding binding;
    CreatePayoutAccountInforViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, CreatePayoutAccountInforAct.class);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.create_payout_account_infor_act);
        viewModel = ViewModelProviders.of(this, factory).get(CreatePayoutAccountInforViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        bindActions();
    }

    private void bindActions() {
        binding.countrySelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.countryTypeSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.stateProvinceRegionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.taxidTypeSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateOwner();
            }
        });
    }

    private void navigateOwner() {
        ActivityUtils.startActivity(CreatePayoutAccountOwnerAct.getIntent(this));
        finish();
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }
}
