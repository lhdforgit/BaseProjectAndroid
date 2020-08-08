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
import java.util.Random;

/**
 * @author ngannd
 * Create by ngannd on 18/12/2018
 */
@Retention(RetentionPolicy.SOURCE)
public @interface MediaHost {
    String MEDIA_HOST = "https://media00" + (new Random().nextInt(8) + 1) + "-cdn.hahalolo.com/";
    String MEDIA_DOMAIN = "media00" + (new Random().nextInt(8) + 1) + "-cdn.hahalolo.com/";
    String MEDIA_HOST_TEST = "https://test-media00" + (new Random().nextInt(8) + 1) + ".hahalolo.com/";
    String MEDIA_DOMAIN_TEST = "test-media00" + (new Random().nextInt(8) + 1) + ".hahalolo.com/";
    String MEDIA_HOST_DEV = "https://test-dev-media00" + (new Random().nextInt(8) + 1) + ".hahalolo.com/";
    String MEDIA_DOMAIN_DEV = "test-dev-media00" + (new Random().nextInt(8) + 1) + ".hahalolo.com/";
}