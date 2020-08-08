/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.wallet;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.data.entities.common.BaseEntity;

/**
 * @author ngannd
 * Create by ngannd on 25/05/2019
 */
public class PayoutRequestBody extends BaseEntity {

    // Wallet id
    @SerializedName("pn300")
    @Expose
    private String pn300;

    // User id
    @SerializedName("pn100")
    @Expose
    private String pn100;

    // Payout account
    @SerializedName("pn450")
    @Expose
    private String pn450;

    // Currency
    @SerializedName("currency")
    @Expose
    private String currency;

    // Request money
    @SerializedName("nn503")
    @Expose
    private double nn503;

    // Description
    @SerializedName("nv507")
    @Expose
    private String nv507;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.pn300);
        dest.writeString(this.pn100);
        dest.writeString(this.pn450);
        dest.writeString(this.currency);
        dest.writeDouble(this.nn503);
        dest.writeString(this.nv507);
    }

    public PayoutRequestBody() {
    }

    protected PayoutRequestBody(Parcel in) {
        super(in);
        this.pn300 = in.readString();
        this.pn100 = in.readString();
        this.pn450 = in.readString();
        this.currency = in.readString();
        this.nn503 = in.readDouble();
        this.nv507 = in.readString();
    }

    public static final Creator<PayoutRequestBody> CREATOR = new Creator<PayoutRequestBody>() {
        @Override
        public PayoutRequestBody createFromParcel(Parcel source) {
            return new PayoutRequestBody(source);
        }

        @Override
        public PayoutRequestBody[] newArray(int size) {
            return new PayoutRequestBody[size];
        }
    };
}
