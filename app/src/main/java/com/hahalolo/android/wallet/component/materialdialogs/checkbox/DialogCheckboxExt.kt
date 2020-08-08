/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
@file:Suppress("unused")

package com.hahalolo.android.wallet.component.materialdialogs.checkbox

import android.support.annotation.CheckResult
import android.support.annotation.StringRes
import android.support.v7.widget.AppCompatCheckBox
import android.view.View
import com.hahalolo.android.wallet.R
import com.hahalolo.android.wallet.component.materialdialogs.MaterialDialog
import com.hahalolo.android.wallet.component.materialdialogs.assertOneSet
import com.hahalolo.android.wallet.component.materialdialogs.utils.Util
import com.hahalolo.android.wallet.component.materialdialogs.utils.maybeSetTextColor

typealias BooleanCallback = ((Boolean) -> Unit)?

@CheckResult
fun MaterialDialog.getCheckBoxPrompt(): AppCompatCheckBox {
    return view.buttonsLayout.checkBoxPrompt
}

@CheckResult
fun MaterialDialog.isCheckPromptChecked() = getCheckBoxPrompt().isChecked

/**
 *
 * @author ndn
 * Create by ndn on 03/11/2018
 *
 * @param res The string resource to display for the checkbox label.
 * @param text The literal string to display for the checkbox label.
 * @param isCheckedDefault Whether or not the checkbox is initially checked.
 * @param onToggle A listener invoked when the checkbox is checked or unchecked.
 */
@CheckResult
fun MaterialDialog.checkBoxPrompt(
        @StringRes res: Int = 0,
        text: String? = null,
        isCheckedDefault: Boolean = false,
        onToggle: BooleanCallback
): MaterialDialog {
    assertOneSet("checkBoxPrompt", text, res)
    view.buttonsLayout.checkBoxPrompt.apply {
        this.visibility = View.VISIBLE
        this.text = text ?: Util.getString(this@checkBoxPrompt, res)
        this.isChecked = isCheckedDefault
        this.setOnCheckedChangeListener { _, checked ->
            onToggle?.invoke(checked)
        }
        maybeSetTextColor(windowContext, R.attr.md_color_content)
    }
    return this
}
