/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs.internal.rtl

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.hahalolo.android.wallet.component.materialdialogs.utils.setGravityStartCompat

/**
 * With our custom layout-ing, using START/END gravity doesn't work so we manually
 * set text alignment for RTL/LTR.
 *
 * @author ndn
 * Create by ndn on 03/11/2018
 */
class RtlTextView(
        context: Context,
        attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {

    init {
        setGravityStartCompat()
    }
}
