/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs.utils

import android.support.annotation.ArrayRes
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog

/**
 * @author ndn
 * Create by ndn on 03/11/2018
 */
internal fun MaterialDialog.getStringArray(@ArrayRes res: Int?): Array<String>? {
    if (res == null) return emptyArray()
    return windowContext.resources.getStringArray(res)
}
