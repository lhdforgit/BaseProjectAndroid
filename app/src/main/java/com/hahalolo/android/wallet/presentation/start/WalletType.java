/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.start;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ngannd
 * Create by ngannd on 28/05/2019
 */
@IntDef({WalletType.PERSONAL, WalletType.BUSINESS, WalletType.ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface WalletType {
    int PERSONAL = 0;
    int BUSINESS = 1;
    int ERROR = -1;
}
