/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.cache.currency;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hahalolo.android.wallet.data.entities.currency.CurrencyEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.hahalolo.android.wallet.data.cache.serializer.Serializer;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/16/18.
 */
@Singleton
public class CurrencyPrefImpl implements CurrencyPref {

    private static final String CURRENCY_TARGET_PREF = "currency-target-pref";
    private static final String CURRENCY_TARGET_PREF_KEY = "currency-target-pref-key";

    private SharedPreferences sharedPref;
    private Serializer serializer;

    @Inject
    CurrencyPrefImpl(Context context, Serializer serializer) {
        this.sharedPref = context.getSharedPreferences(CURRENCY_TARGET_PREF, Context.MODE_PRIVATE);
        this.serializer = serializer;
    }

    @Override
    public void insertTargetCurrency(CurrencyEntity entity) {
        if (entity != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(CURRENCY_TARGET_PREF_KEY, serializer.serialize(entity, CurrencyEntity.class));
            editor.apply();
        }
    }

    @Override
    public CurrencyEntity getTargetCurrency() {
        String json = sharedPref.getString(CURRENCY_TARGET_PREF_KEY, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return serializer.deserialize(json, CurrencyEntity.class);
    }
}
