/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.common.paging;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hahalolo.android.wallet.data.common.paging.NetworkState.Status.FAILED;
import static com.hahalolo.android.wallet.data.common.paging.NetworkState.Status.RUNNING;
import static com.hahalolo.android.wallet.data.common.paging.NetworkState.Status.SUCCESS;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/20/18.
 */
public class NetworkState {

    @IntDef({RUNNING, SUCCESS, FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
        int RUNNING = 0;
        int SUCCESS = 1;
        int FAILED = 2;
    }

    @Status
    private int status;

    private String message;

    private NetworkState(@Status int status, String message) {
        this.status = status;
        this.message = message;
    }

    private NetworkState(int status) {
        this.status = status;
    }

    public static NetworkState LOADED = new NetworkState(SUCCESS);

    public static NetworkState LOADING = new NetworkState(RUNNING);

    public static NetworkState ERROR = new NetworkState(FAILED);

    public static NetworkState error(String message) {
        return new NetworkState(FAILED, message == null ? "unknown error" : message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
