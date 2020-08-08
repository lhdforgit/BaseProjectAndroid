/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount.create.payment_method;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.MenuItem;
import android.view.View;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountOwnerActBinding;
import com.hahalolo.android.wallet.databinding.CreatePayoutAccountPaymentMethodActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form.CreatePayoutAccountTaxFormAct;
import com.hahalolo.android.wallet.presentation.payoutaccountdetail.WalletPayoutAccountDetailAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import javax.inject.Inject;

public class CreatePayoutAccountPaymentMethodAct extends AbsBackActivity {

    @Inject
    ViewModelProvider.Factory factory;

    CreatePayoutAccountPaymentMethodActBinding binding;
    CreatePayoutAccountPaymentMethodViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, CreatePayoutAccountPaymentMethodAct.class);
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.create_payout_account_payment_method_act);
        viewModel = ViewModelProviders.of(this, factory)
                .get(CreatePayoutAccountPaymentMethodViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        bindActions();

    }

    private void bindActions() {
        binding.backBtn.setOnClickListener(v -> navigateTaxForm());
        binding.finishBtn.setOnClickListener(v -> navigateDetail());
    }

    private void navigateTaxForm() {
        ActivityUtils.startActivity(CreatePayoutAccountTaxFormAct.getIntent(this).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
    }

    private void navigateDetail() {
        ActivityUtils.startActivity(WalletPayoutAccountDetailAct.getIntent(this, "payoutID"));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateTaxForm();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
