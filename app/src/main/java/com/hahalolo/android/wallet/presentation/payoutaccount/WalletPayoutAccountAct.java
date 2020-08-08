/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccount;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ArrayAdapter;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.utils.Strings;
import com.hahalolo.android.wallet.databinding.WalletPayoutAccountActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.infor.CreatePayoutAccountInforAct;
import com.hahalolo.android.wallet.presentation.payoutaccountdetail.adapter.SpinnerAdapter;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletPayoutAccountAct extends AbsBackActivity {

    @Inject
    ViewModelProvider.Factory factory;

    WalletPayoutAccountActBinding binding;
    WalletPayoutAccountViewModel viewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, WalletPayoutAccountAct.class);
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_payout_account_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletPayoutAccountViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        bindActions();
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.history_type_array, R.layout.spinner_item);
//        String[] list = getResources().getStringArray(R.array.history_type_array);
//        Strings.log("getStringArray ", list);
//        List<ModelTest> modelTests = new ArrayList<>();
//        if (list != null) {
//            for (String s : list) {
//                modelTests.add(new ModelTest(s));
//            }
//        }

//        SpinnerAdapter<ModelTest> adapter1 = new SpinnerAdapter<>(this, R.layout.spinner_dropdown_item, modelTests);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setSelection(0);
    }

    private void bindActions() {
        binding.createPayoutAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateCreatePayoutAccount();
            }
        });

        binding.startDateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.endDateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void navigateCreatePayoutAccount() {
        ActivityUtils.startActivity(CreatePayoutAccountInforAct.getIntent(this));

    }

    public class ModelTest implements SpinnerAdapter.SpinnerItem<ModelTest> {
        String text;

        public ModelTest(String text) {
            this.text = text;
        }

        public String getText() {
            return text;

        }

        @Override
        public ModelTest getModel() {
            return this;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
