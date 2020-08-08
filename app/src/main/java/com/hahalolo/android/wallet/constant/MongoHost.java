/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ngannd
 * Create by ngannd on 18/12/2018
 */
@Retention(RetentionPolicy.SOURCE)
public @interface MongoHost {
    String API_MONGO_HOST = "https://core-api.hahalolo.com";
    String API_MONGO_HOST_TEST = "https://test-core-api.hahalolo.com";
    String API_MONGO_HOST_DEV = "https://dev-core-api.hahalolo.com";
}