/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.binding;

import android.text.TextUtils;
import android.widget.TextView;

import com.hahalolo.android.wallet.R;

import java.text.NumberFormat;

/**
 * @author ngannd
 * Create by ngannd on 28/05/2019
 */
public class BindingAdapter {

    @android.databinding.BindingAdapter(value = {"price", "curr"})
    public static void updatePrice(TextView textView, double price, String curr) {
        try {
            String total = com.hahalolo.android.wallet.utils.NumberFormat.priceFormatWithCurr(curr, price);
            if (TextUtils.isEmpty(total)) {
                NumberFormat fmt = NumberFormat.getInstance();
                fmt.setGroupingUsed(false);
                fmt.setMaximumIntegerDigits(999);
                fmt.setMaximumFractionDigits(999);
                total = fmt.format(price);
                textView.setText(textView.getContext().getString(R.string.main_current_price, total, curr));
            } else {
                textView.setText(textView.getContext().getString(R.string.main_current_price_total, total));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
