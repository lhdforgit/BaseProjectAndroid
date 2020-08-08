/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.common.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hahalolo.android.wallet.data.paging.PagingRequestHelper;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/20/18.
 */
public class PagingRequestHelperExt {

    public static LiveData<NetworkState> createStatusLiveData(PagingRequestHelper pagingRequestHelper) {
        MutableLiveData<NetworkState> liveData = new MutableLiveData<>();
        pagingRequestHelper.addListener(report -> {
            if (report.hasRunning()) {
                liveData.postValue(NetworkState.LOADING);
            } else if (report.hasError()) {
                liveData.postValue(NetworkState.error(report.toString()));
            } else {
                liveData.postValue(NetworkState.LOADED);
            }
        });
        return liveData;
    }
}