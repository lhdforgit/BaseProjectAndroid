/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api.wallet;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.hahalolo.android.wallet.BuildConfig;
import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.api.utils.ServiceGenerator;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutBody;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutRequestBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
@Singleton
public class WalletMongoRestApiImpl implements WalletMongoRestApi {

    private WalletMongoService service;

    @Inject
    WalletMongoRestApiImpl() {
        service = ServiceGenerator.createService(WalletMongoService.class);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> createPayout(@NonNull PayoutBody body,
                                                            @NonNull String accessToken,
                                                            @NonNull String uClientId) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        body.setuClientId(uClientId);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), new Gson().toJson(body));
        }
        return service.createPayout(body, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> createWallet(@NonNull WalletBody body,
                                                            @NonNull String accessToken,
                                                            @NonNull String uClientId) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        body.setuClientId(uClientId);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), new Gson().toJson(body));
        }
        return service.createWallet(body, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> walletInfo(@NonNull String id,
                                                          @NonNull String uClientId,
                                                          @NonNull String accessToken) {
        checkNotNull(accessToken);
        checkNotNull(uClientId);
        return service.walletInfo(id, uClientId, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> payoutInfo(@NonNull String id,
                                                          @NonNull String uClientId,
                                                          @NonNull String accessToken) {
        checkNotNull(id);
        checkNotNull(accessToken);
        checkNotNull(uClientId);
        return service.payoutInfo(id, uClientId, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> payoutById(@NonNull String pn300,
                                                          @NonNull String pn450,
                                                          @NonNull String uClientId,
                                                          @NonNull String accessToken) {
        checkNotNull(pn300);
        checkNotNull(pn450);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        return service.payoutById(pn300, pn450, uClientId, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> removePayoutAcc(@NonNull PayoutBody body,
                                                               @NonNull String uClientId,
                                                               @NonNull String accessToken) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        body.setuClientId(uClientId);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), new Gson().toJson(body));
        }
        return service.removePayoutAcc(body, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> setDefaultAcc(@NonNull PayoutBody body,
                                                             @NonNull String uClientId,
                                                             @NonNull String accessToken) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        return service.setDefaultAcc(body, accessToken);
    }

    @Override
    public LiveData<ApiResponse<ResponseN300>> sendRequest(@NonNull PayoutRequestBody body,
                                                           @NonNull String uClientId,
                                                           @NonNull String accessToken) {
        checkNotNull(body);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        body.setuClientId(uClientId);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), new Gson().toJson(body));
        }
        return service.sendRequest(body, accessToken);
    }

    @Override
    public Call<ResponseN300> historyPayout(@NonNull String pn100,
                                            @NonNull String pn300,
                                            String strDate,
                                            String endDate,
                                            String limit,
                                            @NonNull String uClientId,
                                            @NonNull String accessToken) {
        checkNotNull(pn100);
        checkNotNull(pn300);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        return service.historyPayout(pn100, pn300, strDate, endDate, limit, uClientId, accessToken);
    }

    @Override
    public Call<ResponseN300> historyPayoutAfter(@NonNull String pn100,
                                                 @NonNull String pn300,
                                                 String strDate,
                                                 String endDate,
                                                 String limit,
                                                 String offset,
                                                 @NonNull String uClientId,
                                                 @NonNull String accessToken) {
        checkNotNull(pn100);
        checkNotNull(pn300);
        checkNotNull(accessToken);
        checkNotNull(uClientId);

        return service.historyPayoutAfter(pn100, pn300, strDate, endDate, limit, offset, uClientId, accessToken);
    }
}
