/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.MenuItem;
import android.view.View;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountOwnerActBinding;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountTaxFormActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.payment_method.CreatePayoutAccountPaymentMethodAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.payment_method.CreatePayoutAccountPaymentMethodViewModel;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import javax.inject.Inject;

public class CreatePayoutAccountTaxFormAct  extends AbsBackActivity {


    @Inject
    ViewModelProvider.Factory factory;

    CreatePayoutAccountTaxFormActBinding binding;
    CreatePayoutAccountTaxFormViewModel viewModel;

    public static Intent getIntent(Context context){
        return new Intent(context, CreatePayoutAccountTaxFormAct.class);
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.create_payout_account_tax_form_act);
        viewModel = ViewModelProviders.of(this, factory)
                .get(CreatePayoutAccountTaxFormViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        bindActions();

    }

    private void bindActions() {
        binding.nextBtn.setOnClickListener(v -> navigatePaymentMethod());
        binding.backBtn.setOnClickListener(v -> navigatePaymentOwner());

    }

    private void navigatePaymentOwner() {
        ActivityUtils.startActivity(CreatePayoutAccountOwnerAct.getIntent(this).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
    }

    private void navigatePaymentMethod() {
        ActivityUtils.startActivity(CreatePayoutAccountPaymentMethodAct.getIntent(this));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                navigatePaymentOwner();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
