/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.di;

import android.app.Application;
import android.content.Context;

import com.hahalolo.android.wallet.data.cache.currency.CurrencyPref;
import com.hahalolo.android.wallet.data.cache.currency.CurrencyPrefImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Module
public abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract Context bindContext(Application application);

    @Binds
    @Singleton
    abstract CurrencyPref bindCurrencyPref(CurrencyPrefImpl pref);
}