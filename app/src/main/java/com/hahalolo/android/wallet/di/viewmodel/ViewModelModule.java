/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.hahalolo.android.wallet.presentation.creat.WalletCreateViewModel;
import com.hahalolo.android.wallet.presentation.login.WalletLoginViewModel;
import com.hahalolo.android.wallet.presentation.main.WalletMainViewModel;
import com.hahalolo.android.wallet.presentation.makerequestpayout.WalletMakeRequestPayoutViewModel;
import com.hahalolo.android.wallet.presentation.payout.WalletPayoutViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.WalletPayoutAccountViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.infor.CreatePayoutAccountInforViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.owner.CreatePayoutAccountOwnerViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.payment_method.CreatePayoutAccountPaymentMethodViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccount.create.tax_form.CreatePayoutAccountTaxFormViewModel;
import com.hahalolo.android.wallet.presentation.payoutaccountdetail.WalletPayoutAccountDetailViewModel;
import com.hahalolo.android.wallet.presentation.payouthistory.WalletPayoutHistoryViewModel;
import com.hahalolo.android.wallet.presentation.recharege.WalletRechargeViewModel;
import com.hahalolo.android.wallet.presentation.start.WalletStartViewModel;
import com.hahalolo.android.wallet.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/1/18.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(WalletCreateViewModel.class)
    abstract ViewModel bindWalletCreateViewModel(WalletCreateViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletLoginViewModel.class)
    abstract ViewModel bindWalletLoginViewModel(WalletLoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletMainViewModel.class)
    abstract ViewModel bindWalletMainViewModel(WalletMainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletPayoutViewModel.class)
    abstract ViewModel bindWalletPayoutViewModel(WalletPayoutViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletStartViewModel.class)
    abstract ViewModel bindWalletStartViewModel(WalletStartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletMakeRequestPayoutViewModel.class)
    abstract ViewModel bindWalletMakeRequestPayoutViewModel(WalletMakeRequestPayoutViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletPayoutAccountViewModel.class)
    abstract ViewModel bindWalletPayoutAccountViewModel(WalletPayoutAccountViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletPayoutAccountDetailViewModel.class)
    abstract ViewModel bindWalletPayoutAccountDetailViewModel(WalletPayoutAccountDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletPayoutHistoryViewModel.class)
    abstract ViewModel bindWalletPayoutHistoryViewModel(WalletPayoutHistoryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletRechargeViewModel.class)
    abstract ViewModel bindWalletRechargeViewModel(WalletRechargeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreatePayoutAccountInforViewModel.class)
    abstract ViewModel bindCreatePayoutAccountInforViewModel(CreatePayoutAccountInforViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreatePayoutAccountOwnerViewModel.class)
    abstract ViewModel bindCreatePayoutAccountOwnerViewModel(CreatePayoutAccountOwnerViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreatePayoutAccountPaymentMethodViewModel.class)
    abstract ViewModel bindCreatePayoutAccountPaymentMethodViewModel(CreatePayoutAccountPaymentMethodViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreatePayoutAccountTaxFormViewModel.class)
    abstract ViewModel bindCreatePayoutAccountTaxFormViewModel(CreatePayoutAccountTaxFormViewModel viewModel);

}
