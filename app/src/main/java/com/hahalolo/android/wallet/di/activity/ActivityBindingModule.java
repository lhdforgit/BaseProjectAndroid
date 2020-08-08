/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.di.activity;

import com.hahalolo.android.wallet.di.ActivityScoped;
import com.hahalolo.android.wallet.presentation.creat.WalletCreateAct;
import com.hahalolo.android.wallet.presentation.login.WalletLoginAct;
import com.hahalolo.android.wallet.presentation.main.WalletMainAct;
import com.hahalolo.android.wallet.presentation.makerequestpayout.WalletMakeRequestPayoutAct;
import com.hahalolo.android.wallet.presentation.payout.WalletPayoutAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.WalletPayoutAccountAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.infor.CreatePayoutAccountInforAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.payment_method.CreatePayoutAccountPaymentMethodAct;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form.CreatePayoutAccountTaxFormAct;
import com.hahalolo.android.wallet.presentation.payoutaccountdetail.WalletPayoutAccountDetailAct;
import com.hahalolo.android.wallet.presentation.payouthistory.WalletPayoutHistoryAct;
import com.hahalolo.android.wallet.presentation.recharege.WalletRechargeAct;
import com.hahalolo.android.wallet.presentation.start.WalletStartAct;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletCreateAct walletCreateAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletLoginAct walletLoginAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletMainAct walletMainAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletPayoutAct walletPayoutAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletStartAct walletStartAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletMakeRequestPayoutAct makeRequestPayoutAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletPayoutAccountAct payoutAccountAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletPayoutAccountDetailAct payoutAccountDetailAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletPayoutHistoryAct payoutHistoryAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WalletRechargeAct walletRechargeAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CreatePayoutAccountInforAct createPayoutAccountAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CreatePayoutAccountOwnerAct createPayoutAccountOwnerAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CreatePayoutAccountPaymentMethodAct createPayoutAccountPaymentMethodAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CreatePayoutAccountTaxFormAct createPayoutAccountTaxFormAct();
}