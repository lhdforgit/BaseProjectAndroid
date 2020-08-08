/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccountdetail.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.databinding.PayoutAccountInfoItemBinding;
import com.hahalolo.android.wallet.databinding.PayoutAccountPaymentMethodItemBinding;

public class PayoutAccoutPaymentMethodItem extends RelativeLayout {
    public PayoutAccoutPaymentMethodItem(Context context) {
        super(context);
        initView();
    }

    public PayoutAccoutPaymentMethodItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PayoutAccoutPaymentMethodItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private PayoutAccountPaymentMethodItemBinding binding;

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.payout_account_payment_method_item, this, false);
        addView(binding.getRoot(), LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
