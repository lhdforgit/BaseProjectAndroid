/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs

import android.R.attr
import android.content.Context
import android.support.annotation.StyleRes
import com.hahalolo.android.wallet.R
import com.hahalolo.android.wallet.component.materialdialogs.utils.getColor
import com.hahalolo.android.wallet.component.materialdialogs.utils.isColorDark

/**
 * @author ndn
 * Create by ndn on 03/11/2018
 */
internal enum class Theme(@StyleRes val styleRes: Int) {
    LIGHT(R.style.MD_Light),
    DARK(R.style.MD_Dark);

    companion object {
        fun inferTheme(context: Context): Theme {
            val isPrimaryDark =
                    getColor(context = context, attr = attr.textColorPrimary).isColorDark()
            return if (isPrimaryDark) LIGHT else DARK
        }
    }
}
