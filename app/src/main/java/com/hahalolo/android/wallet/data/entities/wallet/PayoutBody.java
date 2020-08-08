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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.data.entities.common.BaseEntity;

import java.util.List;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public class PayoutBody extends BaseEntity {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("fn450")
    @Expose
    private List<N450> fn450;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.fn450);
    }

    public PayoutBody() {
    }

    protected PayoutBody(Parcel in) {
        this.id = in.readString();
        this.fn450 = in.createTypedArrayList(N450.CREATOR);
    }

    public static final Parcelable.Creator<PayoutBody> CREATOR = new Parcelable.Creator<PayoutBody>() {
        @Override
        public PayoutBody createFromParcel(Parcel source) {
            return new PayoutBody(source);
        }

        @Override
        public PayoutBody[] newArray(int size) {
            return new PayoutBody[size];
        }
    };
}
