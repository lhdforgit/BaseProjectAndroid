/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class PayoutDetailBody implements Parcelable {
    private String walletId;
    private String payoutId;

    public PayoutDetailBody(@NonNull String walletId, @NonNull String payoutId) {
        this.walletId = walletId;
        this.payoutId = payoutId;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getPayoutId() {
        return payoutId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.walletId);
        dest.writeString(this.payoutId);
    }

    protected PayoutDetailBody(Parcel in) {
        this.walletId = in.readString();
        this.payoutId = in.readString();
    }

    public static final Parcelable.Creator<PayoutDetailBody> CREATOR = new Parcelable.Creator<PayoutDetailBody>() {
        @Override
        public PayoutDetailBody createFromParcel(Parcel source) {
            return new PayoutDetailBody(source);
        }

        @Override
        public PayoutDetailBody[] newArray(int size) {
            return new PayoutDetailBody[size];
        }
    };
}
