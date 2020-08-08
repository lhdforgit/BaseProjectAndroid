/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository.wallet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.api.wallet.WalletMongoRestApi;
import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.hahalolo.android.wallet.data.common.resource.StatusNetwork.ERROR;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
@Singleton
public class WalletRepositoryImpl implements WalletRepository {

    private final WalletMongoRestApi mongoRestApi;

    @Inject
    WalletRepositoryImpl(WalletMongoRestApi mongoRestApi) {
        this.mongoRestApi = mongoRestApi;
    }

    @Override
    public LiveData<Resource<ResponseN300>> createPayout(@NonNull PayoutBody body,
                                                         @NonNull String accessToken,
                                                         @NonNull String uClientId) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        MediatorLiveData<Resource<ResponseN300>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<ResponseN300>> response = mongoRestApi.createPayout(body, accessToken, uClientId);
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(ERROR, "Response null", ResponseN300.error(ERROR)));
            } else if (resource.isSuccessful() && resource.body != null) {
                result.setValue(Resource.success(resource.body));
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, ResponseN300.error(resource.code)));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<ResponseN300>> createWallet(@NonNull WalletBody body,
                                                         @NonNull String accessToken,
                                                         @NonNull String uClientId) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        MediatorLiveData<Resource<ResponseN300>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<ResponseN300>> response = mongoRestApi.createWallet(body, accessToken, uClientId);
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(ERROR, "Response null", ResponseN300.error(ERROR)));
            } else if (resource.isSuccessful() && resource.body != null) {
                result.setValue(Resource.success(resource.body));
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, ResponseN300.error(resource.code)));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<ResponseN300>> walletInfo(@Nullable String id, @NonNull String uClientId, @NonNull String accessToken) {
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        MediatorLiveData<Resource<ResponseN300>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<ResponseN300>> response = mongoRestApi.walletInfo(id, uClientId, accessToken);
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(ERROR, "Response null", ResponseN300.error(ERROR)));
            } else if (resource.isSuccessful() && resource.body != null) {
                result.setValue(Resource.success(resource.body));
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, ResponseN300.error(resource.code)));
            }
        });
        return result;
    }


}
