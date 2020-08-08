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

import static com.hahalolo.android.wallet.constant.ApiAppKey.API_KEY;
import static com.hahalolo.android.wallet.constant.ApiAppKey.API_KEY_DEV;
import static com.hahalolo.android.wallet.constant.ApiAppKey.API_KEY_TEST;

/**
 * @author ngannd
 * Create by ngannd on 18/12/2018
 */
@StringDef({API_KEY, API_KEY_TEST, API_KEY_DEV})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiAppKey {
    String API_KEY = "HAHALOLO_150519_3GGNAQ7QD2";
    String API_KEY_TEST = "TEST_HAHALOLO_140519_ZOMY8FS1SI";
    String API_KEY_DEV = "TEST_HAHALOLO_140519_ZOMY8FS1SI";
}
