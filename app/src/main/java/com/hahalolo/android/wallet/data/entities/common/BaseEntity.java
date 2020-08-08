/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author ngannd
 * Create by ngannd on 22/02/2019
 */
public class BaseEntity implements Parcelable {

    @SerializedName("uClientId")
    @Expose
    private String uClientId;

    public BaseEntity() {
    }

    public String getuClientId() {
        return uClientId;
    }

    public void setuClientId(String uClientId) {
        this.uClientId = uClientId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uClientId);
    }

    protected BaseEntity(Parcel in) {
        this.uClientId = in.readString();
    }

}
