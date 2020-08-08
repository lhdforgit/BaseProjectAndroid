/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.owner;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.MenuItem;
import android.view.View;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.utils.Strings;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountOwnerActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.infor.CreatePayoutAccountInforAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form.CreatePayoutAccountTaxFormAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import javax.inject.Inject;

public class CreatePayoutAccountOwnerAct extends AbsBackActivity {

    @Inject
    ViewModelProvider.Factory factory;

    CreatePayoutAccountOwnerActBinding binding;
    CreatePayoutAccountOwnerViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, CreatePayoutAccountOwnerAct.class);
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.create_payout_account_owner_act);
        viewModel = ViewModelProviders.of(this, factory).get(CreatePayoutAccountOwnerViewModel.class);

    }

    @Override
    protected void initializeLayout() {
        bindActions();

    }

    private void bindActions() {
        binding.backBtn.setOnClickListener(v -> navigatePaymentInfo());
        binding.nextBtn.setOnClickListener(v -> navigatePaymentTaxForm());
    }

    private void navigatePaymentInfo() {
        ActivityUtils.startActivity(CreatePayoutAccountInforAct.getIntent(this).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
    }

    private void navigatePaymentTaxForm() {
        ActivityUtils.startActivity(CreatePayoutAccountTaxFormAct.getIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                navigatePaymentInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
