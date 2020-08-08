/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.creat;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;
import com.hahalolo.android.wallet.databinding.WalletCreateActBinding;
import com.hahalolo.android.wallet.presentation.base.AbsBackActivity;
import com.hahalolo.android.wallet.presentation.start.WalletType;
import com.hahalolo.android.wallet.utils.KeyboardUtils;

import javax.inject.Inject;

import static com.hahalolo.android.wallet.data.common.resource.StatusNetwork.LOADING;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletCreateAct extends AbsBackActivity {

    private static final String WALLET_TYPE_ARGS = "WalletCreateAct-WALLET_TYPE_ARGS";

    @Inject
    ViewModelProvider.Factory factory;

    WalletCreateActBinding binding;
    WalletCreateViewModel viewModel;

    @WalletType
    private int type;

    public static Intent getIntent(Context context, @WalletType int type) {
        Intent intent = new Intent(context, WalletCreateAct.class);
        intent.putExtra(WALLET_TYPE_ARGS, type);
        return intent;
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_create_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletCreateViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        resetLayout();
        initIntent();
        initActions();
        initResult();
    }

    private void initIntent() {
        if (getIntent() != null) {
            type = getIntent().getIntExtra(WALLET_TYPE_ARGS, WalletType.ERROR);
            setupLayout();
        }
    }

    private void setupLayout() {
        if (type == WalletType.PERSONAL) {
            setupPersonal();
        } else if (type == WalletType.BUSINESS) {
            setupBusiness();
        } else {
            resetLayout();
        }
    }

    private void setupBusiness() {
        binding.selectWalletTypeWrapper.setVisibility(View.GONE);
        binding.businessInfoWr.setVisibility(View.VISIBLE);
    }

    private void resetLayout() {
        binding.selectWalletTypeWrapper.setVisibility(View.GONE);
        binding.businessInfoWr.setVisibility(View.GONE);
    }

    private void setupPersonal() {
        binding.selectWalletTypeWrapper.setVisibility(View.GONE);
        binding.businessInfoWr.setVisibility(View.GONE);
    }

    private void initActions() {
        binding.continueBt.setOnClickListener(v -> doCreateWallet());
        binding.walletNameEdt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.walletNameEdt.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                doCreateWallet();
                return true;
            }
            return false;
        });
    }

    private void doCreateWallet() {
        if (binding.walletNameEdt.getText() == null
                || TextUtils.isEmpty(binding.walletNameEdt.getText().toString())) {
            Toast.makeText(WalletCreateAct.this, "Ten wallet khong duoc de trong", Toast.LENGTH_LONG).show();
            return;
        }
        showProgress();

        KeyboardUtils.hideSoftInput(this);
        viewModel.setBody(createBody(binding.walletNameEdt.getText().toString()));
    }

    private WalletBody createBody(String name) {
        WalletBody body = new WalletBody();
        body.setNv301(name);
        body.setNn317(type);
        return body;
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
                        dismissProgress();
                    }
                }
            }
        });
    }
}
