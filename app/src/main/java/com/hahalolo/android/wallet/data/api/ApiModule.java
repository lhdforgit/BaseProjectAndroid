/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api;

import com.hahalolo.android.wallet.data.api.oauth.OauthMongoRestApi;
import com.hahalolo.android.wallet.data.api.oauth.OauthMongoRestApiImpl;
import com.hahalolo.android.wallet.data.api.wallet.WalletMongoRestApi;
import com.hahalolo.android.wallet.data.api.wallet.WalletMongoRestApiImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/16/18.
 */
@Module
public abstract class ApiModule {

    @Singleton
    @Binds
    abstract OauthMongoRestApi bindsOauthRestApi(OauthMongoRestApiImpl restApi);


    @Singleton
    @Binds
    abstract WalletMongoRestApi bindsWalletMongoRestApi(WalletMongoRestApiImpl restApi);

}
