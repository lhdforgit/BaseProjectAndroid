/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository.oauth;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.google.common.base.Joiner;
import com.hahalolo.android.wallet.data.api.oauth.OauthMongoRestApi;
import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.cache.currency.CurrencyPref;
import com.hahalolo.android.wallet.data.common.resource.Resource;
import com.hahalolo.android.wallet.data.common.resource.StatusNetwork;
import com.hahalolo.android.wallet.data.entities.currency.CurrencyEntity;
import com.hahalolo.android.wallet.data.entities.oauth.LogoutResponseEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.data.executor.AppExecutors;

import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.hahalolo.android.wallet.data.common.utils.Strings.base64;
import static com.hahalolo.android.wallet.data.common.utils.Strings.md5;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 */
@Singleton
public class OauthRepositoryImpl implements OauthRepository {

    private final OauthMongoRestApi restApi;
    private final AppExecutors appExecutors;
    private final CurrencyPref currencyPref;

    @Inject
    OauthRepositoryImpl(OauthMongoRestApi restApi,
                        CurrencyPref currencyPref,
                        AppExecutors appExecutors) {
        this.restApi = restApi;
        this.currencyPref = currencyPref;
        this.appExecutors = appExecutors;
    }

    /**
     * Sign in with token format Base64<username:MD5<Password>>
     *
     * @param username username
     * @param password password
     * @return {@link TokenEntity}
     */
    @Override
    public LiveData<Resource<TokenEntity>> token(@NonNull String username, @NonNull String password) {
        String md5Pass = md5(password);
        String token = base64(Joiner.on(":").join(username, md5Pass));
        MediatorLiveData<Resource<TokenEntity>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<TokenEntity>> response = restApi.signIn(token);
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(StatusNetwork.ERROR, "Response null", null));
            } else if (resource.isSuccessful() && resource.body != null) {
                result.setValue(Resource.success(resource.body));
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, null));
            }
        });
        return result;
    }

    private void initCurrency(@NonNull TokenEntity item) {
        try {
            Locale locale = new Locale("", item.getCountryCode());
            Currency currency = Currency.getInstance(locale);
            CurrencyEntity currencyEntity = new CurrencyEntity();
            currencyEntity.setName(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT
                    ? currency.getDisplayName(locale) : "");
            currencyEntity.setCode(currency.getCurrencyCode());
            currencyPref.insertTargetCurrency(currencyEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LiveData<Resource<LogoutResponseEntity>> logout(@NonNull String accessToken) {
        MediatorLiveData<Resource<LogoutResponseEntity>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<LogoutResponseEntity>> api = restApi.logout(accessToken);


        result.addSource(api, response -> {
            if (response == null) {
                result.setValue(Resource.error(StatusNetwork.ERROR, "Response null", null));
            } else if (response.isSuccessful() && response.body != null) {
                LogoutResponseEntity entity = Objects.requireNonNull(response.body);
                result.setValue(Resource.success(entity));
            } else {
                result.setValue(Resource.error(response.code, response.errorMessage, null));
            }
        });
        return result;
    }
}
