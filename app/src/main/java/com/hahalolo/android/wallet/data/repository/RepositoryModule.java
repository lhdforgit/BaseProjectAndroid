/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.repository;

import com.hahalolo.android.wallet.data.repository.oauth.OauthRepository;
import com.hahalolo.android.wallet.data.repository.oauth.OauthRepositoryImpl;
import com.hahalolo.android.wallet.data.repository.payout.PayoutAccountRepository;
import com.hahalolo.android.wallet.data.repository.payout.PayoutAccountRepositoryImpl;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepository;
import com.hahalolo.android.wallet.data.repository.wallet.WalletRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/16/18.
 */
@Module
public abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract OauthRepository bindsOauthRepository(OauthRepositoryImpl repository);

    @Singleton
    @Binds
    abstract WalletRepository bindsWalletRepository(WalletRepositoryImpl repository);

    @Singleton
    @Binds
    abstract PayoutAccountRepository bindsPayoutAccountRepository(PayoutAccountRepositoryImpl repository);

}