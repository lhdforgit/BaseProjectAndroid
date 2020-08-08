/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.di;

import android.app.Application;

import com.hahalolo.android.wallet.data.api.ApiModule;
import com.hahalolo.android.wallet.data.repository.RepositoryModule;
import com.hahalolo.android.wallet.di.activity.ActivityBindingModule;
import com.hahalolo.android.wallet.di.viewmodel.ViewModelModule;
import com.hahalolo.android.wallet.presentation.HahaloloWalletApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidSupportInjectionModule.class,

        ActivityBindingModule.class,
        ViewModelModule.class,

        RepositoryModule.class,
        ApiModule.class
})
public interface AppComponent extends AndroidInjector<HahaloloWalletApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}