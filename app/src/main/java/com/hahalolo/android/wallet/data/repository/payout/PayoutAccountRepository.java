/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository.payout;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;

import retrofit2.http.Body;
import retrofit2.http.Query;

public interface PayoutAccountRepository {

    LiveData<Resource<ResponseN300>> payoutById(@NonNull String pn300,
                                                @NonNull String pn450,
                                                @NonNull String uClientId,
                                                @NonNull String accessToken);

    LiveData<Resource<ResponseN300>> createPayout(@NonNull PayoutBody body,
                                                  @NonNull String uClientId,
                                                  @NonNull String accessToken);
}
