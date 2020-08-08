/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.currency;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity;

import java.util.List;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/16/18.
 */
public class CurrencyResponseEntity implements Parcelable {

    @SerializedName("status")
    @Expose
    private StatusMongoEntity status;

    @SerializedName("elements")
    @Expose
    private List<CurrencyEntity> elements;

    public CurrencyResponseEntity() {
    }

    public StatusMongoEntity getStatus() {
        return status;
    }

    public List<CurrencyEntity> getElements() {
        return elements;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.status, flags);
        dest.writeTypedList(this.elements);
    }

    protected CurrencyResponseEntity(Parcel in) {
        this.status = in.readParcelable(StatusMongoEntity.class.getClassLoader());
        this.elements = in.createTypedArrayList(CurrencyEntity.CREATOR);
    }

    public static final Creator<CurrencyResponseEntity> CREATOR = new Creator<CurrencyResponseEntity>() {
        @Override
        public CurrencyResponseEntity createFromParcel(Parcel source) {
            return new CurrencyResponseEntity(source);
        }

        @Override
        public CurrencyResponseEntity[] newArray(int size) {
            return new CurrencyResponseEntity[size];
        }
    };

    public boolean haveElements() {
        return elements != null && !elements.isEmpty();
    }
}
