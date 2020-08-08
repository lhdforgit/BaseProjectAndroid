/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ngannd
 * Create by ngannd on 10/04/2019
 */
@IntDef({ErrorType.TOAST, ErrorType.DIALOG})
@Retention(RetentionPolicy.SOURCE)
public @interface ErrorType {
    int TOAST = 1;
    int DIALOG = 2;
}
