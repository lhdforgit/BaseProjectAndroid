/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog;
import com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.presentation.login.WalletLoginAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;
import com.hahalolo.android.wallet.utils.KeyboardUtils;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 */
public abstract class AbsActivity extends DaggerAppCompatActivity implements TokenHandle {

    public MaterialDialog dialog;

    public ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable extended screen features. This must be called before setContentView().
        // May be called as many times as desired as long as it is before setContentView().
        // If not called, no extended features will be available.
        // You can not turn off a feature once it is requested.
        // You cannot use other title features with FEATURE_CUSTOM_TITLE.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        if (hideStatusbar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        initializeBindingViewModel();
        initializeLayout();
        initProgress();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    protected boolean hideStatusbar() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        try {
            KeyboardUtils.hideSoftInput(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        try {
            KeyboardUtils.unregisterSoftInputChangedListener(this);
            dialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    protected abstract void initializeBindingViewModel();

    protected abstract void initializeLayout();

    public boolean checkUnauthorized(StatusMongoEntity status) {
        if (status != null && status.isUnauthorized()) {
            if (dialog == null) {
                dialog = new MaterialDialog(this);
            } else if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog.title(R.string.error_unauthorized_title, null);
            dialog.message(R.string.error_unauthorized_message, null);
            dialog.positiveButton(R.string.error_positive_action_ok, null, materialDialog -> {
                dialog.dismiss();
                navigateLogin();
                return null;
            });
            dialog.cancelable(false);
            dialog.show();
            return false;
        }
        return true;
    }

    public boolean checkSuccess(StatusMongoEntity status) {
        return checkSuccess(status, ErrorType.TOAST);
    }

    public boolean checkSuccess(StatusMongoEntity status, @ErrorType int type) {
        if (status == null) {
            return false;
        }
        if (status.isSuccessCode()) {
            return true;
        }
        notifyErrorWithStatusCode(status.code, type);
        return false;
    }

    public void errorNetwork() {
        errorNetwork(ErrorType.TOAST, false);
    }

    public void errorNetwork(@ErrorType int type) {
        errorNetwork(type, false);

    }

    public void errorNetwork(boolean notfound) {
        errorNetwork(ErrorType.TOAST, notfound);
    }

    public void errorNetwork(@ErrorType int type, boolean notfound) {
        switch (type) {
            case ErrorType.TOAST:
                Toast.makeText(this, notfound ? R.string.error_not_found_message
                        : R.string.error_internal_message, Toast.LENGTH_LONG).show();
                break;
            case ErrorType.DIALOG:
                if (dialog == null) {
                    dialog = new MaterialDialog(this);
                } else if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                dialog.message(notfound ? R.string.error_not_found_message
                        : R.string.error_internal_message, null);
                dialog.positiveButton(R.string.error_positive_action_ok, null, materialDialog -> {
                    dialog.dismiss();
                    return null;
                });
                dialog.cancelable(true);
                dialog.show();
                break;
        }
    }

    public void notifyErrorWithStatusCode(int status, @ErrorType int type) {
        @StringRes
        int title = 0;
        @StringRes
        int message;
        boolean cancelable = true;

        switch (status) {
            case StatusMongoEntity.Status.BAD_REQUEST:
                title = R.string.error_bad_request_title;
                message = R.string.error_bad_request_message;
                break;
            case StatusMongoEntity.Status.UNAUTHORIZED:
                title = R.string.error_unauthorized_title;
                message = R.string.error_unauthorized_message;
                cancelable = false;
                break;
            case StatusMongoEntity.Status.FORBIDDEN:
                title = R.string.error_forbidden_title;
                message = R.string.error_forbidden_message;
                break;
            case StatusMongoEntity.Status.NOT_FOUND:
                title = R.string.error_not_found_title;
                message = R.string.error_not_found_message;
                break;
            case StatusMongoEntity.Status.METHOD_NOT_ALLOWED:
                title = R.string.error_not_allowed_title;
                message = R.string.error_not_allowed_message;
                break;
            case StatusMongoEntity.Status.NOT_ACCEPTTABLE:
                title = R.string.error_not_accepttable_title;
                message = R.string.error_not_accepttable_message;
                break;
            case StatusMongoEntity.Status.PROXY_AUTHENTICATION_REQUIRED:
                title = R.string.error_proxy_require_title;
                message = R.string.error_proxy_require_message;
                break;
            case StatusMongoEntity.Status.REQUEST_TIMEOUT:
                message = R.string.error_request_timeout_message;
                break;
            case StatusMongoEntity.Status.CONFLICT:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.LENGTH_REQUIRE:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.PRECONFITION_FAILED:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.REQUEST_ENTITY_TOO_LARGE:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.REQUEST_URI_TOO_LONG:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.UNSUPPORTED_MEDIA_TYPE:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.REQUEST_RANGE_NOT_SATISFIABLE:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.EXPECTATION_FAILED:
                message = R.string.error_network_request_failed_message;
                break;
            case StatusMongoEntity.Status.INTERNAL_SERVER_ERROR:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.NOT_IMPLEMENTED:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.BAD_GATEWAY:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.SERVICE_UNAVAILABLE:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.GATEWAY_TIMEOUT:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.HTTP_VERSION_NOT_SUPPORTED:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.NO_ENTITY:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.NO_PARAM:
                message = R.string.error_no_param_message;
                break;
            case StatusMongoEntity.Status.EXIST_MAIL:
                message = R.string.error_email_exist_message;
                break;
            case StatusMongoEntity.Status.EXIST_USER:
                message = R.string.error_phone_exist_message;
                break;
            case StatusMongoEntity.Status.EXIST_PAGE:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.FRIENDED:
                message = R.string.error_internal_message;
                break;
            case StatusMongoEntity.Status.UNKNOW:
                message = R.string.error_internal_message;
                break;
            default:
                message = R.string.error_internal_message;
                break;
        }

        if (type == ErrorType.TOAST) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            try {
                if (dialog == null) {
                    dialog = new MaterialDialog(this);
                } else if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                if (title != 0) {
                    dialog.title(title, null);
                }
                dialog.message(message, null);
                dialog.positiveButton(android.R.string.ok, null, materialDialog -> {
                    dialog.dismiss();
                    return null;
                });
                dialog.cancelable(cancelable);
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public TokenEntity getToken() {
        return HahaloloWalletApplication.getInstance().getToken();
    }

    @NonNull
    @Override
    public String getUserIdToken() {
        return getToken() != null ? getToken().getUserId() : "";
    }

    @NonNull
    @Override
    public String getAccessToken() {
        return getToken() != null ? getToken().getAccessToken() : "";
    }

    public void navigateLogin() {
        startActivity(WalletLoginAct.getIntent(this));
        ActivityUtils.finishAllActivities();
    }

    public void initProgress() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            //progress.setMessage(getString(R.string.loading));
            // Cancel dismiss progress when user click anywhere
            progress.setCancelable(false);
        }
    }

    public void showProgress() {
        if (progress != null && !isFinishing()) {
            progress.show();
        }
    }

    public void dismissProgress() {
        if (progress != null) {
            progress.dismiss();
        }
    }
}
