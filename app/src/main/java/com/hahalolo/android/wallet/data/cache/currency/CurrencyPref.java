/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.cache.currency;

import com.hahalolo.android.wallet.data.entities.currency.CurrencyEntity;

import javax.annotation.Nullable;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/16/18.
 */
public interface CurrencyPref {

    /**
     * Insert target currency to pref
     *
     * @param entity {@link CurrencyEntity} target currency
     */
    void insertTargetCurrency(CurrencyEntity entity);

    /**
     * Get target currency from pref
     *
     * @return entity {@link CurrencyEntity} target currency
     */
    @Nullable
    CurrencyEntity getTargetCurrency();
}
