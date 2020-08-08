/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs.utils

import android.graphics.Typeface
import android.support.annotation.AttrRes
import android.support.annotation.CheckResult
import android.support.annotation.FontRes
import android.support.v4.content.res.ResourcesCompat
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog
import com.hahalolo.android.wallet.component.materialdialogs.assertOneSet

/**
 * @author ndn
 * Create by ndn on 03/11/2018
 */
@CheckResult
internal fun MaterialDialog.font(
        @FontRes res: Int? = null,
        @AttrRes attr: Int? = null
): Typeface? {
    assertOneSet("font", attr, res)
    if (res != null) {
        return ResourcesCompat.getFont(windowContext, res)
    }

    val a = windowContext.theme.obtainStyledAttributes(intArrayOf(attr!!))
    try {
        val resId = a.getResourceId(0, 0)
        if (resId == 0) return null
        return ResourcesCompat.getFont(windowContext, resId)
    } finally {
        a.recycle()
    }
}
