/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.resource.StatusNetwork;
import com.hahalolo.android.wallet.data.common.utils.Strings;
import com.hahalolo.android.wallet.data.entities.oauth.LoginEntity;
import com.hahalolo.android.wallet.databinding.WalletLoginActBinding;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.base.AbsActivity;
import com.hahalolo.android.wallet.presentation.base.ErrorType;
import com.hahalolo.android.wallet.presentation.start.WalletStartAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;
import com.hahalolo.android.wallet.utils.KeyboardUtils;

import javax.inject.Inject;

/**
 * @author ngannd
 * Create by ngannd on 21/05/2019
 */
public class WalletLoginAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    WalletLoginActBinding binding;
    WalletLoginViewModel viewModel;

    private ProgressDialog progress;

    public static Intent getIntent(Context context) {
        return new Intent(context, WalletLoginAct.class);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.wallet_login_act);
        viewModel = ViewModelProviders.of(this, factory).get(WalletLoginViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        initActions();
        initSignInResponse();
    }

    private void initActions() {
        binding.loginBt.setOnClickListener(v -> doSignIn());
        binding.passwordEdt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.passwordEdt.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                doSignIn();
                return true;
            }
            return false;
        });
    }

    /**
     * Do sign in
     * Check username pattern
     * Check password pattern
     * Hide keyboard
     * UpdateChat sign in observer
     */
    private void doSignIn() {
        binding.usernameEdt.setError(null);
        binding.passwordEdt.setError(null);

        if (binding.usernameEdt.getText() == null
                || TextUtils.isEmpty(binding.usernameEdt.getText().toString())) {
            Toast.makeText(WalletLoginAct.this, getString(R.string.sign_in_username_error_null), Toast.LENGTH_LONG).show();
            return;
        } else if (!Strings.isValidPhone(binding.usernameEdt.getText().toString())) {
            if (!Strings.isValidEmail(binding.usernameEdt.getText().toString())) {
                Toast.makeText(WalletLoginAct.this, getString(R.string.sign_in_username_error_phone_email_regex), Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (binding.passwordEdt.getText() == null || TextUtils.isEmpty(binding.passwordEdt.getText().toString())) {
            Toast.makeText(WalletLoginAct.this, getString(R.string.sign_in_password_error_null), Toast.LENGTH_LONG).show();
            return;
        }

        KeyboardUtils.hideSoftInput(this);
        viewModel.setSignIn(new LoginEntity(binding.usernameEdt.getText().toString(),
                binding.passwordEdt.getText().toString()));
    }

    private void initSignInResponse() {
        viewModel.getSignInResponse().observe(this, resource -> {
            if (resource != null) {
                if (resource.statusNetwork == StatusNetwork.LOADING) {
                    showProgress();
                } else if (resource.statusNetwork == StatusNetwork.SUCCESS) {
                    dismissProgress();
                    if (resource.data != null) {
                        if (!TextUtils.isEmpty(resource.data.getAccessToken())
                                && !TextUtils.isEmpty(resource.data.getUserId())) {
                            HahaloloWalletApplication.getInstance().setToken(resource.data);
                            navigateStartWallet();
                            return;
                        }
                        notifyErrorWithStatusCode(StatusNetwork.ERROR, ErrorType.TOAST);
                    }
                } else {
                    dismissProgress();
                    notifyErrorWithStatusCode(StatusNetwork.ERROR, ErrorType.TOAST);
                }
            } else {
                notifyErrorWithStatusCode(StatusNetwork.ERROR, ErrorType.TOAST);
                dismissProgress();
            }
        });
    }

    private void navigateStartWallet() {
        ActivityUtils.startActivity(WalletStartAct.getIntent(this));
        finish();
    }
}
