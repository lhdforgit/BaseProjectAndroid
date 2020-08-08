/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.constant;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hahalolo.android.wallet.constant.EsHost.API_ELASTIC_HOST;
import static com.hahalolo.android.wallet.constant.EsHost.API_ELASTIC_HOST_DEV;
import static com.hahalolo.android.wallet.constant.EsHost.API_ELASTIC_HOST_TEST;


/**
 * @author ngannd
 * Create by ngannd on 18/12/2018
 */
@StringDef({API_ELASTIC_HOST, API_ELASTIC_HOST_TEST, API_ELASTIC_HOST_DEV})
@Retention(RetentionPolicy.SOURCE)
public @interface EsHost {
    String API_ELASTIC_HOST = "https://es-api.hahalolo.com";
    String API_ELASTIC_HOST_TEST = "https://test-es-api.hahalolo.com";
    String API_ELASTIC_HOST_DEV = "https://dev-es-api.hahalolo.com";
}
