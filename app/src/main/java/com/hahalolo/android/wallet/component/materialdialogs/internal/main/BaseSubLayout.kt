/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */
package com.hahalolo.android.wallet.component.materialdialogs.internal.main

import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.Style.STROKE
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.ViewGroup
import com.hahalolo.android.wallet.R
import com.hahalolo.android.wallet.component.materialdialogs.utils.dimenPx
import com.hahalolo.android.wallet.component.materialdialogs.utils.getColor

/**
 * @author ndn
 * Create by ndn on 03/11/2018
 */
internal abstract class BaseSubLayout(
        context: Context,
        attrs: AttributeSet? = null
) : ViewGroup(context, attrs) {

    private val dividerPaint = Paint()
    protected val dividerHeight = dimenPx(R.dimen.md_divider_height)

    var drawDivider: Boolean = false
        set(value) {
            field = value
            invalidate()
        }

    init {
        @Suppress("LeakingThis")
        setWillNotDraw(false)
        dividerPaint.style = STROKE
        dividerPaint.strokeWidth = context.resources.getDimension(R.dimen.md_divider_height)
        dividerPaint.isAntiAlias = true
    }

    protected fun dialogParent(): DialogLayout {
        return parent as DialogLayout
    }

    protected fun dividerPaint(): Paint {
        dividerPaint.color = getDividerColor()
        return dividerPaint
    }

    protected fun debugPaint(
            @ColorInt color: Int,
            stroke: Boolean = false
    ): Paint = dialogParent().debugPaint(color, stroke)

    private fun getDividerColor(): Int {
        return getColor(dialogParent().dialog.context, attr = R.attr.md_divider_color)
    }
}
