/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.viewmodel;

import android.arch.lifecycle.LiveData;

/**
 * A LiveData class that has {@code null} value.
 */
public class AbsentLiveData extends LiveData {

    @SuppressWarnings("unchecked")
    private AbsentLiveData() {
        postValue(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> LiveData<T> create() {
        return new AbsentLiveData();
    }
}
