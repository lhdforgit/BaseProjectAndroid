/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository.wallet;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;

import retrofit2.Response;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public interface WalletRepository {

    LiveData<Resource<ResponseN300>> createPayout(@NonNull PayoutBody body,
                                                  @NonNull String accessToken,
                                                  @NonNull String uClientId);

    LiveData<Resource<ResponseN300>> createWallet(@NonNull WalletBody body,
                                                  @NonNull String accessToken,
                                                  @NonNull String uClientId);

    /**
     * Search all then no set param(Default get info follow client id user login)
     * Search by id
     * .id: id wallet(N300)
     * Wallet info
     * *.nn302:CurrentAvailable
     * *.nn303:IncomeWaiting
     * *.nn304:IncomePending
     * *.nn305:IncomeHolding
     * *.nn306:OutcomeWaiting
     * *.nn307:OutcomePending
     * *.nn308:OutcomeHolding
     * *.fn302 : hostory order
     * ***..ph100: type order(Hotel, tour, shop)
     * ***..dl146 : Ngày cập nhật tiền
     * ***..nn306: số tiền
     * ***..nn303 : nội dung (0 : user thanh toán order, 1 Confirm order)
     * ***..pp100 : page ID
     *
     * @param id          Wallet ID
     * @param uClientId
     * @param accessToken
     * @return
     */
    LiveData<Resource<ResponseN300>> walletInfo(@Nullable String id,
                                                @NonNull String uClientId,
                                                @NonNull String accessToken);

}
