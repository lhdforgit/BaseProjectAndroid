/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.constant;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hahalolo.android.wallet.constant.HaloConfig.Dev.DEV;
import static com.hahalolo.android.wallet.constant.HaloConfig.Dev.LIVE;
import static com.hahalolo.android.wallet.constant.HaloConfig.Dev.TEST;

/**
 * @author ngannd
 * Create by ngannd on 18/12/2018
 */
public final class HaloConfig {

    @IntDef({DEV, TEST, LIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Dev {
        int DEV = 0;
        int TEST = 1;
        int LIVE = 2;
    }

    private static int dev = TEST;

    public static final int DATABASE_VERSION = 10;

    public static final String MONGO_HOST = dev == DEV ? MongoHost.API_MONGO_HOST_DEV
            : (dev == TEST ? MongoHost.API_MONGO_HOST_TEST : MongoHost.API_MONGO_HOST);

    public static final String ES_HOST = dev == DEV ? EsHost.API_ELASTIC_HOST_DEV
            : (dev == TEST ? EsHost.API_ELASTIC_HOST_TEST : EsHost.API_ELASTIC_HOST);

    public static final String APP_KEY = dev == DEV ? ApiAppKey.API_KEY_DEV
            : (dev == TEST ? ApiAppKey.API_KEY_TEST : ApiAppKey.API_KEY);

    @SuppressLint("HardwareIds")
    public static String userAgent() {
        // User-Agent: <product> / <product-version>
        return Build.MODEL + "/" + Build.MANUFACTURER + "/";
    }

    public static final String DEVICE_NAME = "deviceName";
    public static final String DEVICE_NAME_VALUE = "Mobile";
    public static final String MODEL = "model";
    public static final String MODEL_VALUE = Build.MODEL;
    public static final String MANUFACTURER = "manufacturer";
    public static final String MANUFACTURER_VALUE = Build.MANUFACTURER;
    public static final String VERSION_CODE = "versionCode";
    public static final String VERSION_NAME = "versionName";
    public static final String API_APP_KEY = "api_app_key";

    public static final String VND_CURRENCY = "VND";
    public static final String LANGUAGE_DEFAULT = "vi";

    public static final int SHORT_BODY_TEXT_COUNT = 125;
    public static final int SEE_MORE_BODY_LIMIT_TEXT_COUNT = 350;

    public static final String MONGO_DATE_FORMAT = "yyyy-MM-dd";

    public static final String TOKEN_N_ACTIVE = "n-active";

    public static final String POST_DETAIL_URL = dev == DEV ? HaloHost.HALO_HOST_DEV
            : (dev == TEST ? HaloHost.HALO_HOST_TEST : HaloHost.HALO_HOST) + "/experience.php/newfeed/home?type=post&id=";
    public static final String PERSONAL_WALL_URL = dev == DEV ? HaloHost.HALO_HOST_DEV
            : (dev == TEST ? HaloHost.HALO_HOST_TEST : HaloHost.HALO_HOST) + "/personalwall.php/personal/timeline?pn100=";
}