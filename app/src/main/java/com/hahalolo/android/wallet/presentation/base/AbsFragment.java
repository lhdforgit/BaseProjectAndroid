/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog;
import com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;
import com.hahalolo.android.wallet.utils.ActivityUtils;

import dagger.android.support.DaggerFragment;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/24/18.
 */
public abstract class AbsFragment extends DaggerFragment {

    private MaterialDialog dialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViewModel();
        initializeLayout();
    }

    @Override
    public void onResume() {
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
    public void onDestroy() {
        dialog = null;
        super.onDestroy();
    }

    protected abstract void initializeViewModel();

    protected abstract void initializeLayout();

    @Nullable
    public TokenEntity getToken() {
        return HahaloloWalletApplication.getInstance().getToken();
    }

    @NonNull
    public String getUserIdToken() {
        return getToken() != null ? getToken().getUserId() : "";
    }

    @NonNull
    public String getAccessToken() {
        return getToken() != null ? getToken().getAccessToken() : "";
    }

    public boolean checkUnauthorized(StatusMongoEntity status) {
        if (status != null && status.isUnauthorized()) {
            if (getActivity() != null) {
                if (dialog == null) {
                    dialog = new MaterialDialog(getActivity());
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
            }
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
        if (getActivity() != null) {
            notifyErrorWithStatusCode(status.code, type);
        }
        return false;
    }

    public void errorNetwork() {
        errorNetwork(ErrorType.TOAST, false);
    }

    public void errorNetwork(boolean notfound) {
        errorNetwork(ErrorType.TOAST, notfound);
    }

    public void errorNetwork(@ErrorType int type, boolean notfound) {
        if (getActivity() != null) {
            switch (type) {
                case ErrorType.TOAST:
                    Toast.makeText(getContext(), notfound ? R.string.error_not_found_message
                            : R.string.error_internal_message, Toast.LENGTH_LONG).show();
                    break;
                case ErrorType.DIALOG:
                    if (dialog == null) {
                        dialog = new MaterialDialog(getActivity());
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
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        } else {
            try {
                if (dialog == null) {
                    dialog = new MaterialDialog(getActivity());
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

    public void navigateLogin() {
        ActivityUtils.finishAllActivities();
    }
}