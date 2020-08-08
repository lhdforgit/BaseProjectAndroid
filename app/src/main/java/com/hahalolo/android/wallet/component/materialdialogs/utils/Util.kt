/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs.utils

import android.support.annotation.StringRes
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog

/**
 * @author ndn
 * Create by ndn on 03/11/2018
 */
object Util {
    fun getString(
            materialDialog: MaterialDialog,
            @StringRes res: Int? = null,
            @StringRes fallback: Int? = null
    ): CharSequence? {
        val resourceId = res ?: (fallback ?: 0)
        if (resourceId == 0) return null
        return materialDialog.windowContext.resources.getText(resourceId)
    }
}
